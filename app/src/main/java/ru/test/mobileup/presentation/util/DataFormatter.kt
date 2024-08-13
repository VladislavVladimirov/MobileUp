package ru.test.mobileup.presentation.util

import java.math.BigDecimal
import java.math.RoundingMode

object DataFormatter {

    fun formatPrice(price: Double, currency: String): String {
        if (price < 1.0) {
            val formattedPrice = BigDecimal(price).setScale(4, RoundingMode.HALF_UP).toString()
            val decimalPart = formattedPrice.split(".")[1]
            val shouldAddEllipsis = decimalPart.all { it == '0' }
            val formattedResult = if (shouldAddEllipsis) {
                "$formattedPrice..."
            } else {
                formattedPrice
            }
            return " $currency $formattedResult"
        } else {
            val formattedPrice = BigDecimal(price).setScale(2, RoundingMode.HALF_UP).toString()
            val parts = formattedPrice.split(".")
            val integerPart = parts[0]
            val decimalPart = parts.getOrElse(1) { "" }
            val formattedIntegerPart = StringBuilder()
            for (i in integerPart.indices) {
                formattedIntegerPart.append(integerPart[i])
                if ((integerPart.length - i - 1) % 3 == 0 && i != integerPart.length - 1) {
                    formattedIntegerPart.append(",")
                }
            }
            val formattedResult = if (decimalPart == "00") {
                formattedIntegerPart.toString()
            } else {
                "$formattedIntegerPart.$decimalPart"
            }
            return " $currency $formattedResult"
        }
    }

    fun ellipsize(input: String): String {
        val maxLength = 32
        val ellipsis = "..."

        return if (input.length > maxLength) {
            input.substring(0, maxLength - ellipsis.length) + ellipsis
        } else {
            input
        }
    }

    fun extractLinks(text: String): Map<String, String> {
        val pattern = """<a href="(.*?)">(.*?)</a>""".toRegex()
        val matches = pattern.findAll(text)

        val result = mutableMapOf<String, String>()
        for (match in matches) {
            val link = match.groupValues[1]
            val title = match.groupValues[2]
            result[title] = link
        }

        return result
    }
    fun removeLinks(input: String): String {
        val regex = """<a href="[^"]+">([^<]+)</a>""".toRegex()
        return regex.replace(input) { matchResult ->
            matchResult.groups[1]?.value ?: ""
        }
    }
}