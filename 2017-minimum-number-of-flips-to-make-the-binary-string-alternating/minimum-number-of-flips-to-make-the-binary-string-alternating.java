class Solution {
    public int minFlips(String s) {
        int n = s.length();
        // appending the string.
        s = s+s;
        // check for both strings - start with '0' and start with '1'
        return Math.min(fun(s,'0',n),fun(s,'1',n));
    }

    public int fun(String s, char ch, int len){
        int[] arr = new int[2*len];
        // prefix sum 
        for(int i=0;i<(2*len);i++){
            if(i>0){
                arr[i]=arr[i-1];
            }
            if(s.charAt(i)!=ch){
                arr[i]+=1;
            }
            ch = ch=='1' ? '0' : '1';
        }
        // sliding window
        int start=0, end=len; 
        int ans = arr[end-1];
        while(end<(2*len)){
            ans = Math.min(ans, arr[end++]-arr[start++]);
        }
        return ans;
    }
}