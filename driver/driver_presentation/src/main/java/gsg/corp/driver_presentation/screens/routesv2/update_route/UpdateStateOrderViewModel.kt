package gsg.corp.driver_presentation.screens.routesv2.update_route

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.util.UiEvent
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.core.domain.model.GeneralTypeCode
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.core.global_models.MessageError
import gsg.corp.core.util.UiText
import gsg.corp.core_ui.navigation.ROUTE_ID
import gsg.corp.driver_domain.model.RoutePayment
import gsg.corp.driver_domain.model.RouteStateRequest
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpdateStateOrderViewModel @Inject constructor(
    private val driverUseCases: DriverUseCases,
    savedStateHandle: SavedStateHandle,
    private val pref: Preferences,
) : ViewModel() {

    var state by mutableStateOf(UpdateStateOrderState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val idRoute = savedStateHandle.get<Int>(ROUTE_ID)!!

        idRoute.let {
            state = state.copy(idRoute = it)
            getRouteDetail(it)
        }
    }

    fun onEvent(event: UpdateStateOrderEvent) {
        when (event) {
            is UpdateStateOrderEvent.OnCommentEnter -> state.copy(comment = event.comment)
            is UpdateStateOrderEvent.OnTakePhotoOrder -> state.copy(photoOrder = event.pathPhotoOrder)
            is UpdateStateOrderEvent.OnTakeOtherPhotoOrder -> state.copy(otherPhotoOrder = event.pathOtherPhotoOrder)
            is UpdateStateOrderEvent.OnStateSelected -> state.copy(
                state = GeneralType(
                    event.id,
                    event.name
                )
            )

            is UpdateStateOrderEvent.OnDateRescheduledEnter -> {
                val aea = event.dateRescheduled
                state.copy(dateRescheduled = aea)
            }

            UpdateStateOrderEvent.OnUpdateOrder -> {
                updateState()
                state.copy()
            }

            is UpdateStateOrderEvent.OnCodePayMethod1Selected -> state.copy(
                codePayMethod1 = GeneralTypeCode(event.code, event.name)
            )

            is UpdateStateOrderEvent.OnCodePayMethod2Selected -> state.copy(
                codePayMethod2 = GeneralTypeCode(event.code, event.name)
            )

            is UpdateStateOrderEvent.OnTakePhotoPay1 -> state.copy(pathPhotoPay1 = event.pathPhotoPay1)
            is UpdateStateOrderEvent.OnTakePhotoPay2 -> state.copy(pathPhotoPay2 = event.pathPhotoPay2)
            is UpdateStateOrderEvent.OnFlkPay1GSGChecked -> state.copy(flkPayGSG1 = event.checked)
            is UpdateStateOrderEvent.OnFlkPay2GSGChecked -> state.copy(flkPayGSG2 = event.checked)
            is UpdateStateOrderEvent.OnPayAmount1Enter -> state.copy(payAmount1 = event.payAmount1)
            is UpdateStateOrderEvent.OnPayAmount2Enter -> state.copy(payAmount2 = event.payAmount2)
        }.also { state = it }
    }

    private fun getRouteDetail(idRoute: Int) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = driverUseCases.getRouteDetail(idRoute)) {
                is Resource.Success -> {
                    result.data?.let { routeDetail ->
                        //val routeListUi = routes.
                        state = state.copy(
                            isLoading = false, routeDetail = routeDetail, state = GeneralType(
                                routeDetail.stId,
                                routeDetail.stCode
                            ), comment = routeDetail.comment, dateRescheduled = routeDetail.dateRescheduled
                        )
                        if (routeDetail.RoutePayments.isNotEmpty()) {
                            if (routeDetail.RoutePayments.size > 1) {
                                val itemPayMethod = routeDetail.RoutePayments[0]
                                state = state.copy(
                                    codePayMethod1 = GeneralTypeCode(
                                        itemPayMethod.codePayMethod,
                                        itemPayMethod.codePayMethod
                                    ),
                                    flkPayGSG1 = itemPayMethod.flkPayGsg == 1,
                                    detailPay1 = itemPayMethod.payDetail,
                                    pathPhotoPay1 = itemPayMethod.pathPhotoPay,
                                    payAmount1 = itemPayMethod.payAmount
                                )
                            }
                            if (routeDetail.RoutePayments.size == 2) {
                                val itemPayMethod = routeDetail.RoutePayments[1]
                                state = state.copy(
                                    codePayMethod2 = GeneralTypeCode(
                                        itemPayMethod.codePayMethod,
                                        itemPayMethod.codePayMethod
                                    ),
                                    flkPayGSG2 = itemPayMethod.flkPayGsg == 1,
                                    detailPay2 = itemPayMethod.payDetail,
                                    pathPhotoPay2 = itemPayMethod.pathPhotoPay,
                                    payAmount2 = itemPayMethod.payAmount
                                )
                            }
                        }
                    }
                    _uiEvent.send(UiEvent.Success)
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        messageError = MessageError(
                            isVisible = true,
                            description = result.message
                                ?: UiText.StringResource(R.string.error_login)
                        )
                    )
                }
            }
        }
    }

    private fun updateState() {
        val listRoutePayments = mutableListOf<RoutePayment>()
        if (state.codePayMethod1.code.isNotEmpty()) {
            listRoutePayments.add(
                RoutePayment(
                    codePayMethod = state.codePayMethod1.code,
                    flkPayGsg = if (state.flkPayGSG1) 1 else 0,
                    pathPhotoPay = state.pathPhotoPay1,
                    payAmount = state.payAmount1,
                    payDetail = state.detailPay1
                )
            )
        }
        if (state.codePayMethod2.code.isNotEmpty()) {
            listRoutePayments.add(
                RoutePayment(
                    codePayMethod = state.codePayMethod2.code,
                    flkPayGsg = if (state.flkPayGSG2) 1 else 0,
                    pathPhotoPay = state.pathPhotoPay2,
                    payAmount = state.payAmount2,
                    payDetail = state.detailPay2
                )
            )
        }

        val request = RouteStateRequest(
            idRoute = state.idRoute,
            idUser = pref.loadUserInfo()?.id ?: 0,
            idStateTracking = state.state.id,
            comment = state.comment,
            dateRescheduled = state.dateRescheduled,
            photoStatus = state.photoOrder,
            otherPhotoStatus = state.otherPhotoOrder,
            routesPayments = listRoutePayments
        )

        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = driverUseCases.updateStateOrder(request)) {
                is Resource.Success -> {
                    result.data?.let { _ ->
                        //val routeListUi = routes.
                        state = state.copy(isLoading = false)
                    }
                    _uiEvent.send(UiEvent.NavigateUp)
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        messageError = MessageError(
                            isVisible = true,
                            description = result.message
                                ?: UiText.StringResource(R.string.error_login)
                        )
                    )
                }
            }
        }
    }

}