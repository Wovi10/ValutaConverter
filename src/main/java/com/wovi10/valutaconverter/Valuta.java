package com.wovi10.valutaconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Valuta {
    private String Name;
    private String Abbreviation;
    private HashMap<String, Double> ExchangeValues = new HashMap<String, Double>();

    public Valuta(String name, String abbreviation) {
        Name = name;
        Abbreviation = abbreviation;
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
                this.ExchangeValues.put("AUD", 1.54);
                this.ExchangeValues.put("EUR", 1.00);
                this.ExchangeValues.put("USD", 0.97);
            }
            case "AUD" -> {
                this.ExchangeValues.put("AUD", 1.00);
                this.ExchangeValues.put("EUR", 0.65);
                this.ExchangeValues.put("USD", 0.63);
            }
            case "USD" -> {
                this.ExchangeValues.put("AUD", 1.58);
                this.ExchangeValues.put("EUR", 1.03);
                this.ExchangeValues.put("USD", 1.00);
            }
        }
    }

    public static ArrayList<Valuta> initiateCurrencies() {
        ArrayList<Valuta> currencies = new ArrayList<>();
        currencies.add(new Valuta("Euro", "EUR"));
        currencies.add(new Valuta("Australian Dollar", "AUD"));
        currencies.add(new Valuta("US Dollar", "USD"));
        for (Valuta valuta: currencies) {
            valuta.setDefaults();
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
