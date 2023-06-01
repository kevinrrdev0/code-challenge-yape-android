package gsg.corp.core_ui.global_components_texts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import gsg.corp.core_ui.TextBlack
import gsg.corp.core_ui.setSomeWordsToBold

@Composable
fun TextHeadline(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        style = MaterialTheme.typography.h1
    )
}
@Composable
fun TextHeadline2(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        style = MaterialTheme.typography.h2
    )
}
@Composable
fun TextHeadline3(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        style = MaterialTheme.typography.h3
    )
}
@Composable
fun TextHeadline4(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        style = MaterialTheme.typography.h4
    )
}

@Composable
fun TextSubtitle(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        style = MaterialTheme.typography.subtitle1
    )
}
@Composable
fun TextSubtitle2(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        style = MaterialTheme.typography.subtitle2
    )
}

@Composable
fun TextBody(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 10,
    fontWeight: FontWeight = FontWeight.Normal,
    textDecoration: TextDecoration = TextDecoration.None,
    boldHighlighting: Boolean = false,
    boldText: List<String> = emptyList(),
    overflow: TextOverflow = TextOverflow.Clip
) {
    if (boldHighlighting) {
        Text(
            text = setSomeWordsToBold(text, boldText.ifEmpty { listOf(text) }),
            modifier = modifier,
            color = textColor,
            textAlign = textAlign,
            maxLines=maxLines,
            textDecoration = textDecoration,
            overflow=overflow,
            style = MaterialTheme.typography.body1.copy(fontWeight = fontWeight)
        )
    }else{
        Text(
            text = text,
            modifier = modifier,
            color = textColor,
            textAlign = textAlign,
            maxLines=maxLines,
            overflow=overflow,
            style = MaterialTheme.typography.body1.copy(fontWeight = fontWeight)
        )
    }

}

@Composable
fun TextBody2(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    textDecoration: TextDecoration = TextDecoration.None,
    boldHighlighting: Boolean = false,
    boldText: List<String> = emptyList()
) {
    if (boldHighlighting) {
        Text(
            text = setSomeWordsToBold(text, boldText.ifEmpty { listOf(text) }),
            modifier = modifier,
            color = textColor,
            textAlign = textAlign,
            textDecoration = textDecoration,
            style = MaterialTheme.typography.body2.copy(fontWeight = fontWeight)
        )
    } else {
        Text(
            text = text,
            modifier = modifier,
            color = textColor,
            textAlign = textAlign,
            textDecoration = textDecoration,
            style = MaterialTheme.typography.body2.copy(fontWeight = fontWeight)
        )
    }

}

@Composable
fun TextCaption(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    boldHighlighting: Boolean = false,
    boldText: List<String> = emptyList()
) {
    if (boldHighlighting) {
        Text(
            text = setSomeWordsToBold(text, boldText),
            modifier = modifier,
            color = textColor,
            textAlign = textAlign,
            maxLines = maxLines,
            style = MaterialTheme.typography.caption.copy(fontWeight = fontWeight)
        )
    } else {
        Text(
            text = text,
            modifier = modifier,
            color = textColor,
            textAlign = textAlign,
            maxLines = maxLines,
            style = MaterialTheme.typography.caption.copy(fontWeight = fontWeight)
        )
    }
}

@Preview(name = "Preview Text", showBackground = true, widthDp = 650)
@Composable
private fun PreviewText() {
        Column() {
            TextHeadline(
                "Ruterito Text TextHeadline1"
            )
            TextHeadline2(
                "Ruterito Text TextHeadline2"
            )
            TextHeadline3(
                "Ruterito Text TextHeadline3"
            )
            TextHeadline4(
                "Ruterito Text TextHeadline4"
            )
            TextSubtitle(
                "Ruterito Text TextSubtitle1"
            )
            TextSubtitle2(
                "Ruterito Text TextSubtitle2"
            )
            TextBody(
                "Ruterito Text TextBody1",
                fontWeight = FontWeight.Bold
            )
            TextBody2(
                "Ruterito Text TextBody2"
            )
            TextCaption("Ruterito Text TextCaption", boldHighlighting = true, boldText = listOf("Ruterito"))
        }

}