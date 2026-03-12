import java.util.*;

public class Problem4_Plagiarism {
    private Map<String, Set<String>> ngramIndex = new HashMap<>();

    public void indexDocument(String docId, String content, int n) {
        String[] words = content.split("\\s+");
        for (int i = 0; i <= words.length - n; i++) {
            String ngram = String.join(" ", Arrays.copyOfRange(words, i, i + n));
            ngramIndex.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
    }

    public Map<String, Integer> analyzeDocument(String docId, String content, int n) {
        Map<String, Integer> similarity = new HashMap<>();
        String[] words = content.split("\\s+");
        for (int i = 0; i <= words.length - n; i++) {
            String ngram = String.join(" ", Arrays.copyOfRange(words, i, i + n));
            if (ngramIndex.containsKey(ngram)) {
                for (String otherDoc : ngramIndex.get(ngram)) {
                    if (!otherDoc.equals(docId)) {
                        similarity.put(otherDoc, similarity.getOrDefault(otherDoc, 0) + 1);
                    }
                }
            }
        }
        return similarity;
    }
}