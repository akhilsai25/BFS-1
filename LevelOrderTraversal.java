/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 // This uses a queue where at every layer we add the child nodes of the current nodes parked in the queue. Starting with the root node, we add it to the queue and then iteratively pick the child nodes and add them. Catch is at every stage we just take snapshot of the size of the queue to prevent iterating the upcoming children which are saved for next level.
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderedList = new ArrayList();
        if(root==null) return levelOrderedList;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList();
            for(int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            levelOrderedList.add(list);
        }
        return levelOrderedList;
    }
}
