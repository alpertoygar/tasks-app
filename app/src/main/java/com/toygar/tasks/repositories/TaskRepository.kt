package com.toygar.tasks.repositories

import androidx.lifecycle.LiveData
import com.toygar.tasks.models.Task

interface TaskRepository {
    val tasks : LiveData<List<Task>>

    suspend fun insertTask(task: Task)
}
