/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**21. Merge Two Sorted Lists */
    //My answer is not best answer, check discusss, discuss has two very interesting answer
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode result = null;
        if(l1 == null && l2 == null) return result;
        if(l1 == null && l2 != null) {
            result = l2;
            return result;
        }
        if(l1 != null && l2 == null) {
            result = l1;
            return result;
        }
        if(l1.val > l2.val){
            result = l2;
            result.next = mergeTwoLists(l1, l2.next);
        }
        else {
            result = l1;
            result.next = mergeTwoLists(l1.next, l2);
        }
        return result;
    }

    /**102. Binary Tree Level Order Traveral I */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> tmp = new LinkedList<TreeNode>();
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null) return result;

        tmp.offer(root);
        while(!tmp.isEmpty()){
            int size = tmp.size(); //very important!
            List<Integer> sublist = new LinkedList<Integer>();
            for(int i=0; i<size; i++){
                if(tmp.peek().left != null) tmp.offer(tmp.peek().left);
                if(tmp.peek().right != null) tmp.offer(tmp.peek().right);
                sublist.add(tmp.poll().val);
            }
            result.add(sublist);
        }
        return result;
    }

    /**107. Binary Tree Level Order Traveral II */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        
        addLevel(result, 0, root);
        
        return result;
    }
    
    public void addLevel(LinkedList<List<Integer>> result, int level, TreeNode point){
        if(point == null) return;
        if(result.size()-1 < level) result.addFirst(new LinkedList<Integer>());
        result.get(result.size()-1-level).add(point.val);
        
        addLevel(result, level+1, point.left);
        addLevel(result, level+1, point.right);
    }
    
    /**103. Binary Tree Zigzag Level Order Traversal */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
       LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
       int level = 0;

       resultHelpMethod(root, result, level);
       return result;
    }

    public void resultHelpMethod(TreeNode node, LinkedList<List<Integer>> result, int level){
        if(node == null) return;
        if(result.size()-1 < level) result.add(new LinkedList<Integer>());
        if(level%2 == 0) result.get(level).add(node.val);
        else result.get(level).add(0, node.val);

        resultHelpMethod(node.left, result, level+1);
        resultHelpMethod(node.right, result, level+1);
    }

    /**104. Maximum Depth of Binary Tree */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        int leftDepth = maxDepth(root.left);
        // System.out.println("left " + leftDepth);
        int rightDepth = maxDepth(root.right);
        // System.out.println("right " + rightDepth);
        
        return Math.max(leftDepth, rightDepth)+1;
    }

    /**105. Construct Binary Tree from Preoroder and Inorder Traversal */
    // LeetCode this question most popular answer's comment has a HashMap answer, pretty good
    public TreeNode buildTree(int[] preorder, int[] inorder){
        // return buildTree(preorder, inorder, 0, inorder.length);
        return buildTree(preorder, inorder, 0, 0, inorder.length);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int p, int min, int max){
        if(p >= preorder.length || min == max) return null;
        TreeNode node = new TreeNode(preorder[p]);
        int index = 0;
        for(int i=min; i<max; i++){
            // if(inorder[i] == preorder[p]) {
            if(inorder[i] == node.val) {
                // node.val = preorder[p];
                index = i;
                // break;
            }
        }

        node.left = buildTree(preorder, inorder, p+1, min, index);
        // node.right = buildTree(preorder, inorder, index+1, index+1, max);
        node.right = buildTree(preorder, inorder, p + index - min + 1, index+1, max);
        return node;
    }

    /**108. Convert Sorted Array to Binary Search Tree */
    public TreeNode sortedArrayToBST(int[] nums){
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int min, int max){
        if(min > max) return null;
        // TreeNode result = new TreeNode(nums[(max - min)/2]);
        // result.left = sortedArrayToBST(nums, min, (max - min)/2);
        // result.right = sortedArrayToBST(nums, (max - min)/2+1, max);
        // don't forget about this fool mistake.
        TreeNode result = new TreeNode(nums[(max + min)/2]);
        result.left = sortedArrayToBST(nums, min, (max + min)/2);
        result.right = sortedArrayToBST(nums, (max + min)/2+1, max);
        return result;
    }

    /**109. Convert Sorted List to Binary Search Tree */
    public TreeNode sortedListToBST(ListNode head){
        return sortedListToBST(head, fast, slow);
    }

    public TreeNode sortedListToBST(ListNode head, ListNode fast, ListNode slow){
        if(fast == slow) return null;
        
    }

    /**110. Balanced Binary Tree */
    public boolean isBalanced(TreeNode root){
         return high(root)!=-1;
    }

    public int high(TreeNode root){
        if(root == null) return 0;
        
        int left = high(root.left);
        if(left == -1) return -1;
        // System.out.println("left " + left);
        
        int right = high(root.right);
        if(right == -1) return -1;
        // System.out.println("right "+ right);
        
        // System.out.println(Math.abs(right-left));
        if (Math.abs(right-left)>1) return -1;
        
        return Math.max(right,left)+1;
    }

    /**94. Binary Tree Inorder Traversal */
    public List<Integer> inorderTraversalIterative(TreeNode root){
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> temp = new Stack<TreeNode>();
        TreeNode point = root;

        while(point!=null || !temp.empty()){
            while(point!=null){
                temp.add(point);
                point = point.left;
            }
            // OWN METHOD GET "Time Limit Exceeded" EXCEPTION, DON'T KNOW WHY
            // point = temp.firstElement().right;
            // result.add(temp.pop().val);
            point = temp.pop();
            result.add(point.val);
            point = point.right;
        }

        return result;
    }

    public List<Integer> inorderTraversalRecursive(TreeNode root){
        List<Integer> result = new ArrayList<Integer>();

        postResult(root, result);

        return result;
    }

    public void postResult(TreeNode point, List<Integer> result){
        if(point != null){
            if(point.left != null)
                postResult(point.left, result);
                
            result.add(point.val);

            if(point.right != null)
                postResult(point.right, result);
        }
    }
    
    /**98. Validate Binary Search Tree */
    // int max, min;
    // public boolean isValidBST(TreeNode root){
    //     // if(root != null) System.out.println(root.val);
    //     if(root == null) return true;
    //     if(root.right != null && root.val >= root.right.val){
    //         System.out.println("a");
    //         return false;
    //     }
    //     if(root.left != null && root.left.val >= root.val){
    //         System.out.println("b");
    //         return false;
    //     }
    //     isValidBST(root.left);
    //     isValidBST(root.right);
    //     return true;
    // }

    public boolean isValidBST(TreeNode root){
        // return isValidBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE); using Integer will be failed on number 2147483647
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    // public boolean isValidBST(TreeNode node, Integer max, Integer min){
    public boolean isValidBST(TreeNode node, long max, long min){
        if(node == null) return true;
        if(node.val >= max || node.val <= min) return false;
        // max = node.val; isValidBST(node.left, max, min);
        // min = nood.val; isValidBST(node.right, max, min);
        return isValidBST(node.left, node.val, min) && isValidBST(node.right, max, node.val);
    }
    
    /**100. Same Tree */
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null && q != null) return false;
        if(p != null && q == null) return false;
        // last two line can merge as this
        // if(p == null || q == null) return false;
        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    /**101. Symmetric Tree */
    public boolean isSymmetric(TreeNode root){
        if(root == null) return true; // very easy to miss
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode leftNode, TreeNode rightNode){
        if(leftNode == null && rightNode == null) return true;
        if(leftNode == null || rightNode == null) return false;
        if(leftNode.val != rightNode.val) return false;

        return isSymmetric(leftNode.left, rightNode.right) && isSymmetric(leftNode.right, rightNode.left);
    }
}
