import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManagementSystem {

    private List<StockItem> inventoryList;
    private Map<String, StockItem> searchMap;

    public InventoryManagementSystem() {
        this.inventoryList = new ArrayList<>();
        this.searchMap = new HashMap<>();
    }

    public void addNewStock(StockItem item) {
        this.inventoryList.add(item);
        this.searchMap.put(item.getEngineNumber(), item);
    }

    public boolean deleteIncorrectEntry(int index) {
        if (index >= 0 && index < this.inventoryList.size()) {
            StockItem itemToRemove = this.inventoryList.remove(index);
            this.searchMap.remove(itemToRemove.getEngineNumber());
            return true;
        }
        return false;
    }

    public void sortInventoryByBrand() {
        Collections.sort(this.inventoryList, (item1, item2) -> item1.getBrand().compareTo(item2.getBrand()));
    }

    public StockItem searchByEngineNumber(String engineNumber) {
        return this.searchMap.getOrDefault(engineNumber, null);
    }

    public void loadInventoryFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.skip(1);

            String[] line;
            while ((line = reader.readNext()) != null) {
                String dateEntered = line[0];
                String stockLabel = line[1];
                String brand = line[2];
                String engineNumber = line[3];
                String status = line[4];

                StockItem stockItem = new StockItem(dateEntered, stockLabel, brand, engineNumber, status);
                this.addNewStock(stockItem);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem();
        inventory.loadInventoryFromCSV("C:\\Users\\Jomax\\OneDrive\\Documents\\NetBeansProjects\\MotorPHInventory\\src\\data\\MotorPH Inventory Data - March 2023 Inventory Data.csv");

        System.out.println("Initial Inventory:");
        for (StockItem item : inventory.inventoryList) {
            System.out.println(item.getBrand() + " " + item.getEngineNumber());
        }

        inventory.sortInventoryByBrand();
        System.out.println("\nSorted Inventory:");
        for (StockItem item : inventory.inventoryList) {
            System.out.println(item.getBrand() + " " + item.getEngineNumber());
        }

        StockItem foundItem = inventory.searchByEngineNumber("142QVTSIUR");
        System.out.println("\nFound Item: " + (foundItem != null ? foundItem.getBrand() + " " + foundItem.getEngineNumber() : "Not found"));

        inventory.deleteIncorrectEntry(0);
        System.out.println("\nInventory After Deletion:");
        for (StockItem item : inventory.inventoryList) {
            System.out.println(item.getBrand() + " " + item.getEngineNumber());
        }
    }
}
