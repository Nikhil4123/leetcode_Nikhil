class Solution {
    public int maxArea(int[] height) {
        int l=0,r=height.length-1;
        int lmax=0,rmax=0,mw=0;
        while(l<r){
            int h=Math.min(height[l],height[r]);
            int w=r-l;
            int area=w*h;
            mw=Math.max(mw,area);
            if(height[l]<height[r]){
                l++;
            }else{
                r--;
            }
        }
        return mw;
    }
}