package com.toygar.tasks.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.toygar.tasks.models.PriorityConverter
import com.toygar.tasks.models.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(PriorityConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase? {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        LocalDatabase::class.java, "tasks-db"
                    ).build()
                }
                return INSTANCE
            }
        }
    }
}
