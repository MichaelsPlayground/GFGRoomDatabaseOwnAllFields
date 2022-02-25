package de.androidcrypto.gfgroomdatabaseown;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "stock_movements_table")
public class StockMovementsModal {

    //below line is to auto increment id for each course.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int id;
    //below line is use for date (yyyy-mm-dd).
    private String date;
    //below line is use for date as unix timestamp.
    private String dateUnix;
    //below line is a variable for stock name.
    private String stockName;
    //below line is use for stock isin.
    private String stockIsin;
    //below line is use for direction (buy or sell).
    private String direction;
    //below line is use for amountEuro.
    private String amountEuro;
    //below line is use for number of shares (positive when bying, negative when selling).
    private String numberShares;
    //below line is use for the bank of the account.
    private String bank;
    //below line is use for a note.
    private String note;

    //below line we are creating constructor class.
    //inside constructor class we are not passing our id because it is incrementing automatically
    public StockMovementsModal(String date, String dateUnix, String stockName, String stockIsin,  String direction,  String amountEuro,  String numberShares, String bank, String note) {
        this.date = date;
        this.dateUnix = dateUnix;
        this.stockName = stockName;
        this.stockIsin = stockIsin;
        this.direction = direction;
        this.amountEuro = amountEuro;
        this.numberShares = numberShares;
        this.bank = bank;
        this.note = note;
    }

    //on below line we are creating getter and setter methods.


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateUnix() {
        return dateUnix;
    }

    public void setDateUnix(String dateUnix) {
        this.dateUnix = dateUnix;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockIsin() {
        return stockIsin;
    }

    public void setStockIsin(String stockIsin) {
        this.stockIsin = stockIsin;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(String amountEuro) {
        this.amountEuro = amountEuro;
    }

    public String getNumberShares() {
        return numberShares;
    }

    public void setNumberShares(String numberShares) {
        this.numberShares = numberShares;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
