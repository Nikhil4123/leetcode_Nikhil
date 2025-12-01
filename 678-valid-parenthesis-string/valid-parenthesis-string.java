class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                if (low > 0) low--;
                high--;
            } else {
                if (low > 0) low--;
                high++;
            }
            if (high < 0) return false;
        }
        return low == 0;
    }
}
