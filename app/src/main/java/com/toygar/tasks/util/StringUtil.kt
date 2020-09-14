package com.toygar.tasks.util

fun String.capitalizeFully() = split(" ").joinToString(" ") { it.capitalize() }
