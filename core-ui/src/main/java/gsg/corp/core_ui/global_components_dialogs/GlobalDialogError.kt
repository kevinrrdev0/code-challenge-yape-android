package gsg.corp.core_ui.global_components_dialogs

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import gsg.corp.core_ui.DarkRedGsg
import gsg.corp.core_ui.RedGsg

@Composable
fun GlobalDialogError(
    show: Boolean,
    text: String = "Error generico",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
//        AlertDialog(
//            onDismissRequest = onDismiss,
//            confirmButton = {
//                TextButton(onClick = onConfirm)
//                { Text(text = "Ok, revisaré.", color = RedGsg) }
//            },
//            title = {
//                Row() {
//                    Icon(
//                        imageVector = Icons.Outlined.NearbyError,
//                        contentDescription = null,
//                        tint = DarkRedGsg,
//                        modifier = Modifier
//                            .size(36.dp)
//                            .padding(0.dp)
//                    )
//                    Icon(
//                        imageVector = Icons.Outlined.RunningWithErrors,
//                        contentDescription = null,
//                        tint = DarkRedGsg,
//                        modifier = Modifier
//                            .size(36.dp)
//                            .padding(0.dp)
//                    )
//                    Icon(
//                        imageVector = Icons.Outlined.ReportGmailerrorred,
//                        contentDescription = null,
//                        tint = DarkRedGsg,
//                        modifier = Modifier
//                            .size(36.dp)
//                            .padding(0.dp)
//                    )
//                    Icon(
//                        imageVector = Icons.Outlined.ErrorOutline,
//                        contentDescription = null,
//                        tint = DarkRedGsg,
//                        modifier = Modifier
//                            .size(36.dp)
//                            .padding(0.dp)
//                    )
//                }
//            },
//            text = { Text(text = text, Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
//            modifier = Modifier.padding(0.dp)
//        )

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
                    Divider(color = Color.Red, thickness = 6.dp)
                    Icon(
                        imageVector = Icons.Outlined.MapsHomeWork,
                        contentDescription = null,
                        Modifier.padding(0.dp)
                    )
                    Text(text = text, Modifier.padding(start = 4.dp))

                    Row(
                        modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "OK")

                        }
                    }
                }


            }
        }

    }
}

//text
//button color naranja red
@Preview
@Composable
fun DialogPreview() {
    GlobalDialogError(true, text = "error gerrerere", onConfirm = {
        false
    }, onDismiss = {
        false
    })

}
