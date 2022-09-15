// npm install js-sha1
// node Signature.js
const sha1 = require('js-sha1');
const PRIVATE_KEY = "PRIVATE_KEY";

class Signature {

    constructor() {
    }

    generate(params, isFromPc){
      let param = [];
      if(Object.keys(params).length){
         for(let i in params){
            param.push(params[i]);
         }
      }
      let salt = PRIVATE_KEY;
      param.push(salt);
      let timestamp = new Date().getTime();
      param.push(timestamp);
      let nonce = Math.ceil(Math.random() * 1000000);
      param.push(nonce);
      param.sort();
      param = param.join().replaceAll(',','');
      return sha1(param);
    }
}

// test case
let params = {
   "a" : "a",
   "b": "b"
}
let signObj = new Signature();
let signature = signObj.generate(params, true);
console.log(signature)