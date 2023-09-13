package com.example.androidcomposestudy.utils

import java.text.DecimalFormat
import java.util.Currency
import java.util.Locale

fun formatCurrency(amount: Double, currencyCode: String = "BRL"): String {
    val currencyFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
    val currency = Currency.getInstance(currencyCode)
    currencyFormat.currency = currency
    return currencyFormat.format(amount)
}