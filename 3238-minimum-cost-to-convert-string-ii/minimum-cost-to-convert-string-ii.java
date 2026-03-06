class Solution {
    class TrieNode {
        TrieNode[] children;
        int index; 
        
        TrieNode() {
            this.children = new TrieNode[26];
            this.index = -1;
        }
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        long INF = Long.MAX_VALUE / 2; 
        int n = source.length();
        
        Set<String> uniq_str = new HashSet<>();
        for (int i = 0; i < original.length; i++) {
            uniq_str.add(original[i]);
            uniq_str.add(changed[i]);
        }

        for (int i = 0; i < n; i++) {
            uniq_str.add(String.valueOf(source.charAt(i)));
            uniq_str.add(String.valueOf(target.charAt(i)));
        }

        List<String> strings = new ArrayList<>(uniq_str);
        int m = strings.size();

        Map<String, Integer> index_map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            index_map.put(strings.get(i), i);
        }

        long[][] dist = new long[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int u = index_map.get(original[i]);
            int v = index_map.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], (long)cost[i]);
        }

        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                if (dist[i][k] == INF) continue;

                for (int j = 0; j < m; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        TrieNode root = build_trie(strings);

        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        int max_len = 0;
        for (String s : uniq_str) {
            max_len = Math.max(max_len, s.length());
        }

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;

            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            TrieNode node = root;
            for (int j = i; j < n && j - i + 1 <= max_len; j++) {
                char c = source.charAt(j);
                int idx = c - 'a';
                
                if (node.children[idx] == null) {
                    break;
                }
                
                node = node.children[idx];
                if (node.index != -1) {
                    String src_sub = source.substring(i, j + 1);
                    String tgt_sub = target.substring(i, j + 1);
                    
                    if (src_sub.equals(tgt_sub)) {
                        dp[j + 1] = Math.min(dp[j + 1], dp[i]);
                        continue;
                    }
                    
                    if (index_map.containsKey(tgt_sub)) {
                        int u = node.index;
                        int v = index_map.get(tgt_sub);
                        
                        if (dist[u][v] < INF) {
                            dp[j + 1] = Math.min(dp[j + 1], dp[i] + dist[u][v]);
                        }
                    }
                }
            }
        }

        return dp[n] == INF ? -1 : dp[n];
    }

    private TrieNode build_trie(List<String> strings) {
        TrieNode root = new TrieNode();

        for (int idx = 0; idx < strings.size(); idx++) {
            String s = strings.get(idx);
            TrieNode node = root;

            for (char c : s.toCharArray()) {
                int i = c - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new TrieNode();
                }
                node = node.children[i];
            }
            node.index = idx;
        }

        return root;
    }
}