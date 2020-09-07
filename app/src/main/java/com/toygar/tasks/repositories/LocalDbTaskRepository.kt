package com.toygar.tasks.repositories

import com.toygar.tasks.data.TaskDao
import com.toygar.tasks.models.Task

class LocalDbTaskRepository(
    private val taskDao : TaskDao
): TaskRepository {

    override val tasks = taskDao.getTasks()

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}
