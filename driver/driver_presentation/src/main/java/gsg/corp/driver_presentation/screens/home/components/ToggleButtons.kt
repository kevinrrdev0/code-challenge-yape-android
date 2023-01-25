package gsg.corp.driver_presentation.screens.home.parts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import gsg.corp.core.R

@Composable
fun ToggleButtons(today: String, week: String, month: String ) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val(OneRoutes, TwoRoutes , ThreeRoutes) = createRefs()
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
                Icon(
                    painterResource(id = R.drawable.ic_baseline_check_24),
                    contentDescription = null, modifier = Modifier.size(17.dp),
                    tint = Color.White
                )
                Text(text = today, color = Color.White)
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
                onClick = { },
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
                Text(text = week)
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
                onClick = {  },
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
                Text(text = month)
            }
        }
        createHorizontalChain(OneRoutes, TwoRoutes , ThreeRoutes , chainStyle = ChainStyle.Packed)
    }
}
