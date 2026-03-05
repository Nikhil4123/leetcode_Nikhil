class Solution {
    public int minOperations(String s) {
        int p1 = 0;
        int p2 = 0;

        for(int i = 0; i < s.length(); i++){
            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';

            if(s.charAt(i) != expected1) p1++;
            if(s.charAt(i) != expected2) p2++;
        }

        return Math.min(p1, p2);
    }
}