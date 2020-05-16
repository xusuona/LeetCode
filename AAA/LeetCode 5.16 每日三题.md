## LeetCode 5.16 每日三题

### LeetCode 面试题 39：数组中出现次数超过一半的数字

#### 题目

[https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/)

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

#### 思路

使用单调栈，cnt记录栈中元素个数，如果当前元素和栈顶元素相同时，消去栈顶元素，最终剩下的就是答案

#### 代码

```java
class Solution {
    public int majorityElement(int[] nums) {
        int sk = nums[0],cnt = 1;
        for(int i = 1;i < nums.length;i++){
            if(nums[i] == sk){
                cnt++;
            }else{
                cnt--;
                if(cnt == 0){
                    sk = nums[i];
                    cnt++;
                }
            }
        }
        return sk;
    }
}
```



### LeetCode 面试题 63：股票的最大利润

#### 题目

[https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/](https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/)

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

#### 思路

贪心法

#### 代码

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        if(prices.length == 0)
            return ans;
        int min = prices[0];
        for(int i = 1;i < prices.length;i++){
            if(prices[i] < min)
                min = prices[i];
            ans = Math.max(ans,prices[i]-min);
        }
        return ans;
    }
}
```



### LeetCode 233：数字1的个数