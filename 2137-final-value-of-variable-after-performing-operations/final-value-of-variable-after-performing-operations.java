class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int a=0;
        for(int i=0;i<operations.length;i++){
            String c=operations[i];
            if(c.equals("++X")||c.equals("X++")){
                a++;
            }else if(c.equals("--X")||c.equals("X--")){
                a--;
            }
        }
        return a;
    }
}