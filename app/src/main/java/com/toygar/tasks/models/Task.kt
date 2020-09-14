package com.toygar.tasks.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toygar.tasks.util.capitalizeFully

@Entity
data class Task(
    @PrimaryKey val id : String,
    @ColumnInfo(name = "task_name") val name: String,
    @ColumnInfo(name = "task_priority")val priority: Priority,
    @ColumnInfo(name = "task_description")val description: String?,
    @ColumnInfo(name = "task_due_date")val dueDate: Long?)

enum class SortableTaskFields {
    NAME,
    PRIORITY,
    DUE_DATE;

    fun toHumanString(): String {
        return super.toString().toLowerCase().replace("_", " ").capitalizeFully()
    }
}
