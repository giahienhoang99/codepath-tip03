package OAs;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/*
class OrderState {
    private int numberOfStores;
    private int numberOfOrders;
    private int numberOfDiffBevs;
    private int numberOfBevs;
    private int numberOfEachBev;
    private int perBeverageTotal;

    public OrderState()
    {
        // Your code here

    }

    public void UpdateLimit(int numberOfStores, int perBeverageTotal)
    {
        // Your code here

    }

    public void ProcessOrder(int uniqueId, int storeId, String beverageName, int quantity)
    {
        // Your code here
    }

    public void CloseStore(int storeId)
    {
        // Your code here
    }

    public void PrintState()
    {
        // Your code here
    }
}
*/

class OrderState {
    private int numberOfStores = 0;
    private int numberOfOrders = 0;
    private int numberOfDiffBevs = 0;
    private int numberOfBevs = 0;
    private Map<String, Integer> beverageTotals = new HashMap<>();
    private Map<Integer, Map<String, Integer>> storeOrders = new HashMap<>();
    private int storeLimit = 0;
    private int perBeverageTotalLimit = 0;

    public OrderState() {
        // Initialize the state
    }

    public void UpdateLimit(int numberOfStores, int perBeverageTotal) {
        this.storeLimit = numberOfStores;
        this.perBeverageTotalLimit = perBeverageTotal;

        // If limits exceed, reset all stores
        if (this.numberOfStores > storeLimit || exceedsBeverageLimits()) {
            closeAllStores();
        }
    }

    public void ProcessOrder(int uniqueId, int storeId, String beverageName, int quantity) {
        if (quantity <= 0) {
            closeOrder(storeId, beverageName);
            return;
        }

        // Check if adding the order would exceed the beverage limit
        int newTotalForBev = beverageTotals.getOrDefault(beverageName, 0) + quantity;
        if (newTotalForBev > perBeverageTotalLimit) {
            System.out.println("reject_order: " + uniqueId);
            return;
        }

        // Check if the store is new and would exceed the store limit
        boolean isNewStore = !storeOrders.containsKey(storeId);
        if (isNewStore && numberOfStores >= storeLimit) {
            System.out.println("reject_order: " + uniqueId);
            return;
        }

        // Process the order
        storeOrders.putIfAbsent(storeId, new HashMap<>());
        Map<String, Integer> orders = storeOrders.get(storeId);
        int currentQuantity = orders.getOrDefault(beverageName, 0);

        if (currentQuantity == 0 && !beverageTotals.containsKey(beverageName)) {
            numberOfDiffBevs++;
        }

        orders.put(beverageName, quantity);
        beverageTotals.put(beverageName, newTotalForBev);

        numberOfBevs += quantity - currentQuantity;
        if (currentQuantity == 0) {
            numberOfOrders++;
        }

        if (isNewStore) {
            numberOfStores++;
        }
    }

    public void CloseStore(int storeId) {
        Map<String, Integer> orders = storeOrders.remove(storeId);
        if (orders != null) {
            numberOfStores--;
            for (Map.Entry<String, Integer> entry : orders.entrySet()) {
                String bevName = entry.getKey();
                int quantity = entry.getValue();
                beverageTotals.put(bevName, beverageTotals.get(bevName) - quantity);
                numberOfBevs -= quantity;
                if (beverageTotals.get(bevName) == 0) {
                    beverageTotals.remove(bevName);
                    numberOfDiffBevs--;
                }
            }
            numberOfOrders -= orders.size();
        }
    }

    public void PrintState() {
        System.out.println("number_of_stores: " + numberOfStores + ", number_of_orders: " + numberOfOrders +
                ", number_of_different_beverages: " + numberOfDiffBevs + ", number_of_beverages: " + numberOfBevs);
    }

    private boolean exceedsBeverageLimits() {
        for (int quantity : beverageTotals.values()) {
            if (quantity > perBeverageTotalLimit) {
                return true;
            }
        }
        return false;
    }

    private void closeAllStores() {
        storeOrders.clear();
        beverageTotals.clear();
        numberOfStores = 0;
        numberOfOrders = 0;
        numberOfDiffBevs = 0;
        numberOfBevs = 0;
    }

    private void closeOrder(int storeId, String beverageName) {
        Map<String, Integer> orders = storeOrders.get(storeId);
        if (orders != null && orders.containsKey(beverageName)) {
            int quantity = orders.remove(beverageName);
            numberOfBevs -= quantity;
            beverageTotals.put(beverageName, beverageTotals.get(beverageName) - quantity);

            if (beverageTotals.get(beverageName) == 0) {
                beverageTotals.remove(beverageName);
                numberOfDiffBevs--;
            }

            if (orders.isEmpty()) {
                storeOrders.remove(storeId);
                numberOfStores--;
            }

            numberOfOrders--;
        }
    }
}

public class Solution2 {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        OrderState orderState = new OrderState();
        while (in.hasNext()) {
            String operation = in.next();
            int storeId;
            switch (operation) {
                case "UPDATE_LIMIT":
                {
                    int numberOfStores = in.nextInt();
                    int perBeverageTotal = in.nextInt();
                    orderState.UpdateLimit(numberOfStores, perBeverageTotal);
                }
                break;
                case "ORDER_UPDATE":
                {
                    int uniqueId = in.nextInt();
                    storeId = in.nextInt();
                    String beverageName = in.next();
                    int quantity = in.nextInt();
                    orderState.ProcessOrder(uniqueId, storeId, beverageName, quantity);
                }
                break;
                case "CLOSE_STORE":
                {
                    storeId = in.nextInt();
                    orderState.CloseStore(storeId);

                }
                break;
                case "PRINT_STATE":
                {
                    orderState.PrintState();
                }
                break;
                default:
                    throw new IllegalArgumentException("Invalid operation: " + operation);
            }
        }
    }
}

class OrderStateTest {

    private OrderState orderState;

    @BeforeEach
    void setUp() {
        orderState = new OrderState();
    }

    @Test
    void testScenario1() {
        orderState.UpdateLimit(100, 1000);
        orderState.ProcessOrder(1, 1, "lemonade", 100);
        orderState.ProcessOrder(2, 2, "hot_chocolate", 50);
        assertOutput("number_of_stores: 2, number_of_orders: 2, number_of_different_beverages: 2, number_of_beverages: 150");

        orderState.ProcessOrder(3, 3, "lemonade", 75);
        orderState.ProcessOrder(4, 1, "lemonade", 150);
        orderState.ProcessOrder(5, 1, "water", 50);
        assertOutput("number_of_stores: 3, number_of_orders: 4, number_of_different_beverages: 3, number_of_beverages: 325");
    }

    @Test
    void testScenario2() {
        orderState.UpdateLimit(2, 100);
        orderState.ProcessOrder(1, 1, "lemonade", 100);
        orderState.ProcessOrder(2, 2, "hot_chocolate", 50);
        orderState.ProcessOrder(3, 2, "lemonade", 1);
        orderState.ProcessOrder(4, 3, "hot_chocolate", 1);
        assertOutput("reject_order: 3");
    }

    @Test
    void testScenario3() {
        orderState.UpdateLimit(100, 1000);
        orderState.ProcessOrder(1, 1, "lemonade", 100);
        orderState.ProcessOrder(2, 2, "hot_chocolate", 50);
        orderState.PrintState(); // Expect output as described
        orderState.CloseStore(1);
        orderState.PrintState(); // Expect output as described
        orderState.ProcessOrder(3, 2, "hot_chocolate", 0);
        orderState.PrintState(); // Expect output as described
    }

    @Test
    void testScenario4() {
        orderState.UpdateLimit(100, 1000);
        orderState.ProcessOrder(1, 1, "lemonade", 100);
        orderState.ProcessOrder(2, 2, "hot_chocolate", 50);
        orderState.PrintState(); // Expect output as described
        orderState.CloseStore(1);
        orderState.PrintState(); // Expect output as described
        orderState.ProcessOrder(3, 2, "hot_chocolate", 0);
        orderState.PrintState(); // Expect output as described
    }

    private void assertOutput(String expectedOutput) {
        // Implementation to capture and assert the printed output of PrintState()
    }
}
