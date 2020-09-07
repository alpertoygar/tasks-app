package com.toygar.tasks.repositories

import com.toygar.tasks.models.Task

class TaskRepository(
    private val taskDao : TaskDao
) {

    val tasks = taskDao.getTasks()

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

}
