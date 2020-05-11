## LeetCode 5.10 每日打卡 + 每日三题

### LeetCode 236：二叉树的最近公共父祖先 每日打卡

#### 题目

[https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/)

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

#### 思路

记录到每个指定节点的路径，然后就可以找到最近公共祖先

#### 代码

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    ArrayList<TreeNode> p1 = new ArrayList<>();
    ArrayList<TreeNode> p2 = new ArrayList<>();


    public void dfs(TreeNode root,TreeNode p,TreeNode q,ArrayList<TreeNode> list){
        list.add(root);
        if(root == null){
            return;
        }

        if(root == p){
            p1 = new ArrayList<>(list);
        }else if(root == q){
            p2 = new ArrayList<>(list);
        }


        if(root.left != null){
            dfs(root.left,p,q,list);
            list.remove(list.size()-1);
        }


        if(root.right != null){
            dfs(root.right,p,q,list);
            list.remove(list.size()-1);
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q,new ArrayList<TreeNode>());
        int n = p1.size()-1,m = p2.size()-1;
        if(n != m){
            if(m>n){
                m = n;
            }else
                n = m;
        }

        while(n >= 0 && m >= 0){
            if(p1.get(n) == p2.get(m))
                return p1.get(n);
            n--;
            m--;
        }
        return null;
    }
}
```

### LeetCode 70：爬楼梯

#### 题目

[https://leetcode-cn.com/problems/climbing-stairs/submissions/](https://leetcode-cn.com/problems/climbing-stairs/submissions/)

假设你正在爬楼梯。需要 *n* 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

**注意：**给定 *n* 是一个正整数。

#### 思路

动态规划，递归会超时，dp[i] = dp[i-1]+dp[i-2],可以只使用两个变量记录

#### 代码

```java
class Solution {
    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        int a = 1,b = 2;
        int ans = 0;
        for(int i = 3;i <= n;i++){
            ans = a+b;
            a = b;
            b = ans;
        }
        return ans;
    }
}
```

### LeetCode 8：字符串转整数

#### 题目

[https://leetcode-cn.com/problems/string-to-integer-atoi/](https://leetcode-cn.com/problems/string-to-integer-atoi/)

请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：

本题中的空白字符只包括空格字符 ' ' 。
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。



#### 思路

按照题目要求。筛选不合法的数据

#### 代码

```java
class Solution {
    public int myAtoi(String str) {
        if(str.length() == 0)
        return 0;
        int ans = 0;
        int i = 0;
        boolean flag = false;
        for(;i < str.length();i++){
            if(str.charAt(i) >='0' && str.charAt(i) <= '9'){
                break;
            }
            else if(str.charAt(i) == '+' || str.charAt(i) == '-'){
                if(str.charAt(i) == '-')
                    flag = true;
                i++;
                break;
            }
            else if(str.charAt(i) != ' ')
                return 0;
        }
        int j = i;
        for(;j < str.length();j++)
            if(str.charAt(j) < '0' || str.charAt(j) > '9')
                break;
        String tmp = str.substring(i,j);
        // System.out.println(tmp);
        for(int k = 0;k < tmp.length();k++){
            int cur = tmp.charAt(k)-'0';
            if(flag){
                if(ans < Integer.MIN_VALUE/10 || ans == Integer.MIN_VALUE/10 && cur > 8)
                    return Integer.MIN_VALUE;
                ans = ans*10-cur;
            }
            else{
                if(ans > Integer.MAX_VALUE/10 || ans == Integer.MAX_VALUE/10 && cur > 7)
                    return Integer.MAX_VALUE;
                ans = ans*10+cur;
            }
        }
        return ans;
    }
}
```



### LeetCode 37：解数独