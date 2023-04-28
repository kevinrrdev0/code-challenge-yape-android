package gsg.corp.driver_presentation.screens.routesv2.components

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import gsg.corp.core_ui.*
import gsg.corp.core_ui.global_components_actions.GlobalCall
import gsg.corp.core_ui.global_components_actions.GlobalWhatsApp
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMidRow
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextSubTitle
import gsg.corp.core_ui.global_components_texts.TextTitle
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_presentation.screens.routesv2.RouteUiState

@Preview(showBackground = true)
@Composable
fun PreviewRouteCardItem() {
    RouteCardItem(
        RouteUiState(
            isExpanded = true,
            route = Route(
                st_code = "ENTREGADO",
                cost = "500.00",
                advance = "50.00",
                pay_amount = "450.00",
                code_pay_method = "EFECTIVO",
                product = "Zapatillas talla 44 y medio ",
                telephone = "997479520",
                other_telephone = "997881123",
                full_name = "Kevin Carlos Quispe Mamani",
                district = "Los olivos",
                address = "Jr. Hualcan 2323, Urb. las claritas",
                address_reference = "cerca al parque la libertad"
            )
        ), onClick = {}, onGoDetail = {})
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RouteCardItem(
    routeUi: RouteUiState,
    onClick: () -> Unit,
    onGoDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val routeItem = routeUi.route

    val colorState = when (routeUi.route.st_code) {
        "ENTREGADO" -> {
            ColorSuccess
        }
        "RECHAZADO" -> {
            ColorReject
        }
        "REPROGRAMADO" -> {
            ColorRescheduled
        }
        "PENDIENTE" -> {
            ColorDefault
        }
        else -> {
            ColorInfo
        }
    }

    Card(elevation = 10.dp, modifier = modifier
        .fillMaxWidth()
        .padding(8.dp, 8.dp, 8.dp, 0.dp), onClick = onClick) {
        Column(modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row{
                    TextTitle(title = routeItem.district)
                    GlobalSpacerMidRow()
                    Card(modifier = Modifier
                        .clickable { },
                        backgroundColor = colorState,
                        elevation = 2.dp)//shape = RoundedCornerShape(8.dp)
                    {
                        Row(modifier = Modifier.padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            TextSubTitle(subTile = routeUi.route.st_code)
                        }
                    }
                }

                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = if (routeUi.isExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (routeUi.isExpanded) {
                        "coll"
                    } else "yolo"
                )
            }

            TextBody(body = routeItem.address)
            TextBody(body = "Ref: ${routeItem.address_reference}")
            TextBody(body = "Cli.: ${routeItem.full_name}")
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextBody(body = "Tlf1.: ${routeItem.telephone}")
                Spacer(modifier = Modifier.width(4.dp))
                GlobalCall(phone = routeItem.telephone, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(4.dp))
                GlobalWhatsApp(phone = routeItem.telephone, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(16.dp))
                TextBody(body = "Tlf2.: ${routeItem.other_telephone}")
                Spacer(modifier = Modifier.width(4.dp))
                GlobalCall(phone = routeItem.other_telephone, modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.width(4.dp))
                GlobalWhatsApp(phone = routeItem.telephone, modifier = Modifier.size(22.dp))
            }
            TextBody(body = "Prod.: ${routeItem.product}")
            AnimatedVisibility(visible = routeUi.isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextBody(body = "Metodo de Pago.: ${routeItem.code_pay_method}",fontWeight = FontWeight.Bold)
                    TextBody(body = "Monto a Cobrar: S/ ${routeItem.pay_amount}", fontWeight = FontWeight.Bold)
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { onGoDetail() },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) {
               TextBody(body = "Ir a detalle")
                IconButton(
                    modifier=Modifier.size(16.dp),
                    onClick = onGoDetail
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = null
                    )
                }
            }
        }

    }

}