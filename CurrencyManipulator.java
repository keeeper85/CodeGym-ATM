package com.codegym.task.task26.task2613;

import com.codegym.task.task26.task2613.exception.InsufficientFundsException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)){
            int oldValue = denominations.get(denomination);
            denominations.put(denomination, oldValue + count);
        }
        else denominations.put(denomination, count);
    }

    public int getTotalAmount(){
        int total = 0;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }

        return total;
    }

    public boolean hasMoney(){
        if (getTotalAmount() > 0) return true;
        return false;
    }

    public boolean isAmountAvailable(int expectedAmount){
        if (expectedAmount <= getTotalAmount()) return true;
        return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws InsufficientFundsException {
        Map<Integer, Integer> cache = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            cache.put(entry.getKey(), entry.getValue());
        }

        int amountRemaining = expectedAmount;

        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            int amountOfCurrentDenominations = entry.getValue();
            int denomination = entry.getKey();
            while (amountRemaining >= denomination){
                if (amountOfCurrentDenominations < 1) break;
                else {
                    amountRemaining -= denomination;
                    if (result.containsKey(denomination)){
                        int newValue = result.get(denomination) + 1;
                        result.replace(denomination, newValue);
                    }
                    else result.put(entry.getKey(), 1);
                    amountOfCurrentDenominations--;
                }
                entry.setValue(amountOfCurrentDenominations);
            }
        }

        if (amountRemaining != 0) {
            throw new InsufficientFundsException();
        }

        updateDenominations(result);

        return result;
    }

    private void updateDenominations(Map<Integer, Integer> result) {
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int denominationTaken = entry.getKey();
            int amountTaken = entry.getValue();
            int amountRemaining = denominations.get(denominationTaken) - amountTaken;
            denominations.replace(denominationTaken, amountRemaining);
        }
    }

//    public void testDenominations(){
//        denominations.put(100, 10);
//        denominations.put(50, 10);
//        denominations.put(20, 10);
//        denominations.put(10, 10);
//        denominations.put(5, 10);
//        denominations.put(2, 10);
//        denominations.put(1, 10);
//    }


    public String getCurrencyCode() {
        return currencyCode;
    }
}
