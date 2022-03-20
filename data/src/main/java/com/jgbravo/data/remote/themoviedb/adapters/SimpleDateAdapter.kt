package com.jgbravo.data.remote.themoviedb.adapters

import com.squareup.moshi.*
import java.text.SimpleDateFormat
import java.util.*

class SimpleDateAdapter : JsonAdapter<Date>() {

    companion object {
        const val DATE_PATTERN = "yyyy-MM-dd"
    }

    private val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        return try {
            synchronized(dateFormat) {
                reader.isLenient = true
                val dateAsString = reader.nextString()
                dateFormat.parse(dateAsString)
            }
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let { date ->
            synchronized(dateFormat) {
                writer.value(date.toString())
            }
        }
    }
}