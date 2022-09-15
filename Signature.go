package main

import (
	"Math/rand"
	"crypto/sha1"
	"encoding/hex"
	"fmt"
	"sort"
	"time"
)

const PRIVATE_KEY string = "PRIVATE_KEY"

func generate(m interface{}, isFromPc bool) string {
	// p := fmt.Println
	params := m.(map[string]string)

	params["salt"] = PRIVATE_KEY
	timestamp := time.Now().UnixNano() / 1e6
	params["timestamp"] = fmt.Sprintf("%d", timestamp)
	nonce := rand.Intn(999999) + 100000
	params["nonce"] = fmt.Sprintf("%d", nonce)
	s := []string{}
	for _, v := range params {
		s = append(s, v)
	}
	sort.Strings(s)
	// sha1
	str := ""
	for _, val := range s {
		str += val
	}
	// p(str)
	h := sha1.New()
	h.Write([]byte(str))
	bs := h.Sum(nil)
	return hex.EncodeToString(bs)
}

func main() {
	m := make(map[string]string)
	m["a"] = "a"
	m["b"] = "b"
	signature := generate(m, true)
	fmt.Println(signature)
}
