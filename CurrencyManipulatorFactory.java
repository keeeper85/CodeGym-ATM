package com.codegym.task.task26.task2613;

import java.util.*;

public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){

        String caseIgnored  = currencyCode.toUpperCase();

        if (!map.containsKey(caseIgnored)) map.put(caseIgnored, new CurrencyManipulator(caseIgnored));

    return map.get(caseIgnored);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        Collection<CurrencyManipulator> manipulators = new ArrayList<>();
        for (Map.Entry<String, CurrencyManipulator> stringCurrencyManipulatorEntry : map.entrySet()) {
            manipulators.add(stringCurrencyManipulatorEntry.getValue());
        }
        return manipulators;
    }
}
