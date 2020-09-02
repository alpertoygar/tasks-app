package com.toygar.tasks.repositories

import com.toygar.tasks.models.Task

val TASK_REPOSITORY = InMemoryTaskRepository()

class InMemoryTaskRepository : TaskRepository {

    private val tasks = mutableListOf<Task>()

    override fun insertTask(task: Task) {
        tasks.add(task)
    }

    override fun getTasks(): List<Task> {
        return tasks
    }
}