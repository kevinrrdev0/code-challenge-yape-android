package gsg.corp.ruterito.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import gsg.corp.core.R
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
            selectedField = listOf(CommonType("hola", 1)),
            msgError = "err",
            onFieldSelected = { _, _ -> /*TODO*/ })
        GlobalSpacerSmall()

    }
}

//
//@Preview(name = "Preview Detail Client", showBackground = true, widthDp = 450)
//@Composable
//fun PreviewDetailClient() {
//    Column(
//        Modifier
//            .padding(8.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//
//        CardClient()
//
//        Column(
//            Modifier
//                .padding(8.dp, 16.dp, 8.dp, 0.dp)
//        ) {
//            TextTitleGroupForm("Location")
//            DividerForm(Modifier.padding(0.dp,8.dp,0.dp,8.dp))
//            Column(Modifier.padding(8.dp)) {
//                TextTitleForm("Direccion")
//                TextSubTitleForm(text = "Av. Angelica Gamarra cuadra 5 - Los Olivos")
//                GlobalSpacerSmall()
//                TextTitleForm("Referencia")
//                TextSubTitleForm(text = "Frente al parque alfredo rebaza acosta a 2 cuardras")
//            }
//            TextTitleGroupForm("Packquete",Modifier.padding(0.dp,16.dp,0.dp,0.dp))
//            DividerForm(Modifier.padding(0.dp,8.dp,0.dp,8.dp))
//            Column(Modifier.padding(8.dp)) {
//                TextTitleForm("Producto")
//                TextSubTitleForm(text = "Zapatillas talla 45 modelo nike jordan af1")
//                GlobalSpacerSmall()
//                TextTitleForm("Detalle")
//                TextSubTitleForm(text = "El producto es delicado porfavor entregar derecho")
//            }
//            TextTitleGroupForm("Pagos",Modifier.padding(0.dp,16.dp,0.dp,0.dp))
//            DividerForm(Modifier.padding(0.dp,8.dp,0.dp,8.dp))
//            Column(Modifier.padding(8.dp)) {
//                Row (Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
//                    TextSubTitleForm(text = "Metodo de Pago")
//                    TextSubTitleForm(text = "TRANSFERENCIA")
//                }
//                GlobalSpacerSmall()
//                Row (Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
//                    TextSubTitleForm(text = "Monto a cobrar")
//                    TextSubTitleForm(text = "S/. 189.00")
//                }
//                GlobalSpacerSmall()
//                TextTitleForm("Detalle de pago")
//                TextSubTitleForm(text = "Cancela con 100 soles llevar vuelto")
//            }
//            TextTitleGroupForm("Contacto Cliente",Modifier.padding(0.dp,16.dp,0.dp,0.dp))
//            DividerForm(Modifier.padding(0.dp,8.dp,0.dp,8.dp))
//
//            Row {
//                ButtonContact()
//                ButtonContact()
//            }
//
//            TextTitleGroupForm("Contacto Proveedor",Modifier.padding(0.dp,16.dp,0.dp,0.dp))
//            DividerForm(Modifier.padding(0.dp,8.dp,0.dp,8.dp))
//
//
//
//
//            GlobalSpacerSmall()
//            DividerForm(Modifier.padding(0.dp,8.dp,0.dp,8.dp))
//
//
//
//        }
//
//
//    }
//
//}
//
//@Preview(name = "Preview Button", showBackground = true, widthDp = 450)
//@Composable
//fun ButtonContact() {
//
//    Card(
//        modifier = Modifier
//            .width(90.dp)
//            .padding(8.dp)
//            .clickable { },
//        backgroundColor = Color.Green,
//        elevation = 10.dp
//    ) {
//        Column(Modifier.padding(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally) {
//            Image(
//                painter = rememberImagePainter(
//                    data = null,
//                    builder = {
//                        crossfade(true)
//                        error(R.drawable.ic_call)
//                        fallback(R.drawable.ic_call)
//                    }
//                ),
//                contentDescription = "Client",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(36.dp)
//                    .clip(RoundedCornerShape(topStart = 16.dp))
//            )
//            Text(text = "Llamar 1",
//            style = MaterialTheme.typography.subtitle1.copy(fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.W900))
//        }
//    }
//
//}
//
//
//@Composable
//fun CardClient() {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { },
//        elevation = 10.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(8.dp),
//            horizontalAlignment = Alignment.Start
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = rememberImagePainter(
//                        data = null,
//                        builder = {
//                            crossfade(true)
//                            error(R.drawable.ic_logo_gsg)
//                            fallback(R.drawable.ic_logo_gsg)
//                        }
//                    ),
//                    contentDescription = "Client",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(64.dp)
//                        .clip(RoundedCornerShape(topStart = 16.dp))
//                )
//                Column(Modifier.padding(8.dp,0.dp,0.dp,0.dp)) {
//                    Text(
//                        buildAnnotatedString {
//
//                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
//                                append("Kevin Jackson Reyes Rojas ")
//                            }
//                            append(" - Cliente")
//                        }
//                    )
//
//
//                        Text(text = "Prioridad: Normal")
//
//                        Text(text = "Fecha: 24-05-2022 11:13 ")
//
//                }
//
//
//
//            }
//        }
//    }
//}
//
//
//@Composable
//fun TextTitleGroupForm(text:String,modifier: Modifier = Modifier) {
//    Text(
//        text = text,
//        style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.W900),
//        textAlign = TextAlign.Start,
//        fontWeight = FontWeight.Medium,
//        modifier = modifier
//    )
//}
//
//@Composable
//fun TextTitleForm(text:String) {
//    Text(
//        text = text,
//        style = MaterialTheme.typography.subtitle1.copy(fontSize = 12.sp, color = Color.LightGray),
//        textAlign = TextAlign.Start,
//        fontWeight = FontWeight.Medium
//    )
//}
//
//@Composable
//fun TextSubTitleForm(text:String) {
//    Text(
//        text = text,
//        style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp, color = Color.Black),
//        textAlign = TextAlign.Start,
//        fontWeight = FontWeight.Medium
//    )
//}
//
//
//@Composable
//fun DividerForm(modifier: Modifier = Modifier.padding(0.dp,4.dp)) {
//    Divider(
//        color = Color.LightGray,
//        modifier = modifier,
//        thickness = 1.dp
//    )
//}
