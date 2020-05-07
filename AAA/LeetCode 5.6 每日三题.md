## LeetCode 5.6 每日三题

### LeetCode 1290：二进制链表转整数

#### 思路

 深搜，返回上一届的结果，同时使用一个全局变量记录层数，使用1左移得到对应的结果

#### 代码

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    int level = 0;

    public int dfs(ListNode head){
        if(head.next == null){
            level++;
            return head.val;
        }

        int tmp = dfs(head.next);
        int tmp2 = head.val*(1 <<level);
        level++;
        return tmp+tmp2;
    }

    public int getDecimalValue(ListNode head) {
        return dfs(head);
    }
}
```

### LeetCode 468：验证IP地址

#### 思路

使用题目的要求，对每一部分都做判断

#### 代码

```java
class Solution {
    public String IPV4(String[] IP){
        int n = IP.length;
        //System.out.println(n);
        if(n != 4)
            return "Neither";
        for(int i = 0;i < 4;i++){
            if(IP[i].length() < 1 || IP[i].length() > 3)
                return "Neither";
            for(char a:IP[i].toCharArray()){
                if(!Character.isDigit(a))
                    return "Neither";
            }
            if(IP[i].length() > 1 && IP[i].charAt(0) == '0')//类似于001不合法
                return "Neither";
            if(Integer.parseInt(IP[i]) < 0 || Integer.parseInt(IP[i]) > 255)//数字不合法的情况
                return "Neither";
            //判断每个字符是否合法
            
        }
        return "IPv4";
    }
    public String IPV6(String[] IP){
        int n = IP.length;
        if(n != 8)
            return "Neither";
        String hexdigits = "0123456789abcdefABCDEF";
        for(int i = 0;i < 8;i++){
            if(IP[i].length() < 1 || IP[i].length() > 4)
                return "Neither";
           
            for(char a:IP[i].toCharArray()){
                if(hexdigits.indexOf(a) == -1)
                    return "Neither";
            }
        }
        return "IPv6";
    }



    public String validIPAddress(String IP) {
        boolean tag = false;
        boolean falg = false;
        for(int i = 0;i < IP.length();i++){
            if(IP.charAt(i) == '.')
                tag = true;
            if(IP.charAt(i) == ':')
                falg = true;
        }

        if(tag && !falg){
            if(IP.charAt(0) == '.' || IP.charAt(IP.length()-1) == '.')
                return "Neither";
            String[] str = IP.split("\\.");
            return IPV4(str);
        }else if(falg && !tag){

            if(IP.charAt(0) == ':' || IP.charAt(IP.length()-1) == ':')
                return "Neither";
            return IPV6(IP.split(":"));
        }
        return "Neither";
    }
}
```

