package gsg.corp.core_ui.global_components_texts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(title: String,modifier: Modifier = Modifier,fontWeight: FontWeight= FontWeight.Bold) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6.copy(fontSize = 13.sp),
        textAlign = TextAlign.Start,
        fontWeight = fontWeight,
        maxLines = 1,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextSubTitle(subTile: String, modifier: Modifier = Modifier,fontWeight: FontWeight= FontWeight.Bold) {
    Text(
        text = subTile,
        style = MaterialTheme.typography.subtitle1.copy(fontSize = 12.sp),
        textAlign = TextAlign.Start,
        fontWeight = fontWeight,
        maxLines = 1,
        modifier = modifier,
        color = Color.White,
        overflow = TextOverflow.Ellipsis
    )
}


@Composable
fun TextBody(body: String, modifier: Modifier = Modifier, fontWeight: FontWeight= FontWeight.Normal) {
    Text(
        text = body,
        style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
        textAlign = TextAlign.Start,
        fontWeight = fontWeight,
        maxLines = 1,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis
    )
}