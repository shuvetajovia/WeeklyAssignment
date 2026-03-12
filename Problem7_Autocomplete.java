import java.util.*;

public class Problem7_Autocomplete {

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;
        int frequency;
    }

    private TrieNode root = new TrieNode();

    public void insert(String query, int freq) {
        TrieNode node = root;
        for (char c : query.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEnd = true;
        node.frequency = freq;
    }

    public List<String> getSuggestions(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return result;
            node = node.children.get(c);
        }
        dfs(node, new StringBuilder(prefix), result);
        return result;
    }

    private void dfs(TrieNode node, StringBuilder path, List<String> result) {
        if (node.isEnd) result.add(path.toString());
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            path.append(entry.getKey());
            dfs(entry.getValue(), path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}