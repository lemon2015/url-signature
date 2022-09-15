# url-signature

一个简单的带时效和签名的方法示例，可以生成带过期时间的访问链接和防止篡改参数
A simple and common url parameter signature tool  to prevent url tampering.

## 必传参数 (required params):

* signature 计算出的签名 token
* timestamp 13位unix时间戳 unix timestamp
* nonce  自定义长度的随机数 rand number

## 计算方法

对除signature外的全部参数签名,先字典升序后sha1
Sign all parameters except signature, firstly in ascending dictionary order and then encrypt with sha1

生成类似下面的url:
generate url with signature , e.g.:

```
https://www.abc.com?a=a&b=b&timestamp=1660726411772&nonce=123456&signature=f757a7988a3d9ea04c3ed85ca84e13de4bae764c

```

最后可以在服务器端通过校验时间戳和签名，来控制url的访问时间，防止被人恶意篡改和持久访问。
Finally, the access time of the url can be controlled by verifying the timestamp and signature on the server side to prevent malicious tampering and persistent access.

下面给出一些常见后端开发语言的代码示例。
Here is some languages code e.g.