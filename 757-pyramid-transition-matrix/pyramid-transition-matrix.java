import java.util.*;

class Solution {

    // Map to store allowed transitions
    // Key: 2-character string
    // Value: list of possible top characters
    Map<String, List<Character>> map = new HashMap<>();

    boolean dfs(String bottom, int index, StringBuilder temp) {

        // Base case: pyramid completed
        if (bottom.length() == 1) {
            return true;
        }

        // Finished building next row
        if (index == bottom.length() - 1) {
            return dfs(temp.toString(), 0, new StringBuilder());
        }

        // Take current pair
        String key = bottom.substring(index, index + 2);

        // If no transition exists, stop
        if (!map.containsKey(key)) {
            return false;
        }

        // Try all possible characters
        for (char ch : map.get(key)) {
            temp.append(ch);                 // choose
            if (dfs(bottom, index + 1, temp)) {
                return true;                 // success
            }
            temp.deleteCharAt(temp.length() - 1); // backtrack
        }

        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {

        // Build mapping
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char value = s.charAt(2);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        return dfs(bottom, 0, new StringBuilder());
    }
}
