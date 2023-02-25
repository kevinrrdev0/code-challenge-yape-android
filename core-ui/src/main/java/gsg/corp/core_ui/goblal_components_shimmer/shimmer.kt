package gsg.corp.core_ui.goblal_components_shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.core_ui.ColorGray

@Composable
fun UserShimmer() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(12.dp))
        Row {
            Box(
                modifier = Modifier
                    .width(279.dp)
                    .height(29.dp)
                    .shimmerEffect()
            )
        }
        Spacer(
            modifier = Modifier
                .height(18.dp)

        )
    }
}

@Composable
fun MyRoutesShimmer() {
    Spacer(
        modifier = Modifier.height(29.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .width(121.dp)
                .height(19.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
        Column {
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(19.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(19.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(19.dp)
                    .shimmerEffect()
            )
        }
        Column() {
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(19.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(19.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(123.dp)
                    .height(19.dp)
                    .shimmerEffect()
            )
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, top = 15.dp),
    ) {
        Divider(
            Modifier
                .height(1.dp)
                .background(
                    Color(0xFFA5A4A4)
                )
        )
    }
}

@Composable
fun CardRoutesShimmer() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .padding(11.dp),
        elevation = 7.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorGray)
        ) {
           Box(modifier = Modifier
               .size(90.dp)
               .padding(10.dp)
               .align(Alignment.CenterVertically)
               .shimmerEffect()

           )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .padding(top = 15.dp)
            ) {
                Box(modifier = Modifier
                    .height(17.dp)
                    .width(158.dp)
                    .shimmerEffect())
                Spacer(modifier = Modifier.height(5.dp))
                Box(modifier = Modifier
                    .height(17.dp)
                    .width(158.dp)
                    .shimmerEffect())
                Spacer(modifier = Modifier.height(5.dp))
                Box(modifier = Modifier
                    .height(17.dp)
                    .width(158.dp)
                    .shimmerEffect())
                Spacer(modifier = Modifier.height(3.dp))
                Box(modifier = Modifier
                    .height(17.dp)
                    .width(158.dp)
                    .shimmerEffect())
            }
            Column(
                Modifier
                    .align(Alignment.CenterVertically)) {
              Box(modifier = Modifier
                  .size(32.dp)
                  .align(Alignment.End)
                  .shimmerEffect())
            }
            Column(
                Modifier
                    .align(Alignment.CenterVertically)) {
                Box(modifier = Modifier
                    .size(32.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.End)
                    .shimmerEffect())
            }

        }
    }
}

@Composable
fun RoutesCollectionShimmer() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(282.dp)
                    .height(30.dp)
                    .shimmerEffect()
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            //Screen data  Collection y mas
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
                    Box(
                        modifier = Modifier
                            .width(19.dp)
                            .height(17.dp)
                            .shimmerEffect()
                    )
                    Text(text = "Empresa:")
                }
                Row(Modifier.padding(start = 3.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .height(20.dp)
                                .width(100.dp)
                                .padding(start = 16.dp)
                                .shimmerEffect()
                        )
                        //Icon wsat y phone
                        Box(
                            modifier = Modifier
                                .height(30.dp)
                                .width(60.dp)
                                .align(Alignment.TopEnd)
                                .shimmerEffect()
                        )
                    }
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 3.dp)) {
                    Row {
                        Box(
                            modifier = Modifier
                                .height(18.dp)
                                .width(12.dp)
                                .shimmerEffect()
                        )
                        Text(text = "Direccion:")
                    }
                    Spacer(
                        modifier = Modifier
                            .height(3.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .height(20.dp)
                                .width(240.dp)
                                .padding(start = 16.dp)
                                .shimmerEffect()
                        )
                        Column(Modifier.fillMaxWidth()) {
                            //icono de copiar
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.End)
                                    .shimmerEffect()
                            )
                        }

                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(3.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 18.dp)
                ) {
                    Text(
                        text = "Referencia:"
                    )
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(200.dp)
                            .shimmerEffect()
                    )
                    Spacer(
                        modifier = Modifier
                            .height(3.dp)
                    )
                    Text(
                        text = "Producto:"
                    )
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(106.dp)
                            .shimmerEffect()
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 21.dp)
                ) {
                    Text(
                        text = "Cantidad:"
                    )
                    Spacer(
                        modifier = Modifier
                            .width(3.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(19.dp)
                            .shimmerEffect()
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
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
            }


        }
    //Borrar Screen si no es parte de su pantalla
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp))
        {
            Text(
                text = "Datos de Cobro",
                Modifier
                    .padding(start = 12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Box(modifier = Modifier
                    .size(22.dp)
                    .shimmerEffect())
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                )
                Text(
                    text = "Monto a cobrar",
                    modifier = Modifier
                        .padding(top = 1.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp)
            ){
                Box(modifier = Modifier
                    .height(20.dp)
                    .width(100.dp)
                    .shimmerEffect())
                Spacer(
                    modifier = Modifier.size(2.dp)
                )
                Text(text = "Metodo de Pago")
                Box(modifier = Modifier
                    .height(20.dp)
                    .width(265.dp)
                    .shimmerEffect())

                Spacer(
                    modifier = Modifier.size(2.dp)
                )
                Text(text = "Cuentas para las transferencias")
                Spacer(
                    modifier = Modifier.height(6.dp)
                )
                Button(onClick = { },
                    colors = ButtonDefaults
                        .buttonColors
                            (backgroundColor = Color.Red,
                            contentColor = Color.White),
                    shape = RoundedCornerShape(25),
                    modifier = Modifier
                        .width(125.dp)
                        .height(35.dp)
                ) {
                    Text(text = "Ver Cuentas",fontSize = 13.sp)
                }
            }

        }
    }
}
