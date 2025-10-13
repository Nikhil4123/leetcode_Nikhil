class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> rs = new ArrayList<>();
        if (words.length > 0) {
            rs.add(words[0]);
        }

        for (int i = 1; i < words.length; i++) {
            if (!isAnagrams(words[i - 1], words[i])) {
                rs.add(words[i]);
            }
        }
        return rs;
    }

    private boolean isAnagrams(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    int[] freq = new int[26];
    for (int i = 0; i < s1.length(); i++) {
        freq[s1.charAt(i) - 'a']++;
        freq[s2.charAt(i) - 'a']--;
    }
    for (int count : freq) {
        if (count != 0) return false;
    }
    return true;
}


    // public boolean isAnagrams(String s1, String s2) {

    //     if (s1.length() != s2.length())
    //         return false;

    //     HashMap<Character, Integer> h1 = new HashMap<>();
    //     HashMap<Character, Integer> h2 = new HashMap<>();
    //     for (int i = 0; i < s1.length(); i++) {
    //         h1.put(s1.charAt(i), h1.getOrDefault(s1.charAt(i), 0) + 1);
    //     }
    //     for (int i = 0; i < s2.length(); i++) {
    //         h2.put(s2.charAt(i), h2.getOrDefault(s2.charAt(i), 0) + 1);
    //     }
    //     return h1.equals(h2);
    // }
}