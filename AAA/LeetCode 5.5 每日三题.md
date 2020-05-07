## LeetCode 5.5 每日三题

### LeetCode 824：山羊拉丁文

#### 思路

使用split（）方法得到字符串数组，然后就暴力就行

#### 代码

```java
class Solution {
    public String toGoatLatin(String S) {
        String[] str = S.split(" ");
        char[] ch = new char[]{'a','e','i','o','u','A','E','I','O','U'};
        StringBuffer ans = new StringBuffer();
        StringBuffer app = new StringBuffer("a");
        for(int i = 0;i < str.length;i++){
            if(i>0)
                ans.append(" ");
            String tmp = str[i];
            char ch1 = tmp.charAt(0);
            boolean tag = true;
            for(int j = 0;j < 10;j++){
                if(ch1 == ch[j]){
                    tag = false;
                    ans.append(tmp);
                    ans.append("ma");
                    ans.append(app);
                    app.append("a");
                }
            }
            if(tag){
                ans.append(tmp.substring(1,tmp.length()));
                ans.append(tmp.substring(0,1));
                ans.append("ma");
                ans.append(app);
                app.append("a");
            }
        }
        return ans.toString();
    }
}
```

### LeetCode 2 ： 链表相加

#### 思路

正常的加法思路，采用一个进位标志服

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        int w = 0;
        ListNode tmp = ans;
        while (l1 != null || l2 != null){
            int a = l1 == null?0:l1.val;
            int b = l2 == null?0:l2.val;
            tmp.next = new ListNode((a+b+w)%10);
            tmp = tmp.next;
            w = (a+b+w)/10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if(w > 0 ){
            tmp.next = new ListNode(w);
        }
        return ans.next;
    }
}
```

### LeetCode 23：合并K个有序链表

#### 思路

采用分治法的思想，两两合并，递归出口是只有一个链表的时候

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


    public ListNode Merage(ListNode l1,ListNode l2){
        ListNode ans = new ListNode(0);
        ListNode tmp = ans;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            }else{
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }

        if(l1 != null)
            tmp.next = l1;
        if(l2 != null)
            tmp.next = l2;
        return ans.next;
    }


    public ListNode help(ListNode[] lists,int left,int right){
        if(left == right)
            return lists[left];
        int mid = left+(right-left)/2;

        ListNode l1 = help(lists,left,mid);
        ListNode l2 = help(lists,mid+1,right);

        return Merage(l1,l2);
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length <= 0)
            return null;
        return help(lists,0,lists.length-1);
    }
}
```