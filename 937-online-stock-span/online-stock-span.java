// Java
import java.util.Stack;

class StockSpanner {
    private Stack<int[]> stack;

    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1]; // Add popped span
        }
        stack.push(new int[]{price, span}); // Store price and span
        return span;
    }
}