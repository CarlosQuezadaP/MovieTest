package com.merqueo.co.data.db.converters

import androidx.room.TypeConverter


class FromListOfIntToStringConverter {
    @TypeConverter
    fun fromIntList(listOfInts: List<Int>): String {
        return listOfInts.joinToString(",")
    }

    @TypeConverter
    fun toIntList(data: String): List<Int> {
        return data.split(",".toRegex()).dropLastWhile { it.isEmpty() }.map { it.toInt() }.toList()
    }
}