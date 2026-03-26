class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ws = 0 , we = 0 , ml = Integer.MIN_VALUE;
        int[] freq = new int[128];
        int n=s.length();

        while(we<n){
            char ch = s.charAt(we);
            freq[ch]++;
            while(freq[ch]>1){
                freq[s.charAt(ws)]--;
                ws++;
            }
            ml=Math.max(ml,we-ws+1);
            we++;
        }
        return (ml==Integer.MIN_VALUE?0:ml);
    }
}