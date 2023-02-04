package gsg.corp.driver_presentation.screens.routes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core_ui.ColorGray

@Composable
fun RoutesScreen(
    viewModel: RoutesViewModel = hiltViewModel(),
) {

    viewModel.onGetRoutesTypes()

    val routesList = listOf("ajasj","dasd")

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(15.dp))
        ButtonRoutes(one = "Recolección", two = "Express", three = "   Zonas   " ,{},{})
        Spacer(modifier = Modifier.size(15.dp))
        LazyColumn() {
            itemsIndexed(
                items = routesList
            ) {index, r ->
                CardsRoutes(
                    ruteText = r,
                    district = "Surco",
                    client = "w",
                    cell = "w",
                    onClick = {}
                )
            }
        }
    }

}
@Composable
fun CardsRoutes(
    ruteText: String,
    district: String,
    client: String,
    cell: String,
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(19.dp),
        elevation = 7.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorGray)
            ,
        ) {
            Icon(
                painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.group_2 ),
                contentDescription = null ,
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)

            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 15.dp)
                ,
                Arrangement.SpaceAround
            ) {
                Text(text = "Rutas: $ruteText", fontWeight = FontWeight.SemiBold)
                Text(text = "Distrito: $district")
                Text(text = "Cliente: $client")
                Text(text = "Tlf: $cell")
            }
            Icon(
                painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.ic_baseline_content_copy_24 ),
                contentDescription = null ,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { }
            )
            IconButton(onClick = onClick, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(
                    painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.vector ),
                    contentDescription = null ,
                    modifier = Modifier
                        .padding()
                        .size(30.dp)
                )
            }

        }
    }
}

@Composable
fun ButtonRoutes(
    one : String,
    two : String,
    three : String,
    onClickZones: () -> Unit,
    onClickExpress: () -> Unit,
)
    {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val(OneRoutes, TwoRoutes , ThreeRoutes, IconMap) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(OneRoutes) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(TwoRoutes.start)
                }
        ){
            Button(
                onClick =  {},
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red
                ),
                shape = RoundedCornerShape(
                    50,
                    0,
                    0,
                    50
                ),
            ) {
                Text(text = one, color = Color.White)
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(TwoRoutes){
                    top.linkTo(parent.top)
                    start.linkTo(OneRoutes.end)
                    end.linkTo(ThreeRoutes.start)
                }
        ){
            Button(
                onClick = onClickExpress,
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                shape = RoundedCornerShape(
                    0,
                    0,
                    0,
                    0
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                ),
            ){
                Text(text = two)
            }
        }
        Box(
            modifier = Modifier
                .constrainAs(ThreeRoutes){
                    top.linkTo(parent.top)
                    start.linkTo(TwoRoutes.end)
                    end.linkTo(parent.end)
                }
        ){
            TextButton(
                onClick = onClickZones,
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
                shape = RoundedCornerShape(
                    0,
                    50,
                    50,
                    0
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                ),
            ) {
                Text(text = three)
            }
        }
        Box(modifier = Modifier
            .constrainAs(IconMap){
                top.linkTo(parent.top)
                start.linkTo(ThreeRoutes.end)
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.group),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(top = 5.dp, start = 9.dp)

                )
        }
        createHorizontalChain(OneRoutes, TwoRoutes , ThreeRoutes ,IconMap, chainStyle = ChainStyle.Packed)
    }
}