 # -*- coding:UTF-8 -*-
from pickle import TRUE
import time,random,hashlib

PRIVATE_KEY = "PRIVATE_KEY";

class Signature():
        
    def generate(self, params):  
        param = []
        for v in params.values():
            param.append(v)
        timestamp = int(round(time.time() * 1000))
        param.append(timestamp)
        nonce = random.randint(100000,999999)
        param.append(nonce)
        privateKey = PRIVATE_KEY
        param.append(privateKey)
        param.sort(key=lambda v: (isinstance(v, str), v))
        param = list(map(lambda x:str(x),param))
        paramStr = "".join(param)
        paramStr = hashlib.sha1(paramStr.encode("utf-8")).hexdigest()
        return paramStr;

# test case       
params = {
    "a" : "a",
    "b": "b"
}
signObj = Signature()
signature = signObj.generate(params)
print(signature) 