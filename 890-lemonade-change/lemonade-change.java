class Solution {
    public boolean lemonadeChange(int[] bills) {
        int c1 = 0, c2 = 0; // c1 = count of $5, c2 = count of $10
        
        for (int bill : bills) {
            if (bill == 5) {
                c1++;
            } 
            else if (bill == 10) {
                if (c1 == 0) return false; // need one $5 for change
                c1--;
                c2++;
            } 
            else { // bill == 20
                if (c2 > 0 && c1 > 0) { 
                    c2--; 
                    c1--; // give one $10 and one $5
                } 
                else if (c1 >= 3) { 
                    c1 -= 3; // give three $5
                } 
                else { 
                    return false; 
                }
            }
        }
        return true;
    }
}
