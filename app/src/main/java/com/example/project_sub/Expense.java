package com.example.project_sub;

public class Expense {

    String day;
    String amount;

    public Expense(String day, String amount) {
        this.day = day;
        this.amount = amount;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}
