package gsg.corp.driver_presentation.screens.routes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.ColorGray
import gsg.corp.driver_presentation.R

@Composable
fun CardRoute(
    ruteText: String,
    district: String,
    client: String,
    cell: String,
    onClick: () -> Unit
) {
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
            Icon(
                painter = painterResource(id = R.drawable.group_2 ),
                contentDescription = null ,
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .padding(top = 15.dp)
            ) {
                Text(text = "Rutas: $ruteText", fontWeight = FontWeight.SemiBold)
                Text(text = "Distrito: $district")
                Text(text = "Cliente: $client")
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "Tlf: $cell")
            }
            Column(
                Modifier
                    .align(Alignment.CenterVertically)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_content_copy_24 ),
                    contentDescription = null ,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.End)
                        .clickable { }
                )
            }
            Column(
                Modifier
                    .align(Alignment.CenterVertically)){
                IconButton(onClick = onClick, modifier = Modifier.align(Alignment.End)) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector ),
                        contentDescription = null ,
                        modifier = Modifier
                            .padding()
                            .size(28.dp)
                    )
                }
            }
        }
    }
}