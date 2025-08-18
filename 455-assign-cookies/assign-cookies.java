class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int l=0,r=0;
        int n=g.length, m=s.length;
        while(l<m){
            if(r < n && g[r]<=s[l]){
                r++;
            }
            l++;
        }
        return r;
    }
}