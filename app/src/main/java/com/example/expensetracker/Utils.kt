package com.example.expensetracker

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.example.expensetracker.data.model.ExpenseEntity
import java.text.ParseException
import java.util.Date
import java.util.Locale

object Utils {

    fun formatDateToReadableForm(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun formatDateForChart(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd-MM", java.util.Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun formatDayMonth(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM", java.util.Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }


    @SuppressLint("DefaultLocale")
    fun FormatToDecimalValue(d : Double) : String {
        return String.format("%.2f", d)
    }

    fun getMilliFromDate(dateFormat: String?): Long {
        var date = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            date = formatter.parse(dateFormat)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println("Today is $date")
        return date.time
    }

    fun getItemIcon(item : ExpenseEntity) :Int {
        if (item.category == "Paypal") {
            return R.drawable.ic_paypal
        } else if (item.category == "Netflix") {
            return R.drawable.ic_netflix
        } else if (item.category == "Starbucks") {
            return R.drawable.ic_starbucks
        }
        return R.drawable.ic_upwork
    }
}