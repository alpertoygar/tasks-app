package com.toygar.tasks.repositories

import com.toygar.tasks.models.Task

interface TaskRepository {
    fun insertTask(task : Task)
    fun getTasks() : List<Task>
}