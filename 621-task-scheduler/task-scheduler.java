class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char c : tasks)
            count[c - 'A']++;
        
        Arrays.sort(count);
        int maxFreq = count[25];
        int maxCount = 0;
        
        for (int i = 25; i >= 0 && count[i] == maxFreq; i--)
            maxCount++;
        
        int partCount = maxFreq - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - maxFreq * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);
        
        return tasks.length + idles;
    }
}
