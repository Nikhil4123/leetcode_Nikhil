class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stk = new Stack<>();
        for(char c : num.toCharArray()){
            while(k>0 && !stk.isEmpty() && stk.peek()>c){
                stk.pop();
                k--;
            }
            stk.push(c);
        }
        while(k>0 && !stk.isEmpty()){
            stk.pop();
            k--;
        }

        StringBuilder sb=new StringBuilder();
        for(char sk : stk){
            sb.append(sk);
        }
        
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}