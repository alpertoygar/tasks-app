package com.toygar.tasks.models

import androidx.room.TypeConverter
import androidx.room.TypeConverters

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

class PriorityConverter{

    @TypeConverter
    fun fromPriority(value: Priority): String{
        return value.toString()
    }

    @TypeConverter
    fun toPriority(value: String): Priority{
        return Priority.valueOf(value)
    }
}
