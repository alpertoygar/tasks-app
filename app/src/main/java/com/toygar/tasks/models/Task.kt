package com.toygar.tasks.models

val TASKS = mutableListOf<Task>()

data class Task(val name: String, val priority: Priority, val description: String = "")