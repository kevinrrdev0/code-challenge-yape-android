package gsg.corp.core_ui.global_components_actions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.LocalSpacing

@Composable
fun GlobalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold, color = Color.White),
    backgroundColor : Color = Color.Red
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(LocalSpacing.current.spaceExtraSmall)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewGlobalButton(){
    Column(Modifier.padding(60.dp)) {
        GlobalButton("Test", onClick = { })
    }

}