import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an inventory management system. It allows me to add, delete, sort, and search stock items.
 */

public class InventoryManagementSystem {

        // List to store all stock items in the inventory
    private List<StockItem> inventoryList;
    
            // Map to quickly search for stock items by engine number (key)
    private Map<String, StockItem> searchMap;

    /**
     * Constructor initializes the empty list and map.
     */
    public InventoryManagementSystem() {
        this.inventoryList = new ArrayList<>();
        this.searchMap = new HashMap<>();
    }

        /**
     * Adds a new stock item to both the list and map.
     *
     * @param item The StockItem object to be added.
     */
    public void addNewStock(StockItem item) {
                // Add the item to the end of the list
        this.inventoryList.add(item);
                // Use engine number as key in map for efficient lookup later
        this.searchMap.put(item.getEngineNumber(), item);
    }
    /**
     * Deletes a stock item from both the list and map based on its index in the list.
     *
     * @param index The position of the item in the inventoryList that needs to be removed.
     * @return True if deletion was successful; false otherwise (e.g., invalid index).
     */
    public boolean deleteIncorrectEntry(int index) {
        if (index >= 0 && index < this.inventoryList.size()) {
                        // Remove from list at specified index
            StockItem itemToRemove = this.inventoryList.remove(index);
            
                        // Also remove from map using engine number as key
            this.searchMap.remove(itemToRemove.getEngineNumber());
            return true; // Deletion successful
        }
        return false; // Invalid or out-of-range index provided
    }
    
     /**
     * Sorts all items in inventory by brand name alphabetically using Collections.sort().
     */

    public void sortInventoryByBrand() {
        Collections.sort(this.inventoryList, (item1, item2) ->                          /* Compare two StockItems based on their brand names */ 
 item1.getBrand().compareTo(item2.getBrand()));
    }

/**
* Searches for a specific stock item by its engine number using O(1) lookup via HashMap.  
*
* @param engineNumber Unique identifier of each vehicle/engine.  
* @return Found StockItem object if exists; otherwise returns null.  
*/      
    public StockItem searchByEngineNumber(String engineNumber) {
        return this.searchMap.getOrDefault(engineNumber, null);
    }
/**
* Loads initial data into system from a CSV file located at filePath. Each line represents one vehicle/stock entry with fields: dateEntered, label/stockLabel, brandName/brand, uniqueIdentifier/engineNumber & statusOfVehicle/status respectively.

*
* Note: Skips first row assuming it contains header information not needed here!
*
*@param filePath Path where your csv file resides including filename itself e.g., "C:\\Users\\Jomax\\OneDrive\\Documents\\NetBeansProjects\\MotorPHInventory\\src\\data\\MotorPH Inventory Data - March 2023 Inventory Data.csv"
*/
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
          
                /* Create new instance representing current vehicle being processed */          
                StockItem stockItem = new StockItem(dateEntered, stockLabel, brand, engineNumber, status);
                          /* Add newly created entry into our collection(s). */         
                this.addNewStock(stockItem);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    /** Main method demonstrates usage of various functionalities provided by InventoryManagementSystem class like loading data from csv file then sorting/searching/deleting entries etc.. */
    
    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem();
        inventory.loadInventoryFromCSV("C:\\Users\\Jomax\\OneDrive\\Documents\\NetBeansProjects\\MotorPHInventory\\src\\data\\MotorPH Inventory Data - March 2023 Inventory Data.csv");

        System.out.println("Initial Inventory:");
        
           /* Print out unsorted collection contents - just displaying Brand & Engine Number info per entry */ 
        for (StockItem item : inventory.inventoryList) {
            System.out.println(item.getBrand() + " " + item.getEngineNumber());
        }
        /* Sort entire collection alphabetically according Brand Name now! */

        inventory.sortInventoryByBrand();
        
        /* Display sorted results now! */
        System.out.println("\nSorted Inventory:");
        for (StockItem item : inventory.inventoryList) {
            System.out.println(item.getBrand() + " " + item.getEngineNumber());
        }

        /* Search specific vehicle having given Engine Number within entire dataset! */
        StockItem foundItem = inventory.searchByEngineNumber("142QVTSIUR");
        
        /* Output result whether found or not! */
        System.out.println("\nFound Item: " + (foundItem != null ? foundItem.getBrand() + " " + foundItem.getEngineNumber() : "Not found"));

        /* Delete first record present currently within dataset - indexed at '0' !*/
        inventory.deleteIncorrectEntry(0);
        
        /* Show updated remaining records post-deletion operation !*/
        System.out.println("\nInventory After Deletion:");
        for (StockItem item : inventory.inventoryList) {
            System.out.println(item.getBrand() + " " + item.getEngineNumber());
        }
    }
}
