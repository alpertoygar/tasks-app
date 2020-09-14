package com.toygar.tasks.models

import androidx.room.TypeConverter

enum class Priority {
    HIGH,
    MEDIUM,
    LOW;

    fun toHumanString(): String {
        return super.toString().toLowerCase().capitalize()
    }
}

class PriorityConverter{

    @TypeConverter
    fun fromPriority(value: Priority): String = value.toString()


    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)
}
