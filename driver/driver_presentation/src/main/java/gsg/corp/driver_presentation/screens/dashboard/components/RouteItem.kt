package gsg.corp.driver_presentation.screens.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import gsg.corp.core_ui.*
import gsg.corp.core_ui.global_components_actions.GlobalCall
import gsg.corp.core_ui.global_components_actions.GlobalWhatsApp
import gsg.corp.driver_presentation.screens.route.RouteUiState


@ExperimentalCoilApi
@Composable
fun RouteItem(
    routeUi: RouteUiState,
    onClick: () -> Unit,
    onGoDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current

    val colorState = when (routeUi.route.state) {
        "ENTREGADO" -> {
            ColorSuccess
        }
        "RECHAZADO" -> {
            ColorReject
        }
        "REPROGRAMADO" -> {
            ColorRescheduled
        }
        else -> {
            ColorRegister
        }
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() }
            .padding(end = spacing.spaceMedium)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
            horizontalArrangement = Arrangement.End) {
            Icon(
                imageVector = if (routeUi.isExpanded) {
                    Icons.Default.KeyboardArrowUp
                } else Icons.Default.KeyboardArrowDown,
                contentDescription = if (routeUi.isExpanded) {
                    "coll"
                } else "yolo"
            )
        }
        Column(Modifier.padding(top = 0.dp, bottom = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = null,
                        builder = {
                            crossfade(true)
                            error(gsg.corp.core.R.drawable.ic_logo_gsg)
                            fallback(gsg.corp.core.R.drawable.ic_logo_gsg)
                        }
                    ),
                    contentDescription = routeUi.route.product_detail,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(topStart = 5.dp))
                )
                Column() {
                    Text(text = "${routeUi.route.product_name}",
                        style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.width(160.dp),
                        overflow = TextOverflow.Ellipsis)
                    Text(text = "${routeUi.route.product_name}",
                        style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        modifier = Modifier.width(200.dp),
                        overflow = TextOverflow.Ellipsis)
                }

                GlobalCall(isEnable = routeUi.route.cel1.isNotBlank(),
                    phone = routeUi.route.cel1)
                GlobalCall(isEnable = routeUi.route.cel2.isNotBlank(),
                    phone = routeUi.route.cel2)


            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp))
            {
                Text(text = "Distrito: ${routeUi.route.district}")
                Text(text = "Direccion: ${routeUi.route.address}")
                Text(text = "Referencia: ${routeUi.route.address_ref}")
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "H: ${routeUi.route.date_route.substring(11)}")

                Card(modifier = Modifier
                    .padding(4.dp)
                    .clickable { },
                    backgroundColor = colorState,
                    elevation = 4.dp)//shape = RoundedCornerShape(8.dp)
                {
                    Row(modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "${routeUi.route.state}",
                            style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium)
                    }
                }

                GlobalWhatsApp(routeUi.route.telephone1.isNotBlank(),routeUi.route.telephone1)
            }
        }
        AnimatedVisibility(visible = routeUi.isExpanded) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp))
            {

                Row(modifier.fillMaxWidth()) {
                    Text(text = "Proveedor",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        color = Orange,
                        modifier = modifier.padding(end = 8.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = LightGray
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(text = "${routeUi.route.product_name}",
                            style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                        Text(text = "${routeUi.route.pay_method}",
                            style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                    }
                    Row() {
                        GlobalCall(
                            isEnable = routeUi.route.cel1.isNotBlank(),
                        phone = routeUi.route.cel1)
                        GlobalCall(
                            isEnable = routeUi.route.cel2.isNotBlank(),
                            phone = routeUi.route.cel2)
                    }

                }

                Column(Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
                    ) {
                    Text(text = "S/. ${routeUi.route.amount_collect}",
                        style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = "Detalle: ${routeUi.route.pay_method}",
                        style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium)
                }
                Row(Modifier
                    .fillMaxWidth()
                    .clickable { onGoDetail() },
                    horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Ir al detalle ",
                        style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium)
                    IconButton(
                        onClick = onGoDetail,
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

}