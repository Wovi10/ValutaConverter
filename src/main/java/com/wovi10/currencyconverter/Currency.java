package com.wovi10.currencyconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Currency {
    private String Name;
    private String Abbreviation;
    private HashMap<String, Double> ExchangeValues = new HashMap<String, Double>();

    public Currency(String name, String abbreviation) {
        this.Name = name;
        this.Abbreviation = abbreviation;
    }

    public Currency() {
        this.Name = "default";
        this.Abbreviation = "default";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbbreviation() {
        return Abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        Abbreviation = abbreviation;
    }

    public HashMap<String, Double> getExchangeValues() {
        return ExchangeValues;
    }

    public void setExchangeValue(String key, Double exchangeValue) {
        this.ExchangeValues.put(key, exchangeValue);
    }

    public void setDefaults(){
        switch (this.Abbreviation.toUpperCase(Locale.ROOT)) {
            case "EUR" -> {
                this.ExchangeValues.put("AUD", 1.55);
                this.ExchangeValues.put("EUR", 1.00);
                this.ExchangeValues.put("USD", 0.97);
            }
            case "AUD" -> {
                this.ExchangeValues.put("AUD", 1.00);
                this.ExchangeValues.put("EUR", 0.64);
                this.ExchangeValues.put("USD", 0.63);
            }
            case "USD" -> {
                this.ExchangeValues.put("AUD", 1.58);
                this.ExchangeValues.put("EUR", 1.03);
                this.ExchangeValues.put("USD", 1.00);
            }
        }
    }

    public static ArrayList<Currency> initiateCurrencies() {
        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("Euro", "EUR"));
        currencies.add(new Currency("Australian Dollar", "AUD"));
        currencies.add(new Currency("US Dollar", "USD"));
        for (Currency currency : currencies) {
            currency.setDefaults();
        }
        return currencies;
    }

    public static Double convert(Double amount, Double exchangeValue) {
        double price;
        price = amount * exchangeValue;
        price = Math.round(price * 100d) / 100d;

        return price;
    }
}
