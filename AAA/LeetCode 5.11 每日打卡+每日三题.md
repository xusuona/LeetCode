## LeetCode 5.11 每日打卡+每日三题

### LeetCode 50：pow(n,x) 每日打卡

#### 题目

[https://leetcode-cn.com/problems/powx-n/](https://leetcode-cn.com/problems/powx-n/)

实现 [pow(*x*, *n*)](https://www.cplusplus.com/reference/valarray/pow/) ，即计算 x 的 n 次幂函数。

#### 思路

矩阵快速幂方法

#### 代码

```java
class Solution {

    public double help(double x,long n){
        double ans = 1.0;
        double tmp = x;

        while(n > 0){
            if(n % 2 == 1){
                ans *= tmp;
            }
            tmp *= tmp;
            n  /= 2;
        }
        return ans;
    }


    public double myPow(double x, int n) {
        long N = n;
        return N>0?help(x,N):1.0/help(x,-N);
    }
}
```



### LeetCode 21：合并两个有序链表

#### 题目

[https://leetcode-cn.com/problems/merge-two-sorted-lists/](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

#### 思路

按照思路

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2==null)
        return l1==null?l2:l1;
        ListNode head = null;
        ListNode t1 = l1;
        ListNode t2 = l2;
        if(t1.val < t2.val){
            head = t1;
            t1 = t1.next;
        }else{
            head = t2;
            t2 = t2.next;
        }
        ListNode ans = head;
        while(t1 != null && t2 != null){
            if(t1 != null  && t1.val < t2.val){
                ans.next = t1;
                t1 = t1.next;
                ans = ans.next;
            }else if(t2!=null){
                ans.next = t2;
                t2 = t2.next;
                ans = ans.next;
            }
        } 
        while(t1 != null){
            ans.next = t1;
            t1 = t1.next;
            ans = ans.next;
        }
        while(t2 != null){
            ans.next = t2;
            t2 = t2.next;
            ans = ans.next;
        }
        return head;
    }
}
```



### LeetCode 148：排序链表

#### 题目

[https://leetcode-cn.com/problems/sort-list/](https://leetcode-cn.com/problems/sort-list/)

在 *O*(*n* log *n*) 时间复杂度和常数级空间复杂度下，对链表进行排序。

#### 思路

归并排序的思路

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
   public ListNode help(ListNode pre,int num){
        int aNum = 0,bNum = 0;
        ListNode a = pre.next,b = a;
        for(int i = 0;i < num;i++){//进行划分，一个指针先执行num长度，然后比较合并
            if(b== null)
                return null;
            b = b.next;
        }
        while(aNum < num && bNum < num && b!= null){//因为b指针是后面指针，停止条件是当b执行到链表的末尾
            if(a.val>=b.val){
                pre.next = b;
                bNum++;
                b = b.next;
            }else{
                pre.next = a;
                aNum++;
                a = a.next;
            }
            pre = pre.next;
        }
        
        if(aNum < num){
            pre.next = a;
            for(;aNum < num;aNum++)
                pre = pre.next;
            pre.next = b;
        }
        if(bNum < num && b!=null){
            pre.next = b;
            for(;bNum < num && pre!=null;bNum++)
                pre = pre.next;
        }
        return pre;
    }
    
    public ListNode sortList(ListNode head) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;
        for(int len = 1;(pre = help(pre,len)) != null;len *=2){
            while(pre != null){
                pre = help(pre,len);
            }
            pre = preHead;
        }
        return preHead.next;
    }
}
```



#### 