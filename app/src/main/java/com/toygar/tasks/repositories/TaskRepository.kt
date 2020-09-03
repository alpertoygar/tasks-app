package com.toygar.tasks.repositories

import androidx.lifecycle.LiveData
import com.toygar.tasks.models.Task

interface TaskRepository {
    fun insertTask(task : Task)
    fun getTasks() : LiveData<List<Task>>
}
