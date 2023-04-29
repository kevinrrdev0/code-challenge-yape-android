package gsg.corp.driver_presentation.screens.routesv2.update_route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.core.util.UiEvent
import gsg.corp.core_ui.global_components_actions.DatePickerComponent
import gsg.corp.core_ui.global_components_actions.ImagePicker
import gsg.corp.core_ui.global_components_inputs.GlobalInput
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMid
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.driver_presentation.R
import gsg.corp.driver_presentation.screens.routesv2.components.CustomDropDown
import gsg.corp.driver_presentation.screens.routesv2.routes_detail.*
import kotlinx.coroutines.flow.Flow

@Composable
fun UpdateStateOrderScreen(
    state: UpdateStateOrderState,
    uiEvent: Flow<UiEvent>,
    onEvent: (UpdateStateOrderEvent) -> Unit,
    onNavigateUp: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                }
                is UiEvent.ShowSnackBar -> {
                }
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (!state.isLoading) {
            BodyUpdateRouteDetail(state, onEvent = { onEvent(it) }, onNavigateUp = onNavigateUp)
        }
    }
    BoxLoadAnimation(state.isLoading)
}

@Composable
fun BodyUpdateRouteDetail(
    state: UpdateStateOrderState,
    onEvent: (UpdateStateOrderEvent) -> Unit,
    onNavigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Detalle de Ruta ${state.routeDetail.code_tracking}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        GlobalSpacerSmall()
        RouteDetailOrder(state.routeDetail)
        GlobalSpacerSmall()
        RoutePayOrder(state.routeDetail)
        GlobalSpacerSmall()
        UpdateStateOrder(state, onEvent = { onEvent(it) })
        GlobalSpacerMid()
        ButtonsActionsUpdateState(onEvent = onEvent, onNavigateUp = onNavigateUp)
    }
}

@Composable
fun UpdateStateOrder(state: UpdateStateOrderState, onEvent: (UpdateStateOrderEvent) -> Unit) {
    CustomDropDown(
        state.listState,
        state.state,
        onEventDropDown = { id, name -> onEvent(UpdateStateOrderEvent.OnStateSelected(id, name)) })

    if (state.state.name == "REPROGRAMADO") {
        GlobalSpacerSmall()
        DatePickerComponent(
            date = state.dateRescheduled,
            label = "Fecha de la reprogramacion",
            onDateSelected = { firstFormat, _ ->
                onEvent(UpdateStateOrderEvent.OnDateRescheduledEnter(firstFormat))
            })
    } else {
        onEvent(UpdateStateOrderEvent.OnDateRescheduledEnter(""))
    }
    GlobalSpacerSmall()
    GlobalInput(
        state.comment,
        "Ingresar Comentario",
        maxLines = 3,
        onValueChange = { onEvent(UpdateStateOrderEvent.OnCommentEnter(it)) })

    if (state.state.name == "ENTREGADO" || state.state.name == "RECHAZADO" || state.state.name == "PERDIDO") {
        GlobalSpacerSmall()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ImagePicker(label = "Foto Pedido", onPhotoIsTaken = {
                it?.let { uri ->
                    onEvent(UpdateStateOrderEvent.OnTakePhotoOrder(uri))
                }

            })
            ImagePicker(label = "Foto Pago", onPhotoIsTaken = {
                it?.let { uri ->
                    onEvent(UpdateStateOrderEvent.OnTakePhotoCollect(uri))
                }
            })
        }
    }

}

@Composable
fun ButtonsActionsUpdateState(onEvent: (UpdateStateOrderEvent) -> Unit, onNavigateUp: () -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = { onNavigateUp() },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Red
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null
            )
            Text(text = "Atras")
        }

        TextButton(
            onClick = {
                onEvent(UpdateStateOrderEvent.OnUpdateOrder)

            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF41DA26)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_check_24),
                contentDescription = null
            )
            Text(text = "Actualizar")
        }
    }
}