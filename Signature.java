import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class Signature {
    private static final String PRIVATE_KEY = "PRIVATE_KEY";

    public static String sha1(String str) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    public static String generate(Map<String, String> map) {
        map.put("salt", PRIVATE_KEY);
        map.put("timestamp", String.valueOf(System.currentTimeMillis())); //  1660726411772
        map.put("nonce", String.valueOf(new Random().nextInt(Integer.MAX_VALUE))); // 123456
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        StringBuilder tempStr = new StringBuilder();
        for (Map.Entry<String, String> mapping : list) {
            tempStr.append(mapping.getValue());
        }
        return sha1(tempStr.toString());
    }

    // test
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        String signature = generate(map);
        System.out.println(signature);
    }
}

