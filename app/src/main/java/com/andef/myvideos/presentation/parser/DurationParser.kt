package com.andef.myvideos.presentation.parser

object DurationParser {
    fun parse(duration: String): String {
        val regex = Regex("PT(\\d+)M(\\d+)S")
        val matchResult = regex.find(duration)
        return StringBuilder().apply {
            append("Продолжительность: ")
            if (matchResult != null) {
                val minutes = matchResult.groupValues[1].toInt()
                val seconds = matchResult.groupValues[2].toInt()
                append("$minutes:$seconds")
            } else {
                append("неизвестно")
            }
        }.toString()
    }
}