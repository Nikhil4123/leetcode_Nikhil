import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();

        String[] map = {
            "", "", "abc", "def", "ghi", "jkl", 
            "mno", "pqrs", "tuv", "wxyz"
        };

        Queue<String> queue = new LinkedList<>();
        queue.add("");

        for (char d : digits.toCharArray()) {
            int size = queue.size();
            String letters = map[d - '0'];

            for (int i = 0; i < size; i++) {
                String prefix = queue.poll();
                for (char c : letters.toCharArray()) {
                    queue.add(prefix + c);
                }
            }
        }

        return new ArrayList<>(queue);
    }
}
