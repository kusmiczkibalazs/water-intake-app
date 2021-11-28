package com.example.assignmentapp.goalachieved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}