import java.util.*;

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

    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        if(T2 == null)
            return true;
        else if(T1 == null)
            return false;
        else return isSameTree(T1, T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
    }

    private boolean isSameTree(TreeNode T1, TreeNode T2) {
        if(T1 == null && T2 == null)
            return true;
        if(T1 == null || T2 == null)
            return false;
        if(T1.val != T2.val)
            return false;
        return isSameTree(T1.left,T2.left) && isSameTree(T1.right, T2.right);
    }

    public static int kthSmallest(TreeNode root, int k) {
        int i = 0;
        TreeNode p = root;
        LinkedList<TreeNode> s = new LinkedList<>();
        while(p != null || ! s.isEmpty()){
            while(p != null){
                s.push(p);
                p = p.left;
            }
            p = s.pop();
            if(++i == k){
                return p.val;
            }
            p = p.right;
        }
        return 0;
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

    private static ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }

    public boolean validBST(TreeNode root) {
        //return helper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return validBSTIterative(root);
    }

    private boolean helper(TreeNode root, long max, long min){
        if(root == null) return true;
        if(root.val<min) return false;
        if(root.val>max) return false;

        return helper(root.left, (long)root.val-1, min) &&
                helper(root.right, max, (long)root.val+1);
    }

    public boolean validBSTIterative(TreeNode root) {
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

    public static ListNode bTreeToLinkedList(TreeNode root) {
        //flatten tree to linkedlist inorder
        ListNode list1 = root.left != null ? bTreeToLinkedList(root.left) : null;
        ListNode list2 = root.right != null ? bTreeToLinkedList(root.right) : null;
        ListNode list3 = new ListNode( root.val);

        list3.next = list2;
        if (list1 == null)
            return list3;

        ListNode last = list1;
        while (last.next != null)
            last = last.next;
        last.next = list3;

        return list1;
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        //use a HashMap to store the TreeNode and the according cloumn value
        HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();
        queue.offer(root);
        weight.put(root, 0);
        int min = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int w = weight.get(node);
            if (!map.containsKey(w)) {
                map.put(w, new ArrayList<>());
            }
            map.get(w).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                weight.put(node.left, w - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                weight.put(node.right, w + 1);
            }
            //update min ,min means the minimum column value, which is the left most node
            min = Math.min(min, w);
        }
        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }
        return res;
    }

}
