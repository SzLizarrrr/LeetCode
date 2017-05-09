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
        System.out.println("left " + leftDepth);
        int rightDepth = maxDepth(root.right);
        System.out.println("right " + rightDepth);
        
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
        
    }
}