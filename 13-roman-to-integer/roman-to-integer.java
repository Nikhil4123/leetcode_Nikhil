class Solution {
    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int value = 0;

            switch (ch) {
                case 'I': value = 1; break;
                case 'V': value = 5; break;
                case 'X': value = 10; break;
                case 'L': value = 50; break;
                case 'C': value = 100; break;
                case 'D': value = 500; break;
                case 'M': value = 1000; break;
            }

            // check next character for subtraction rule
            if (i + 1 < s.length()) {
                char nextCh = s.charAt(i + 1);
                int nextVal = 0;

                switch (nextCh) {
                    case 'I': nextVal = 1; break;
                    case 'V': nextVal = 5; break;
                    case 'X': nextVal = 10; break;
                    case 'L': nextVal = 50; break;
                    case 'C': nextVal = 100; break;
                    case 'D': nextVal = 500; break;
                    case 'M': nextVal = 1000; break;
                }

                if (value < nextVal) {
                    ans -= value; // subtract if smaller before bigger
                    continue;
                }
            }
            ans += value;
        }
        return ans;
    }
}
