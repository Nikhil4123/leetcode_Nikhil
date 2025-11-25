class Solution {
    public int smallestRepunitDivByK(int k) {
        long n=1;
        int l=1;
        if(k==1) return 1;
        if(k%2==0 || k%5==0) return -1;
        while(n%k!=0){
            n=(n*10+1)%k;
            l++;
        }
        return l;
    }
}