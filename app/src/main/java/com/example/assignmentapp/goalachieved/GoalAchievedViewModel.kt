package com.example.assignmentapp.goalachieved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentapp.goalachieved.network.QuoteApi
import kotlinx.coroutines.launch
import java.lang.Exception

class GoalAchievedViewModel : ViewModel() {

    private val _navigateBack = MutableLiveData<Boolean?>()
    val navigateBack: LiveData<Boolean?>
        get() = _navigateBack

    fun doneNavigateBack() {
        _navigateBack.value = null
    }

    fun onBack() {
        _navigateBack.value = true
    }

    fun isGoalAchieved(intakeQuantity : Int) : Boolean = intakeQuantity >= 30

    fun calculateRemainingQuantity(intakeQuantity : Int) : Int = 30 - intakeQuantity

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getQuoteProperties()
    }

    private fun getQuoteProperties() {
        viewModelScope.launch {
            try {
                val result = QuoteApi.retrofitService.getQuote()
                _response.value = '"' + result.content + '"'
            } catch (e: Exception) {
                _response.value = "Hiba: " + e.message
            }
        }
    }
}