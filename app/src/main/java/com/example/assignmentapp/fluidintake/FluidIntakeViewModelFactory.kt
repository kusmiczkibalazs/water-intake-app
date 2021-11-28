package com.example.assignmentapp.fluidintake

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentapp.database.FluidIntakeDatabaseDao

class FluidIntakeViewModelFactory(
    private val dataSource: FluidIntakeDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FluidIntakeViewModel::class.java)) {
            return FluidIntakeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}