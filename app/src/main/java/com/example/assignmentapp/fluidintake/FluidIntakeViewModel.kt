package com.example.assignmentapp.fluidintake

import android.app.Application
import android.os.Build
import android.os.Handler
import android.text.format.DateUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.assignmentapp.database.FluidIntake
import com.example.assignmentapp.database.FluidIntakeDatabaseDao
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.schedule

class FluidIntakeViewModel(
    dataSource: FluidIntakeDatabaseDao) : ViewModel() {

    var inputIntakeQuantity: Int = -1

    private val database = dataSource

    val intakes = database.getAllIntakes()

    private val _submitPushed = MutableLiveData<Boolean?>()
    val submitPushed: LiveData<Boolean?>
        get() = _submitPushed

    fun doneGettingInputFromFragment() {
        _submitPushed.value = null
    }

    private val _clearPushed = MutableLiveData<Boolean?>()
    val clearPushed: LiveData<Boolean?>
        get() = _clearPushed

    fun doneHidingKeyboard() {
        _clearPushed.value = null
    }

    private val _navigateToResultFragment = MutableLiveData<Int>()
    val navigateToResultFragment: LiveData<Int>
        get() = _navigateToResultFragment

    fun doneNavigatingToResultFragment() {
        _navigateToResultFragment.value = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmitIntake() {
        _submitPushed.value = true

        viewModelScope.launch {
            val newIntake = FluidIntake()
            newIntake.intakeQuantity = inputIntakeQuantity
            if (inputIntakeQuantity != -1) {
                insert(newIntake)
                Handler().postDelayed({
                    Log.i("FluidIntakeViewModel", sumTodayIntake().toString())
                    _navigateToResultFragment.value = sumTodayIntake()
                }, 100)
            }
            inputIntakeQuantity = -1
            // TODO megjelenítés
        }

    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sumTodayIntake(): Int {
        var sumOfToday : Int = 0
        intakes.value?.forEach {
            //var intakeDate: LocalDate = LocalDate.parse(it.intakeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            if (DateUtils.isToday(it.intakeTimeMilli)) {
                sumOfToday += it.intakeQuantity
            }
        }
        return sumOfToday
    }

    val clearButtonVisibility = Transformations.map(intakes) {
        it?.isNotEmpty()
    }

    private suspend fun insert(fluidIntake: FluidIntake) {
        database.insert(fluidIntake)
    }

    private suspend fun clear() {
        database.clear()
        _clearPushed.value = true
    }

}