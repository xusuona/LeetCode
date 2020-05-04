## LeetCode 5.4 每日三题

### LeetCode 面试题05.06

#### 思路

使用异或操作将位数不同的值设为1，之后将 **n & (n-1)** 得到最后一位的1的个数

#### 代码

```java
class Solution {
    public int convertInteger(int A, int B) {
        int cnt = 0;
        int cmp = A^B;
        while(cmp !=  0){
            cmp = cmp & (cmp-1);
            cnt++;
        }
        return cnt;
    }
}
```

### LeetCode 面试题 

#### 思路

使用滑动窗口，使用set集合存放字符，

#### 代码

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() ==0 || s.length() == 1)
            return s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0,j = 0;
        while(i < s.length() && j < s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
            }else{
                set.remove(s.charAt(i++));
            }
            ans = Math.max(ans,j-i);
        }
        return  ans;
    }
}
```

