class Solution {
    public boolean checkValidString(String s) {
        int h=0,l=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                l++;
                h++;
            }else if(ch==')'){
                if(l>0) l--;
                h--;
            }else {
                if(l>0)l--;
                h++;
            }
            if(h<0) return false;
        }
        return l==0;
    }
}

