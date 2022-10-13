package com.wovi10.currencyconverter.utils;

import com.wovi10.currencyconverter.Currency;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyConstants {
    public static final int PROGRAM_WIDTH = 500;
    public static final int PROGRAM_HEIGHT = 250;
    public static final String PROGRAM_TITLE = "Currency Converter";
    public static final String CHOOSE_A_VALUTA = "Choose a valuta";
    public static final String CONVERT_TEXT = "Convert";
    public static final Double DEFAULT_EXCHANGEVALUE = 1d;
    public static final int STANDARD_INDENT = 50;
    public static final int STANDARD_HEIGHT = 50;
    public static final int TEXTFIELD_WIDTH = 50;
    public static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    public static final Currency defaultCurrency = new Currency();
    public static final ArrayList<Currency> Currencies = Currency.initiateCurrencies();
}
