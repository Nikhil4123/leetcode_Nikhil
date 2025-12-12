class Solution {

    private void processMessageIds(String s, int[] mentions) {
        for(String part : s.split(" ")) {
            if(part.startsWith("id")) {
                int id = Integer.parseInt(part.substring(2));
                mentions[id]++;
            }
        }
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];
        Map<Integer, Integer> timeStampToHERECount = new HashMap<> ();
        int countOfHERE = 0;
        int countOfALL = 0;
        

        // processing MESSAGE events
        for(List<String> event : events) {
            if("MESSAGE".equals(event.get(0))) {
                if("ALL".equals(event.get(2))) countOfALL++;
                else if("HERE".equals(event.get(2))) {
                    countOfHERE++;
                    int timeStamp = Integer.parseInt(event.get(1));
                    // System.out.printf("%d\n", timeStamp);
                    timeStampToHERECount.put(timeStamp, timeStampToHERECount.getOrDefault(timeStamp, 0) + 1);
                } else {
                    String temp = event.get(2);
                    processMessageIds(temp, mentions);
                }
            } 
        }

        // process offline
        for(List<String> event : events) {
            if("OFFLINE".equals(event.get(0))) {
                int id = Integer.parseInt(event.get(2));
                int timeStamp = Integer.parseInt(event.get(1));
                for(int i=timeStamp;i<timeStamp+60;i++) {
                    if(timeStampToHERECount.containsKey(i)) {
                        mentions[id] -= timeStampToHERECount.get(i);
                        // System.out.printf("%d\n", i);
                    }
                }
            }
        }

        for(int i=0;i<numberOfUsers; i++) {
            mentions[i] += countOfALL;
            mentions[i] += countOfHERE;
        }

        return mentions;

    }
}