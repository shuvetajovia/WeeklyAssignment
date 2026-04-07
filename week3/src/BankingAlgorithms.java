import java.util.*;

public class BankingAlgorithms {

    // ===================== Problem 1 =====================
    static class Transaction {
        String id;
        double fee;
        String ts;

        Transaction(String id, double fee, String ts) {
            this.id = id;
            this.fee = fee;
            this.ts = ts;
        }

        public String toString() {
            return id + ":" + fee + "@" + ts;
        }
    }

    static void bubbleSortTransactions(List<Transaction> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    static void insertionSortTransactions(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).ts.compareTo(key.ts) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    static void findHighFee(List<Transaction> list) {
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println("High Fee: " + t);
            }
        }
    }

    // ===================== Problem 2 =====================
    static class Client {
        String name;
        int risk;
        double balance;

        Client(String name, int risk, double balance) {
            this.name = name;
            this.risk = risk;
            this.balance = balance;
        }

        public String toString() {
            return name + ":" + risk;
        }
    }

    static void bubbleSortClients(Client[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].risk > arr[j + 1].risk) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void insertionSortClientsDesc(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].risk < key.risk ||
                            (arr[j].risk == key.risk &&
                                    arr[j].balance < key.balance))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ===================== Problem 3 =====================
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    static void quickSortDesc(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] > pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ===================== Problem 4 =====================
    static class Asset {
        String name;
        double returnRate;
        double volatility;

        Asset(String name, double r, double v) {
            this.name = name;
            this.returnRate = r;
            this.volatility = v;
        }
    }

    static void mergeSortAssets(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortAssets(arr, l, m);
            mergeSortAssets(arr, m + 1, r);
            mergeAssets(arr, l, m, r);
        }
    }

    static void mergeAssets(Asset[] arr, int l, int m, int r) {
        Asset[] temp = new Asset[r - l + 1];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i].returnRate <= arr[j].returnRate)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    static void quickSortAssets(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionAssets(arr, low, high);
            quickSortAssets(arr, low, pi - 1);
            quickSortAssets(arr, pi + 1, high);
        }
    }

    static int partitionAssets(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // ===================== Problem 5 =====================
    static int linearSearch(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }

    static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].equals(target)) return mid;
            else if (arr[mid].compareTo(target) < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // ===================== Problem 6 =====================
    static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1, res = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target) {
                res = arr[mid];
                low = mid + 1;
            } else high = mid - 1;
        }
        return res;
    }

    static int ceil(int[] arr, int target) {
        int low = 0, high = arr.length - 1, res = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                res = arr[mid];
                high = mid - 1;
            } else low = mid + 1;
        }
        return res;
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {

        // Problem 1 Demo
        List<Transaction> tx = new ArrayList<>();
        tx.add(new Transaction("id1", 10.5, "10:00"));
        tx.add(new Transaction("id2", 25.0, "09:30"));
        tx.add(new Transaction("id3", 5.0, "10:15"));

        bubbleSortTransactions(tx);
        System.out.println("Bubble Sorted: " + tx);

        insertionSortTransactions(tx);
        System.out.println("Insertion Sorted: " + tx);

        findHighFee(tx);

        // Problem 2 Demo
        Client[] clients = {
                new Client("A", 20, 1000),
                new Client("B", 50, 2000),
                new Client("C", 80, 500)
        };

        bubbleSortClients(clients);
        System.out.println("Clients Bubble: " + Arrays.toString(clients));

        insertionSortClientsDesc(clients);
        System.out.println("Clients Desc: " + Arrays.toString(clients));

        // Problem 3 Demo
        int[] trades = {500, 100, 300};
        mergeSort(trades, 0, trades.length - 1);
        System.out.println("Merge Sorted: " + Arrays.toString(trades));

        quickSortDesc(trades, 0, trades.length - 1);
        System.out.println("Quick Desc: " + Arrays.toString(trades));

        // Problem 5 Demo
        String[] logs = {"accA", "accB", "accB", "accC"};
        System.out.println("Linear: " + linearSearch(logs, "accB"));
        System.out.println("Binary: " + binarySearch(logs, "accB"));

        // Problem 6 Demo
        int[] risks = {10, 25, 50, 100};
        System.out.println("Floor(30): " + floor(risks, 30));
        System.out.println("Ceil(30): " + ceil(risks, 30));
    }
}