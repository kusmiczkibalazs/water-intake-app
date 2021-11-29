package com.example.assignmentapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fluid_intake_table")
data class FluidIntake(
    @PrimaryKey(autoGenerate = true)
    var intakeId: Long = 0L,

    @ColumnInfo(name = "intake_quantity")
    var intakeQuantity: Int = -1,

    @ColumnInfo(name = "intake_time_milli")
    var intakeTimeMilli: Long = System.currentTimeMillis()

)