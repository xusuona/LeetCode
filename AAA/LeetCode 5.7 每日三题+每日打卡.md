## LeetCode 5.7 每日三题+每日打卡

### 每日打卡: LeetCode 572：另一个树的子树

#### 题目

[https://leetcode-cn.com/problems/subtree-of-another-tree/](https://leetcode-cn.com/problems/subtree-of-another-tree/)

给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

#### 思路

弱鸡的我只会暴力匹配，先使用宽搜找到相等的节点，然后进入完全匹配，相同返回true，遍历结束后没有相同就返回false。

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
    public boolean dfs(TreeNode s,TreeNode t){
        if(s == null && t == null)
            return true;
        if(s == null || t == null)
            return false;
        if(s.val != t.val)
            return false;
        return dfs(s.left,t.left) && dfs(s.right,t.right);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(s);

        while(!qu.isEmpty()){
            TreeNode tmp = qu.poll();
            if(tmp.val == t.val){
                if(dfs(tmp,t))
                    return true;
            }
            if(tmp.left != null)
                qu.offer(tmp.left);
            if(tmp.right != null)
                qu.offer(tmp.right);
        }
        return false;
    }
}
```

### LeetCode 7：整数反转

#### 题目

[https://leetcode-cn.com/problems/reverse-integer/](https://leetcode-cn.com/problems/reverse-integer/)

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

#### 思路

加一次特判，

> ​	if 正数：
>
> ​		当前数字  >  Integer.MAX_VALUE/10 或者 （当前数字 ==  Integer.MAX_VALUE/10 且 加数 > 7）
>
> ​			会溢出，返回0
>
> ​	if 负数：
>
> ​		当前数字  >  Integer.MIN_VALUE/10 或者 （当前数字 ==  Integer.MIN_VALUE/10 且 加数 > 8）
>
> ​			会溢出，返回0
>
> 

#### 代码

```java
class Solution {
    public int reverse(int x) {
        boolean pos = true;
        if(x < 0){
            pos = false;
        }
        int ans = 0;
        while(x != 0){
            int tmp = x%10; 
            if(pos){
                if((ans == Integer.MAX_VALUE/10 && tmp > 7) || (ans > Integer.MAX_VALUE/10)){
                  return 0;
                }
            }else{
                if((ans == Integer.MIN_VALUE/10 && tmp > 8) || (ans < Integer.MIN_VALUE/10))
                    return 0;
            }
            ans = ans*10+tmp;
            x /= 10;
        }
        return ans;
    }
}
```



### LeetCode 98：验证二叉搜索树

#### 题目

[https://leetcode-cn.com/problems/validate-binary-search-tree/](https://leetcode-cn.com/problems/validate-binary-search-tree/)

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

- 节点的左子树只包含小于当前节点的数。

- 节点的右子树只包含大于当前节点的数。
- 所有左子树和右子树自身必须也是二叉搜索树。

#### 思路

递归实现，不满足条件就返回false

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

    public boolean dfs(TreeNode root,Integer left,Integer right){
        if(root == null)
            return true;
        if(left != null && root.val <= left)//不满足左边条件
            return false;
        if(right != null && root.val >= right)//不满足右边条件
            return false;
        return dfs(root.left,left,root.val) && dfs(root.right,root.val,right);
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root,null,null);
    }
}
```



### LeetCode 41： 缺失的第一个正数

#### 题目



[https://leetcode-cn.com/problems/first-missing-positive/](https://leetcode-cn.com/problems/first-missing-positive/)

给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

**提示：**

你的算法的时间复杂度应为O(*n*)，并且只能使用常数级别的额外空间。

#### 思路

使用自身数组作为哈希表，位置为i的下标位置存放i+1的数字，遍历两次，

- 第一次遍历，让 **num = nums[i]**,如果 **num > 0 && num <= n && i+1  != num && nums[num-1] != num **,则进行交换，将数组交换到对应下标出
- 交换之后仍需要继续检查是否需要交换
- 不需要交换，递增i
- 第二次遍历需要 判断当前位置下标是否符合
- 最后说明没有缺失的数字，返回数组长度加一

#### 代码

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int j = 0, n = nums.length;
        if(n == 0)
            return 1;
        while(j < n){
            int i = nums[j];
            if(i>0 && i<=n && j+1 != i && nums[i-1] != i){
                int tmp  =nums[i-1];
                nums[i-1] = nums[j];
                nums[j] = tmp;
                continue;
            }
             j += 1;
        }

        for(int k = 0;k < n;k++){
            if(nums[k] != k+1)
                return k+1;
        }
        if (nums[0]==nums.length)return nums.length+1;
        return nums.length+1;
    }
}
```

