package gsg.corp.ruterito.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.global_components_actions.*
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall

@Preview(name = "Preview Global Buttons", showBackground = true, widthDp = 350)
@Composable
fun PreviewButtons() {
    Column(Modifier.padding(16.dp)) {
        GlobalButton(text = "prueba", onClick = { /*TODO*/ })
        GlobalSpacerSmall()
        GlobalCall(phone = "994")
        GlobalSpacerSmall()
        GlobalCamera(text = "Selec", onImageSelect = { /*TODO*/ })
        GlobalSpacerSmall()
        GlobalOutLineButton(text = "Pureba outline button", onClick = { /*TODO*/ })
        GlobalSpacerSmall()
        GlobalSelect(text = "select",
            hint = "Pruebita",
            inputText = "aea",
            selectedField = listOf(CommonType("hola",1)),
            msgError = "err",
            onFieldSelected = {_,_ -> /*TODO*/})
        GlobalSpacerSmall()
    }
}
