class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char ans='{';
        for(char ch:letters){
            if(ch>target &&ch<ans){
                ans=ch;
            }
        }
        return ans=='{'?letters[0]:ans;
    }
}