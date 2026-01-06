class Solution {

    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int level = 1;
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        while (!q.isEmpty()) {

            int size = q.size();   // number of nodes in this level
            int sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;

                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }

            level++;
        }

        return maxLevel;
    }
}
