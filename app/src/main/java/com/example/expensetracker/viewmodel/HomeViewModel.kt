package com.example.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.R
import com.example.expensetracker.Utils
import com.example.expensetracker.data.ExpenseDataBase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseEntity

class HomeViewModel(dao : ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpenses()

    fun getBalance(list : List<ExpenseEntity>) : String {
        var balance = 0.0
        list.forEach {
            if (it.type == "Income")
                balance += it.amount
            else
                balance -= it.amount
        }
        return "$ ${Utils.FormatToDecimalValue(balance)}"
    }

    fun getTotalExpense(list : List<ExpenseEntity>) : String {
        var total = 0.0
        list.forEach {
            if (it.type == "Expense")
                total += it.amount
        }
        return "$ ${Utils.FormatToDecimalValue(total)}"
    }

    fun getTotalIncome(list : List<ExpenseEntity>) :String {
        var totalIncome = 0.0
        list.forEach {
            if (it.type == "Income")
                totalIncome += it.amount
        }
        return "$ ${Utils.FormatToDecimalValue(totalIncome)}"
    }
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}