import java.util.*;

public class Problem5_AnalyticsDashboard {
    private Map<String, Integer> pageViews = new HashMap<>();
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private Map<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {
        System.out.println("Top Pages: " + pageViews);
        System.out.println("Unique Visitors: " + uniqueVisitors);
        System.out.println("Traffic Sources: " + trafficSources);
    }
}