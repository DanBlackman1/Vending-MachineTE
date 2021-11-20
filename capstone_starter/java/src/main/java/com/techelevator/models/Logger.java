package com.techelevator.models;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

public class Logger implements Closeable {

    private File logFile;
    private PrintWriter writer;

    public Logger(String pathName){
        this.logFile = new File(pathName);

        if(!logFile.exists()){
            try{
                this.writer = new PrintWriter(this.logFile);
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }
        } else{
            try{
                this.writer = new PrintWriter(new FileWriter(this.logFile, true));
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void write(String logMessage){
        this.writer.println(logMessage);
        this.writer.flush();
    }

    public void writeSameLine(String logMessage){
        this.writer.print(logMessage);
        this.writer.flush();
    }

    public void writeSales(List<VendingMachineItem> vendingMachineItemList) {
        BigDecimal salesTotal = new BigDecimal("0.00");
        for (VendingMachineItem item : vendingMachineItemList) {
            if (item.getTotalSold() > 0) {
                salesTotal = salesTotal.add(item.getPrice());
            }
            String productname = item.getProductName();
            String totalSold = Integer.toString(item.getTotalSold());
            String salesOutput = productname + "|" + totalSold;
            this.write(salesOutput);
        }
        this.write("\nTotal Sales Today: $" + salesTotal);
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }


}
