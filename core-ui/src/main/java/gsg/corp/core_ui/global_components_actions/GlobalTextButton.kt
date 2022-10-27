package gsg.corp.core_ui.global_components_actions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.LocalSpacing

@Composable
fun GlobalTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.h5.copy(
        fontWeight = FontWeight.SemiBold,
        color = textColor
    )
) {
    TextButton(onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),
        content = {
            Text(
                text = text,
                style = textStyle,
                modifier = Modifier.padding(LocalSpacing.current.spaceExtraSmall)
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewGlobalTextButton() {
    Column(Modifier.padding(60.dp)) {
        GlobalTextButton("Test", onClick = { }, textColor = Color.Red)
    }

}