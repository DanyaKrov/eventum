package com.example.eventum.data.roomDatabase.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListConverter {
    @TypeConverter
    fun fromList(value: List<Long>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<Long> {
        val type = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson(value, type)
    }
}