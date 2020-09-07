package com.toygar.tasks.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val id : String,
    @ColumnInfo(name = "task_name") val name: String,
    @ColumnInfo(name = "task_priority")val priority: Priority,
    @ColumnInfo(name = "task_description")val description: String?)
