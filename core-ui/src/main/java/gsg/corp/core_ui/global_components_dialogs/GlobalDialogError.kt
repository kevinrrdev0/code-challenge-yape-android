package gsg.corp.core_ui.global_components_dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import gsg.corp.core_ui.global_components_actions.GlobalTextButton
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall

@Composable
fun GlobalDialogError(
    show: Boolean,
    title:String = "Title",
    text: String = "Error generico",
    onConfirm: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { }) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { },
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Divider(color = Color.Red, thickness = 8.dp)
                    Icon(
                        imageVector = Icons.Outlined.ErrorOutline,
                        contentDescription = null,
                        Modifier
                            .size(84.dp)
                            .padding(8.dp),
                        tint = Color.Red
                    )
                    GlobalSpacerSmall()
                    Text(text = title,Modifier.padding(horizontal = 20.dp))
                    GlobalSpacerSmall()
                    Text(text = text,Modifier.padding(horizontal = 20.dp))
                    GlobalSpacerSmall()
                    GlobalTextButton("Ok", onClick = onConfirm, textColor = Color.Red)

                }
            }
        }
    }
}

@Preview
@Composable
fun DialogPreview() {
    GlobalDialogError(true, text = "error gerrerere peruien textni erneire reinrei rienre", onConfirm = {
        false
    })

}
