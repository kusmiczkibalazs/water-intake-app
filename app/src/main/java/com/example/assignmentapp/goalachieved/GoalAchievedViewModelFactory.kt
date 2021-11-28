package com.example.assignmentapp.goalachieved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GoalAchievedViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalAchievedViewModel::class.java)){
            return GoalAchievedViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}