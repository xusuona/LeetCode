## LeetCode 5.13 每日打卡 + 每日三题

### LeetCode 102 ：二叉树的层序遍历

#### 题目

[https://leetcode-cn.com/problems/binary-tree-level-order-traversal/](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

#### 思路

使用cnt变量记录层数，相同层数的放入同一个数组中

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

    public void help(List<List<Integer>> ans,int cnt,TreeNode node){
        if(node == null)
            return;
        if(ans.size() == cnt){
            ans.add(cnt,new ArrayList<Integer>());
        }
        ans.get(cnt).add(node.val);
        help(ans,cnt+1,node.left);
        help(ans,cnt+1,node.right);
        return;    
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        help(ans,0,root);
        return ans;   
    }
}
```



### LeetCode 1360：日期之间间隔几天

#### 题目

[https://leetcode-cn.com/problems/number-of-days-between-two-dates/submissions/](https://leetcode-cn.com/problems/number-of-days-between-two-dates/submissions/)

请你编写一个程序来计算两个日期之间隔了多少天。

日期以字符串形式给出，格式为 `YYYY-MM-DD`，如示例所示。

**提示：**

- 给定的日期是 `1971` 年到 `2100` 年之间的有效日期。

#### 思路

分别计算出每个日期距离1971年多少天，然后做差

#### 代码

```java
class Solution {
    public boolean isRY(int year){
        if(year % 400 == 0)//能被400整除
            return true;
        if(year % 100 == 0)
            return false;
        if(year % 4 == 0)
            return true;
        return false;
    }

    public int getDay(String date){
        int year = Integer.parseInt(date.substring(0,4));
        int mon = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));

        int begin = 1971;
        int ans = 0;

        for(int i = begin;i < year;i++){//年差
            if(isRY(i))
                ans += 366;
            else
                ans += 365;
        }

        boolean pos = false;
        //月差
        if(isRY(year))
            pos = true;
        
        for(int i = 1;i < mon;i++){
            if(i == 2){
                if(pos)
                    ans += 29;
                else
                    ans += 28;
            }else if( i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12){
                ans += 31;
            }else
                ans += 30;
        }

        return ans + day;

    }

    public int daysBetweenDates(String date1, String date2) {
        int a = getDay(date1),b = getDay(date2);
        return Math.abs(a-b);
    }
}
```



### LeetCode 93：还原IP地址

#### 题目

[https://leetcode-cn.com/problems/restore-ip-addresses/](https://leetcode-cn.com/problems/restore-ip-addresses/)

给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

#### 思路

深搜，找到每一种有效的分割方法

#### 代码

```java
class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        if(n<4 || n> 12)
            return ans;
        help(s,0,4,new StringBuffer());
        return ans; 
    }

    public void help(String s,int beg,int num,StringBuffer res){
        int n = s.length();
         if((n-beg) > num*3)
            return;
        if(num == 1){
            int tmp = Integer.parseInt(s.substring(beg,n));
            res.append(".");
            if(n-beg>1 && s.charAt(beg) == '0')
                return;
            if(tmp>=0&&tmp<=255){
                res.append(s.substring(beg,n));
                String str = new String(res.toString());
                ans.add(str);
            }
            return;
        }
        for(int i = beg+1;i < beg+4 && i<n;i++){
            int tmp = Integer.parseInt(s.substring(beg,i));
            if(tmp >= 0 && tmp <=255){
                if(i-beg>1 && s.charAt(beg) == '0')
                    continue;
                int l = res.length();
                if(beg!=0)
                    res.append(".");
                res.append(s.substring(beg,i));
                help(s,i,num-1,res);
                res.delete(l,res.length());
            }
        }
    } 
}
```



### LeetCode 1028：从先序遍历还原二叉树