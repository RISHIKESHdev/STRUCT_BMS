package com.lvbank.model.transaction;

public class Cash{
    private String Denomination;

    public Cash(String denomination) {
        Denomination = denomination;
    }

    public String getDenomination() {
        return Denomination;
    }

    public void setDenomination(String denomination) {
        Denomination = denomination;
    }
}