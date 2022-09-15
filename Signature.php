<?php

class Signature
{
    const PRIVATE_KEY = "PRIVATE_KEY";

    public function __construct()
    {
    }

    protected function getUnixMicroTime()
    {
        $time = microtime(1);
        return floor($time * 1000);
    }

    public function generate(array $params)
    {
        $params['salt'] = self::PRIVATE_KEY;
        $params['timestamp'] = $this->getUnixMicroTime();
        $params['nonce'] = time();
        $tmpArr = array_values($params);
        sort($tmpArr, SORT_STRING);
        $str = implode($tmpArr);
        return sha1($str);
    }

}

// test case
$signObj = new Signature();
$params = [
    'a' => 'a',
    'b' => 'b'
];
$signature = $signObj->generate($params);
echo $signature . PHP_EOL;
