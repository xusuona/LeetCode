## LeetCode 5.1 每日三题

### LeetCode 693：交替位二进制数

#### 思路

右移和前一位的结果相比较，暴力

#### 代码

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        int cmp = n & 1;
        while(n>0){
            n >>= 1;
            int tmp = n & 1;
            if(tmp == cmp)
                return false;
            cmp = tmp;
        }
        return true;
    }
}
```

### LeetCode 535：TinyURL的加密解密

#### 思路

使用URL的hashcode值进行加密解密，使用HashMap存储

#### 代码

```java
public class Codec {
     Map<String, String> map = new HashMap<>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = String.valueOf(longUrl.hashCode());
        map.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
```



