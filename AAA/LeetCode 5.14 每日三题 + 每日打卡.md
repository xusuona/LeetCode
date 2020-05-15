## LeetCode 5.14 每日三题 + 每日打卡

### LeetCode 136：只出现一次的数字 每日打卡

#### 题目

[https://leetcode-cn.com/problems/single-number/](https://leetcode-cn.com/problems/single-number/)

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

#### 思路

异或

#### 代码

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for(int i = 1;i < nums.length;i++){
            ans = ans ^ nums[i];
        }
        return ans;
    }
}
```



### LeetCode 501：二叉搜索树中的众数

#### 题目

[https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/submissions/](https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/submissions/)

给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

#### 思路

中序遍历，先左子树，在本身，在右子树，因为   left <= now <= right 

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
    int max = 0;
    List<Integer> modes = new LinkedList<>();
    int currentValue;
    int count;

    public int[] findMode(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        currentValue = root.val;
        count = 0;

        helper(root);
        int[] result = new int[modes.size()];
        for (int i=0;i<modes.size();i++){
            result[i] = modes.get(i);
        }
        return result;
    }

    public void helper(TreeNode root){
        if (root == null){
            return;
        }
        helper(root.left);
        if (currentValue == root.val){
            count++;
        }else if(currentValue!=root.val){
            count=1;
            currentValue = root.val;
        }

        if (count==max){
            modes.add(currentValue);
        }else if (count>max){
            max = count;
            modes.clear();
            modes.add(currentValue);
        }

        helper(root.right);

    }
}

```



### LeetCode 94：二叉树的中序遍历

#### 题目

[https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode/](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode/)

#### 思路

使用辅助栈

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
```



### LeetCode 64：有效数字









