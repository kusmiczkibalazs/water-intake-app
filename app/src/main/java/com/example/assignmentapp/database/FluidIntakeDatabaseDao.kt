package com.example.assignmentapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FluidIntakeDatabaseDao {

    @Insert
    suspend fun insert(fluidIntake: FluidIntake)

    @Query("DELETE FROM fluid_intake_table")
    suspend fun clear()

    @Query("SELECT * FROM fluid_intake_table ORDER BY intakeId DESC")
    fun getAllIntakes(): LiveData<List<FluidIntake>>

}