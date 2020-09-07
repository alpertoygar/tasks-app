package com.toygar.tasks.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.toygar.tasks.models.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task : Task)

    @Query("SELECT * from Task")
    fun getTasks() : LiveData<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)
}
