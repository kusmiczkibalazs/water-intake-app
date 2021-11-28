package com.example.assignmentapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FluidIntake::class], version = 4, exportSchema = false)
abstract class FluidIntakeDatabase : RoomDatabase() {

    abstract val fluidIntakeDatabaseDao: FluidIntakeDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FluidIntakeDatabase? = null

        fun getInstance(context: Context): FluidIntakeDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, FluidIntakeDatabase::class.java,"fluid_intake_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}