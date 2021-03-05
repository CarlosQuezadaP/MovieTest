package com.merqueo.co.data.source.db.converters

import androidx.room.TypeConverter


class FromListOfStringsToStringConverter {
    @TypeConverter
    fun fromStringList(listOfStrings: List<String>): String {
        return listOfStrings.joinToString(",")
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        return data.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toList()
    }
}