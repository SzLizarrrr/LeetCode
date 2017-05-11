/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class TreeSolution {
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

    /**104. Maximum Depth of Binary Tree */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        
        int leftDepth = maxDepth(root.left);
        // System.out.println("left " + leftDepth);
        int rightDepth = maxDepth(root.right);
        // System.out.println("right " + rightDepth);
        
        return Math.max(leftDepth, rightDepth)+1;
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
