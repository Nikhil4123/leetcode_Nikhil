class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stk = new Stack<>();

        for (int a : asteroids) {
            boolean destroyed = false;
            while (!stk.isEmpty() && a < 0 && stk.peek() > 0) {
                if (Math.abs(a) > stk.peek()) {
                    stk.pop(); // Destroy the top asteroid
                } else if (Math.abs(a) == stk.peek()) {
                    stk.pop(); // Both destroy each other
                    destroyed = true;
                    break;
                } else {
                    destroyed = true; // Current one is destroyed
                    break;
                }
            }
            if (!destroyed) {
                stk.push(a);
            }
        }

        // Convert stack to array
        int[] result = new int[stk.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stk.pop();
        }
        return result;
    }
}
