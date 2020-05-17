## LeetCode 5.15 每日三题 + 每日打卡

### LeetCode 560：和为K的子数组

#### 题目

[https://leetcode-cn.com/problems/subarray-sum-equals-k/](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

给定一个整数数组和一个整数 **k，**你需要找到该数组中和为 **k** 的连续的子数组的个数。

#### 思路

参考官方题解，采用hashMap存放累计和和数量，**如果 j 到 i 之间的和为K，pre[i] 代表 0到i之间的总和，那么一定存在 pre[i] = pre[j-1]+k**

#### 代码

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int cnt = 0;//存放答案
        int pre = 0;//存放累计的和
        for(int i = 0;i < nums.length;i++){
            pre += nums[i];
            if(map.containsKey(pre-k)){//如果之前存在和为pre-k的和，说明可以有一段区域满足和为K
                cnt += map.get(pre-k);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }


        return cnt;
    }
}
```



### LeetCode 617：合并二叉树

#### 题目

[https://leetcode-cn.com/problems/merge-two-binary-trees/](https://leetcode-cn.com/problems/merge-two-binary-trees/)

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

#### 思路

深度优先搜索



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

    public TreeNode dfs(TreeNode t1,TreeNode t2){
        if(t1 == null && t2 == null)
            return null;
        if(t1 == null){
            return t2;
        }
        if(t2 == null)
            return t1;
        
        TreeNode ans = new TreeNode(t1.val+t2.val);
        ans.left = dfs(t1.left,t2.left);
        ans.right = dfs(t1.right,t2.right);
        return ans;
    }


    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;
        if(t2 == null)
            return t1;
        
        return dfs(t1,t2);
    }
}
```



### LeetCode 102：二叉树的层序遍历

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



### LeetCode 145：二叉树的后序遍历

