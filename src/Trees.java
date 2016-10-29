import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuanwang on 10/28/16.
 */
public class Trees {

    /*
    The time complexity for the problem should be O(n), since we are basically visiting each node
    in the tree. Yet an interviewer might ask you for further optimization when he or she saw a
    string concatenation. A string concatenation is just too costly. A StringBuilder can be used
    although a bit tricky since it is not immutable like string is.

    When using StringBuilder, We can just keep track of the length of the StringBuilder
    before we append anything to it before recursion and afterwards set the length back.
    Another trick is when to append the "->",
    since we don't need the last arrow at the end of the string, we only append it before
    recurse to the next level of the tree.

     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(res, root.left, sb);
            helper(res, root.right, sb);
        }
        sb.setLength(len);
    }

    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int maxDepthIterative(TreeNode root) {
        if (root == null)
            return 0;

        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size-- > 0) {
                TreeNode cur = stack.pop();
                if (cur.left != null)
                    stack.addLast(cur.left);
                if (cur.right != null)
                    stack.addLast(cur.right);
            }
            count++;

        }
        return count;

    }

    public boolean isValidBST(TreeNode root) {
        //return helper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return isValidBSTIterative(root);
    }

    private boolean helper(TreeNode root, long max, long min){
        if(root == null) return true;
        if(root.val<min) return false;
        if(root.val>max) return false;

        return helper(root.left, (long)root.val-1, min) &&
                helper(root.right, max, (long)root.val+1);
    }

    public boolean isValidBSTIterative(TreeNode root) {
        LinkedList<TreeNode> s = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while(!s.isEmpty() || cur != null){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            TreeNode p = s.pop();
            if(pre != null && pre.val >= p.val) return false;
            pre = p;
            cur = p.right;
        }
        return true;
    }

    private static ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }
    //flatten tree to linkedlist inorder
}
