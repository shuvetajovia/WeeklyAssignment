import java.util.*;

public class Problem10_MultiLevelCache {

    private class CacheEntry {
        String videoId;
        Object data;
        int accessCount;
        CacheEntry(String videoId, Object data) {
            this.videoId = videoId;
            this.data = data;
            this.accessCount = 0;
        }
    }

    private LinkedHashMap<String, CacheEntry> L1; // in-memory
    private Map<String, CacheEntry> L2; // SSD placeholder
    private Map<String, CacheEntry> L3; // DB placeholder
    private int L1Capacity;

    public Problem10_MultiLevelCache(int l1Capacity) {
        this.L1Capacity = l1Capacity;
        L1 = new LinkedHashMap<String, CacheEntry>(l1Capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                return size() > L1Capacity;
            }
        };
        L2 = new HashMap<>();
        L3 = new HashMap<>();
    }

    public void accessVideo(String videoId) {
        if (L1.containsKey(videoId)) {
            L1.get(videoId).accessCount++;
        } else if (L2.containsKey(videoId)) {
            CacheEntry entry = L2.remove(videoId);
            promoteToL1(entry);
        } else if (L3.containsKey(videoId)) {
            CacheEntry entry = L3.get(videoId);
            promoteToL1(entry);
        } else {
            CacheEntry entry = new CacheEntry(videoId, null);
            promoteToL1(entry);
        }
    }

    private void promoteToL1(CacheEntry entry) {
        L1.put(entry.videoId, entry);
    }
}