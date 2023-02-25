package gsg.corp.driver_presentation.screens.routes.routes_detail.undelivered

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Preview
@Composable
fun ExpressUndeliveredScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "Express RUT-EXP1A",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            ExpressContent()
            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            Divider(
                Modifier
                    .height(1.dp)
                    .background(Color(0xFFA5A4A4))
            )
            ExpresssFoto("estado")
            ExpresssFoto("cobro")
        }

            Column(modifier = Modifier.fillMaxWidth(), Arrangement.Bottom) {
                Row(
                    modifier = Modifier
                        .padding(start = 29.dp, end = 29.dp),
                ) {
                    ButtonFin2(
                        "Cancelar",
                        modifier = Modifier
                            .width(115.dp)
                    )
                    Spacer(modifier = Modifier.width(94.dp))
                    ButtonFin3(
                        "Guardar",
                        modifier = Modifier
                            .width(115.dp)

                    )
                }
            }
    }
}
@Composable
fun ExpressContent() {
    var isLoding by rememberSaveable { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Entregado", "Rechazado", "Reprogramado", "En ruta")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Datos del recojo"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row {
            Icon(painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.carbon_enterprise), contentDescription = null)
            Text(text ="Empresa")
        }
        Spacer(modifier = Modifier
            .height(3.dp)
        )
        Row {

            Text(
                text = "FRIS SPORTRS",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 25.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(170.dp)
            )
        }

        Row(modifier = Modifier) {
            Icon(
                painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.vector__3_),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Cliente")
        }
        Row {
            Text(
                text = "kevin Reyes",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 25.dp)
            )
        }
        Column(
            Modifier
                .padding(start = 21.dp)
                .fillMaxWidth()) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .width(220.dp)
                    .onGloballyPositioned { coordinates ->
                        //this value is used to assign to the DropDown the same widthT
                        textfieldSize = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red,
                    backgroundColor = Color.Transparent,
                    leadingIconColor = Color.Red,
                    cursorColor = Color.Red,
                    focusedLabelColor = Color.Red,
                ),
                label = { Text("Estado del pedido") },
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textfieldSize.width.toDp()})

            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        if (label == "Reprogramado") {
                            isLoding = true
                            selectedText = label
                            expanded = false
                        } else {
                            isLoding = false
                            selectedText = label
                            expanded = false
                        }
                    }
                    ){
                        Text(text = label)
                    }
                }
            }
        }
        if (isLoding){
            RescheduledTextField()
        }
    }
}

@Composable
fun RescheduledTextField() {
    var selectedText by remember { mutableStateOf("") }
    Column(
        Modifier
            .padding(start = 20.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier.width(230.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                backgroundColor = Color.Transparent,
                leadingIconColor = Color.Red,
                cursorColor = Color.Red,
                focusedLabelColor = Color.Red,
            ),
            label = {Text("Fecha de la reprogramacion")}
        )
    }
}

@Composable
fun ExpresssFoto(foto : String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 47.dp, top = 16.dp)
    ){
        Text(
            text = "Foto $foto del pedido"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row() {
            Image(painter =painterResource(id = gsg.corp.driver_presentation.R.drawable.image) , contentDescription = null
            )
            Column(
                Modifier,
                Arrangement.SpaceBetween,
                Alignment.CenterHorizontally
            ){
                Row(
                    Modifier
                        .padding(start = 25.dp, top = 24.dp)
                ){
                    Icon(
                        painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.camera),
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )
                    Text(
                        text = "Tomar foto",
                        fontWeight =
                        FontWeight.SemiBold
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                )
                Row(
                    Modifier
                        .padding(start = 25.dp, end = 24.dp)
                ){
                    Icon(
                        painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.galleryexport),
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                    Text(
                        text = "Galeria",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
@Composable
fun ButtonFin2(text : String,modifier: Modifier= Modifier ) {
    TextButton(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color.Red
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier

    ) {
        Icon(painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.ic_baseline_close_24), contentDescription = null)
        Text(text = text)
    }
}
@Composable
fun ButtonFin3(text : String, modifier: Modifier = Modifier) {
    TextButton(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = Color(0xFF41DA26)
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.ic_baseline_check_24),
            contentDescription = null)
        Text(text = text)
    }
}