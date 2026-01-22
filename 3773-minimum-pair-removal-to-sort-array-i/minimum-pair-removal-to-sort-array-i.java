class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) list.add(n);
        
        int operations = 0;
        while (!isSorted(list)) {
            int minSum = Integer.MAX_VALUE;
            int index = -1;
            
            // Find leftmost pair with minimum sum
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    index = i;
                }
            }
            
            // Replace the pair with their sum
            int sum = list.get(index) + list.get(index + 1);
            list.set(index, sum);
            list.remove(index + 1);
            operations++;
        }
        return operations;
    }

    private boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) return false;
        }
        return true;
    }
}