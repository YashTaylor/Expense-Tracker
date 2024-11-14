package com.example.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.R
import com.example.expensetracker.Utils
import com.example.expensetracker.data.ExpenseDataBase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseEntity
import com.example.expensetracker.data.model.ExpenseSummary
import java.security.KeyStore.Entry

class StatsViewModel(dao : ExpenseDao) : ViewModel() {
    val entries = dao.getAllExpensesByDate()
    val topEntries = dao.getTopExpenses()

    fun getEntriesForChart(entries : List<ExpenseSummary>) : List<com.github.mikephil.charting.data.Entry> {
        val list = mutableListOf<com.github.mikephil.charting.data.Entry>()
        for (entry in entries) {
            val formattedDate = Utils.getMilliFromDate(entry.date)
            list.add(com.github.mikephil.charting.data.Entry(formattedDate.toFloat(), entry.total_amount.toFloat()))
        }
        return list
    }
}

class StatsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}