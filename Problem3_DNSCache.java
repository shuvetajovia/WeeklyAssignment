import java.util.*;
import java.util.concurrent.*;

public class Problem3_DNSCache {
    private class DNSEntry {
        String domain;
        String ipAddress;
        long expiryTime;
        DNSEntry(String domain, String ip, long ttlMillis) {
            this.domain = domain;
            this.ipAddress = ip;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }
    }

    private Map<String, DNSEntry> cache = new ConcurrentHashMap<>();
    private int hits = 0, misses = 0;

    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        long now = System.currentTimeMillis();
        if (entry != null && entry.expiryTime > now) {
            hits++;
            return entry.ipAddress;
        } else {
            misses++;
            String ip = queryUpstream(domain);
            cache.put(domain, new DNSEntry(domain, ip, 300_000));
            return ip;
        }
    }

    private String queryUpstream(String domain) {
        return "127.0.0.1"; // placeholder
    }

    public void getCacheStats() {
        System.out.println("Hits: " + hits + ", Misses: " + misses);
    }
}