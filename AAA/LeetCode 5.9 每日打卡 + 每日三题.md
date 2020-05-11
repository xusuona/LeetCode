## LeetCode 5.9 每日打卡 + 每日三题

### LeetCode 69：X的平方根：每日打卡

#### 题目

[https://leetcode-cn.com/problems/sqrtx/](https://leetcode-cn.com/problems/sqrtx/)

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

#### 思路

二分查找

#### 代码

```java
class Solution {
    public int mySqrt(int x) {
        long mid = x/2;
        while(true){
            long tmp = mid*mid;
            if(tmp == x)
                return (int)mid;
            if(tmp > x){
                mid /= 2; 
            }else{
                long add = (mid+1)*(mid+1);
                if(add > x)
                    return (int)mid;
                else{
                    mid += 1; 
                }
            }
        }
    }
}
```

### leetCode 面试题42.连续子数组的最大和

#### 题目

[https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/submissions/](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/submissions/)

输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

#### 思路

贪心法，当之前的总和小于0，或者之前的总和加上当前的数字小于0，就说明不能继续加，这样会使总和变小

#### 代码

```java
class Solution {
    public int maxSubArray(int[] nums) {
        
        if(nums.length == 0)
            return 0;
        int maxSum = nums[0];
        int tmpSum = nums[0];
        for(int i = 1;i < nums.length;i++){
            if(tmpSum < 0||tmpSum+nums[i] < 0){
                tmpSum = nums[i];     
            }else{
                tmpSum += nums[i];
            }
            maxSum = Math.max(maxSum,tmpSum);
        } 
        return maxSum;
    }
}
```



### LeetCode 面试题38.字符串的排列

#### 题目

[https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)

输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

#### 思路

回溯法，标记已经被使用的字符，同时使用set集合存放

#### 代码

```java
class Solution {

    Set<String> ans = new HashSet<>();


    public void dfs(StringBuffer str,boolean[] isR,String s){
        if(str.length() == s.length()){//拼接的字符串和原来的字符串相同时，递归的出口
            StringBuffer tmp =  new StringBuffer(str);
            if(!ans.contains(tmp.toString())){
                ans.add(tmp.toString());
            }
            return;
        }

        for(int i = 0;i < s.length();i++){
            if(!isR[i]){//还没有被加入
                isR[i] = true;
                str.append(s.substring(i,i+1));
                dfs(str,isR,s);
                str.deleteCharAt(str.length()-1);
                isR[i] = false;  
            }
        }
    }

    public String[] permutation(String s) {
        dfs(new StringBuffer(),new boolean[s.length()],s);
        return ans.toArray(new String[ans.size()]);
    }
}
```



### LeetCode 面试题51.数组中的逆序对

#### 题目

[https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/](https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/)

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

#### 思路

归并排序的思想，两个有序集合的逆序对可以通过归并排序过程中得到

#### 代码

```java
class Solution {
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if(len < 2)
            return 0;
        int[] copy = new int[len];
        for(int i = 0;i < len;i++)
            copy[i] = nums[i];

        return help(nums,0,len-1,copy);
    }


    public int help(int[] nums,int left,int right,int[] copy){
        if(left >= right)//递归函数的出口
            return 0;
        int mid = left + (right-left)/2;

        int leftCount = help(nums,left,mid,copy);
        int rightCount = help(nums,mid+1,right,copy);

        if(nums[mid] <= nums[mid+1])//当两个序列有序时，说明不存在逆序对
            return leftCount+rightCount;
        
        int Count = Cnt(nums,left,mid,right,copy);
        return leftCount+rightCount+Count;
    }


    public int Cnt(int[] nums,int left,int mid,int right,int[] copy){
        for(int i = left;i <= right;i++)
            copy[i] = nums[i];
        int i = left,j = mid+1;
        int count = 0;

        for(int k = left;k <= right;k++){

            if(i == mid+1){
                nums[k] = copy[j];
                j++;
            }else if(j == right+1){
                nums[k] = copy[i];
                i++;
            }else if(copy[i] <= copy[j]){
                nums[k] = copy[i];
                i++;
            }else{
                nums[k] = copy[j];
                j++;
                count += mid-i+1;
            }
        }

        return count;
    }
}
```

 