class Solution {
    public String sortVowels(String s) {
        Set<Character> vowelsSet = Set.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');
        List<Character> vowels = new ArrayList<>();

        for (char c : s.toCharArray())
            if (vowelsSet.contains(c))
                vowels.add(c);

        Collections.sort(vowels);

        StringBuilder result = new StringBuilder();
        int index = 0;

        for (char c : s.toCharArray()) {
            if (vowelsSet.contains(c))
                result.append(vowels.get(index++));
            else
                result.append(c);
        }

        return result.toString();
    }
}