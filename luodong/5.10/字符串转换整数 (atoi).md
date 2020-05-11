# [8. 字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

> 解题思路分析

- 这道题目可能会超过integer的最大表示！
- 也就是说会在某一步 res* 10 + digit > Integer.MAX_VALUE。
- *10 和 +digit 都有可能越界，那么只要把这些都移到右边去就可以了。
- res> (Integer.MAX_VALUE - digit) / 10 就是越界。



### 代码实现


~~~java
class Solution {
    public static int myAtoi(String str) {
        str = str.trim();
        if(str==null||str.equals(""))
            return 0;
        int mark = 1,len=str.length(),res=0;
        if(str.charAt(0) == '-')
            mark=-1;
        for(int i = (str.charAt(0)=='+'||str.charAt(0)=='-'?1:0);i<len;i++){
            if(!(str.charAt(i)>='0'&&str.charAt(i)<='9'))
                break;
            //越界
            if(res>(Integer.MAX_VALUE-(str.charAt(i)-'0'))/10){
                return mark==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            res = (res << 1) + (res << 3)+str.charAt(i)-'0';
        }
        return res*mark;
    }
}
~~~