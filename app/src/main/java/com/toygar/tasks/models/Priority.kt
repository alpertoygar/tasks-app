package com.toygar.tasks.models

import androidx.room.TypeConverter

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

class PriorityConverter{

    @TypeConverter
    fun fromPriority(value: Priority): String = value.toString()


    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)
}
