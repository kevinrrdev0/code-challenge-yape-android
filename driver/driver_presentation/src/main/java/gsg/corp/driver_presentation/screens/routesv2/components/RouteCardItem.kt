package gsg.corp.driver_presentation.screens.routesv2.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gsg.corp.core.util.toUpperCaseLocale
import gsg.corp.core_ui.*
import gsg.corp.core_ui.R
import gsg.corp.core_ui.global_components_actions.GlobalCall
import gsg.corp.core_ui.global_components_actions.GlobalWhatsApp
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerLarge
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMidRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowLarge
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowMedium
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowMediumLarge
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextBody2
import gsg.corp.core_ui.global_components_texts.TextSubtitle2
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_presentation.screens.routesv2.RouteUiState
import java.util.Locale

@Preview(showBackground = true, heightDp = 600, widthDp = 600)
@Composable
fun PreviewRouteCardItem() {
    Column {
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

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RouteCardItem(
    routeUi: RouteUiState,
    onClick: () -> Unit,
    onGoDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    val routeItem = routeUi.route

    val (colorState, colorDotState) = when (routeUi.route.st_code) {
        "ENTREGADO" -> {
            Pair(ColorSuccess, ColorDotSuccess)
        }

        "RECHAZADO" -> {
            Pair(ColorReject, ColorDotReject)
        }

        "REPROGRAMADO" -> {
            Pair(ColorRescheduled, ColorDotRescheduled)
        }

        "PENDIENTE" -> {
            Pair(ColorDefault, ColorDotDefault)
        }

        else -> {
            Pair(ColorInfo, ColorDotInfo)
        }
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp, modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp), onClick = onClick
    ) {
        Column {
            Column(modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        CommonRowCardItem(R.drawable.ic_bar_code, routeItem.code_tracking, true)
                        GlobalSpacerRowMediumLarge()
                        StatusRoute(colorState, colorDotState, routeItem.st_code)
                    }

                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = if (routeUi.isExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                GlobalExtraSpacerSmall()
                CommonRowCardItem(R.drawable.ic_client, routeItem.full_name.toUpperCaseLocale())
                GlobalExtraSpacerSmall()
                CommonRowCardItem(R.drawable.ic_location, routeItem.district.toUpperCaseLocale())
                GlobalExtraSpacerSmall()
                CommonRowCardItem(R.drawable.ic_map_ref, routeItem.address.toUpperCaseLocale())
                AnimatedVisibility(visible = routeUi.isExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        GlobalExtraSpacerSmall()
                        CommonRowCardItem(
                            R.drawable.ic_chat_message,
                            routeItem.observation
                        )
                        GlobalExtraSpacerSmall()
                        CommonRowCardItem(
                            R.drawable.ic_box_package,
                            "${routeItem.number_packages} / ${routeItem.product}"
                        )
                        GlobalExtraSpacerSmall()
                        CommonRowCardItem(
                            R.drawable.ic_product_detail,
                            routeItem.productDetail
                        )
                        GlobalExtraSpacerSmall()
                        CommonRowCardItem(
                            R.drawable.ic_wallet,
                            "${routeItem.code_pay_method} / ${routeItem.pay_amount}"
                        )


                        GlobalExtraSpacerSmall()
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(id = R.drawable.ic_call_phone),
                                contentDescription = "Phone"
                            )
                            GlobalExtraSpacerSmallRow()
                            PhoneNumberActions(routeItem.telephone)
                            if (routeItem.other_telephone.isNotEmpty()) {
                                GlobalSpacerRowSmall()
                                PhoneNumberActions(routeItem.other_telephone)
                            }
                        }
                        GlobalSpacerSmall()
                    }
                }
            }

            if (!routeUi.isExpanded) {
                GlobalSpacerSmall()
            }
            Divider(thickness = 1.dp, color = RedGsg)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onGoDetail()
                    }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextBody(text = "Ir a detalle", textColor = RedGsg, boldHighlighting = true)
                GlobalExtraSpacerSmallRow()
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_login_action),
                    contentDescription = "login action",
                    tint = RedGsg
                )
            }
        }
    }
}

@Composable
fun StatusRoute(colorState: Color, colorDot: Color, text: String) {
    Card(
        backgroundColor = colorState,
        shape = RoundedCornerShape(4.dp)
    )
    {
        Row(
            modifier = Modifier.padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(6.dp),
                painter = painterResource(id = R.drawable.ic_dot),
                contentDescription = "login action",
                tint = colorDot
            )
            GlobalExtraSpacerSmallRow()
            TextBody2(text = text, boldHighlighting = true)
        }
    }
}

@Composable
fun PhoneNumberActions(
    phoneNumber: String,
    modifier: Modifier = Modifier,
    textColor: Color = TextBlack,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    boldHighlighting: Boolean = false
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextBody(
            text = phoneNumber,
            modifier = modifier,
            textColor = textColor,
            textAlign = textAlign,
            fontWeight = fontWeight,
            boldHighlighting = boldHighlighting
        )
        GlobalSpacerRowSmall()
        GlobalCall(phone = phoneNumber, modifier = Modifier.size(22.dp))
        GlobalSpacerRowSmall()
        GlobalWhatsApp(phone = phoneNumber, modifier = Modifier.size(22.dp))
    }
}

@Composable
fun CommonRowCardItem(idDrawable: Int, text: String, boldText: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = idDrawable),
            contentDescription = null
        )
        GlobalExtraSpacerSmallRow()
        TextBody(
            text = text, maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            boldHighlighting = boldText
        )
    }
}