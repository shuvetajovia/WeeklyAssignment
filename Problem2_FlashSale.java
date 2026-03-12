import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem2_FlashSale {
    private Map<String, AtomicInteger> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, new AtomicInteger(quantity));
        waitingList.put(productId, new LinkedList<>());
    }

    public synchronized boolean purchaseItem(String productId, int userId) {
        AtomicInteger count = stock.get(productId);
        if (count != null && count.get() > 0) {
            count.decrementAndGet();
            return true;
        } else {
            waitingList.get(productId).add(userId);
            return false;
        }
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, new AtomicInteger(0)).get();
    }
}