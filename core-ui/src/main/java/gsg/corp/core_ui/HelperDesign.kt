package gsg.corp.core_ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

fun setSomeWordsToBold(fullText: String, boldWords: List<String>): AnnotatedString {
    return buildAnnotatedString {
        var startIndex = 0
        boldWords.forEach { boldWord ->
            val index = fullText.indexOf(boldWord, startIndex, true)
            if (index != -1) {
                append(fullText.substring(startIndex, index))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(fullText.substring(index, index + boldWord.length))
                }
                startIndex = index + boldWord.length
            }
        }
        if (startIndex < fullText.length) {
            append(fullText.substring(startIndex))
        }
    }
}