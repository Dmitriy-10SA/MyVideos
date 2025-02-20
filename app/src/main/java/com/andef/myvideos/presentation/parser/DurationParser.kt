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
                if (minutes >= 10) {
                    append("$minutes:")
                } else {
                    append("0$minutes:")
                }
                if (seconds >= 10) {
                    append(seconds)
                } else {
                    append("0$seconds")
                }
            } else {
                append("неизвестно")
            }
        }.toString()
    }
}