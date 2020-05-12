## LeetCode 5.12 每日三题 + 每日打卡

### LeetCode 155：最小栈 每日打卡

#### 题目

[https://leetcode-cn.com/problems/min-stack/](https://leetcode-cn.com/problems/min-stack/)

#### 思路

使用两个栈，一个存放最小值，一个正常存放，pop 的时候判断一下

#### 代码

```java
class MinStack {

    Stack<Integer> normal;
    Stack<Integer> minS;

    /** initialize your data structure here. */
    public MinStack() {
        normal = new Stack<>();
        minS = new Stack<>();
    }
    
    public void push(int x) {
        if(minS.isEmpty() || minS.peek() >= x){
            minS.push(x);
        }
        normal.push(x);
    }
    
    public void pop() {
        if(normal.isEmpty())
            return;
        int n = minS.peek(),m = normal.peek();
        if(n == m)
            minS.pop();
        normal.pop();
    }
    
    public int top() {
        if(normal.isEmpty())
            return -1;
        return normal.peek();
    }
    
    public int getMin() {
        if(minS.isEmpty())
            return 0;
        return minS.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```



### LeetCode 13：罗马数字转整数

#### 题目

[https://leetcode-cn.com/problems/roman-to-integer/submissions/](https://leetcode-cn.com/problems/roman-to-integer/submissions/)



#### 思路

安装题目的要求，使用Map存放，然后判断情况就行

#### 代码

```java
class Solution {
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int ans = 0;
        for(int i = 0;i < s.length();i++){
            if(i == s.length()-1){
                char ch = s.charAt(i);
                ans += map.get(ch);
                break;
            }
            if(s.substring(i,i+2).equals("IV")){
                ans += 4;
                i+=1;
            }else if(s.substring(i,i+2).equals("IX")){
                ans += 9;
                i += 1;
            }else if(s.substring(i,i+2).equals("XL")){
                ans += 40;
                i += 1;
            }else if(s.substring(i,i+2).equals("XC")){
                ans += 90;
                i += 1;
            }else if(s.substring(i,i+2).equals("CD")){
                ans += 400;
                i += 1;
            }else if(s.substring(i,i+2).equals("CM")){
                ans += 900;
                i += 1;
            }else{
                char ch = s.charAt(i);
                ans += map.get(ch);
            }
        }
        return ans;
    }
}
```



### LeetCode 12：整数转罗马数字

#### 题目

[https://leetcode-cn.com/problems/integer-to-roman/submissions/](https://leetcode-cn.com/problems/integer-to-roman/submissions/)

#### 思路

将每个组成部分放入数组中，每次减去对应的值，直到<1

#### 代码

```java
class Solution {
    public String intToRoman(int num) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"I");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(50,"L");
        map.put(40,"XL");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(900,"CM");
        map.put(1000,"M");

        int[] nums = new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
        StringBuffer ans = new StringBuffer();
        while(num > 0){
            for(int i = nums.length-1;i>=0;i--){
                if(num <= 0)
                    break;
                int cnt = num/nums[i];
                for(int j = 0;j < cnt;j++){
                    ans.append(map.get(nums[i]));
                    num -= nums[i];
                }
            }
        }
        return ans.toString();
    }
}
```



### LeetCode 32：最长有效括号