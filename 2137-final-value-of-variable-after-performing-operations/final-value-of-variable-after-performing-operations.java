class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int a = 0;
        for (String c : operations) {
            if (c.contains("++")) a++;
            else a--;
        }
        return a;
    }
}