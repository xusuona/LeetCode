## LeetCode 5.8 每日打卡+每日三题

### LeetCode 221：每日打卡：求最大正方形

#### 题目

[https://leetcode-cn.com/problems/maximal-square/](https://leetcode-cn.com/problems/maximal-square/)

在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

#### 思路

使用动态规划的思想，**dp[i,j]表示以i,j为右下角的正方形的边长**，

**转移方程为：**

**dp[i\][j] = Math.min(dp[i-1\][j],dp[i\][j-1],dp[i-1\][j-1])+1**

因为dp[i,j]表示的是以当前位置为右下角的正方形边长，所以，结合[i-1.j],[i,j-1],[i-1,j-1]的值中最小值可以得到当前的最大边长

#### 代码

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length,m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 0; 
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(matrix[i][j] == '1'){
                    if(i == 0 || j==0){
                        dp[i][j] = 1;
                    }else
                        dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
                 ans = Math.max(ans,dp[i][j]);
            }
        }
        return ans*ans;
    }
}
```

### LeetCode 面试题17.01：不用加号的加法

#### 题目

[https://leetcode-cn.com/problems/add-without-plus-lcci/](https://leetcode-cn.com/problems/add-without-plus-lcci/)

设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。

#### 思路

异或之后的值时不含进位的结果，而取与操作后右移一位正好等于进位的结果，循环直到进位值为0，得到加法的值

#### 代码

```java
class Solution {
    public int add(int a, int b) {
        int a1 = 0,b1 = 0;
        while(b != 0){
            a1 = a^b;//这是半加，不包含进位的加法
            b1 = (a&b) << 1;//这是需要进位的值

            a = a1;
            b = b1; 
        }
        return a;
    }
}
```



### LeetCode 445：两数相加II

#### 题目

[https://leetcode-cn.com/problems/add-two-numbers-ii/](https://leetcode-cn.com/problems/add-two-numbers-ii/)

给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

**进阶：**

如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

#### 思路

首先求出两个链表的长度，如果相等直接递归求和，不相等就取出位数相同的部分，进行递归求和，使用全局变量记录进位，之后再将多的部分进行求和，将两段链表拼接，得到最终结果

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

    public int exAdd = 0;//进位值

    public ListNode getAns(ListNode l1,ListNode l2){
        if(l1.next == null && l2.next == null){//当两个都到达末尾时，可以进行加法
            int val = l1.val+l2.val;
            exAdd = val/10;
            ListNode tmp = new ListNode(val%10);
            return tmp;
        }

        //此时说明没有到达结尾
        ListNode next = null;
        if(l1.next == null)
            next = getAns(l1,l2.next);
        else if(l2.next == null)
            next = getAns(l1.next,l2);
        else
            next = getAns(l1.next,l2.next);

        int val = l1.val+l2.val+exAdd;
        exAdd=  val/10;
        ListNode ans = new ListNode(val%10);
        ans.next = next;
        return ans;
    }

    public ListNode help(ListNode root){
        if(root.next == null){
            int val = root.val+exAdd;
            exAdd = val/10;//进位
            return new ListNode(val%10);
        }

        ListNode tmpNext = help(root.next);
        int val = root.val+exAdd;
        exAdd = val/10;//进位
        ListNode ans = new ListNode(val%10);
        ans.next = tmpNext;
        return ans;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = 0,m = 0;
        ListNode tmp1 = l1,tmp2 = l2;
        while(tmp1 != null){
            n++;
            tmp1 = tmp1.next;
        }
       // System.out.println(n);
        while(tmp2 != null){
            m++;
            tmp2 = tmp2.next;
        }

        if(n == m){//两个链表长度相同说明可以直接相加
            ListNode tmp = getAns(l1,l2);
            if(exAdd > 0){
                ListNode ans = new ListNode(exAdd);
                ans.next = tmp;
                return ans;
            }else
                return tmp;
        }
        if(n<m){//保证l1是长链表
            ListNode tmp = l2;
            l2 = l1;
            l1 = tmp;
            int tm = n;
            n = m;
            m = tm;
        }

        int cnt = n-m-1;
        ListNode tmp = l1;
        while(cnt > 0){
            tmp = tmp.next;
            cnt--;
        }
        ListNode next = getAns(tmp.next,l2);//得到相同位数上的结果
        tmp.next = null;//得到前半部分的链表
        ListNode ans = help(l1);
        ListNode node = ans;
        while(node.next != null){
            node = node.next;
        }
        node.next = next;
        if(exAdd > 0){
            ListNode ll = new ListNode(exAdd);
            ll.next = ans;
            return ll;
        }
            
        return ans;

    }
}
```



### LeetCode 887：鸡蛋掉落