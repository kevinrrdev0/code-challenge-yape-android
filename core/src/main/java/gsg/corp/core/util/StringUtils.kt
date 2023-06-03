package gsg.corp.core.util

import java.util.Locale

fun String.toUpperCaseLocale(): String {
    val locale = Locale("es", "PE")
    return this.uppercase(locale)
}