/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jomax
 */
public class StockItem {
    private String dateEntered;
    private String stockLabel;
    private String brand;
    private String engineNumber;
    private String status;

    public StockItem(String dateEntered, String stockLabel, String brand, String engineNumber, String status) {
        this.dateEntered = dateEntered;
        this.stockLabel = stockLabel;
        this.brand = brand;
        this.engineNumber = engineNumber;
        this.status = status;
    }

    // Getters and Setters
    public String getDateEntered() { return dateEntered; }
    public void setDateEntered(String dateEntered) { this.dateEntered = dateEntered; }
    
    public String getStockLabel() { return stockLabel; }
    public void setStockLabel(String stockLabel) { this.stockLabel = stockLabel; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getEngineNumber() { return engineNumber; }
    public void setEngineNumber(String engineNumber) { this.engineNumber = engineNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
