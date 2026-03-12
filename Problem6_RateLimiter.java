import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem6_RateLimiter {

    private class TokenBucket {
        int maxTokens;
        double refillRate; // tokens per second
        double tokens;
        long lastRefillTime;

        TokenBucket(int maxTokens, double refillRate) {
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.tokens = maxTokens;
            this.lastRefillTime = System.currentTimeMillis();
        }

        synchronized boolean allowRequest() {
            refill();
            if (tokens >= 1) {
                tokens--;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            double delta = (now - lastRefillTime) / 1000.0;
            tokens = Math.min(maxTokens, tokens + delta * refillRate);
            lastRefillTime = now;
        }
    }

    private Map<String, TokenBucket> clients = new HashMap<>();

    public void addClient(String clientId, int maxTokens, double refillRate) {
        clients.put(clientId, new TokenBucket(maxTokens, refillRate));
    }

    public boolean checkRateLimit(String clientId) {
        TokenBucket bucket = clients.get(clientId);
        if (bucket != null) return bucket.allowRequest();
        return false;
    }
}