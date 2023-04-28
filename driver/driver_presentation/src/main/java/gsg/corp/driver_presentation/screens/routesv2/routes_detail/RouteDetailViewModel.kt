package gsg.corp.driver_presentation.screens.routesv2.routes_detail

import android.util.Log
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
import gsg.corp.core.global_models.MessageError
import gsg.corp.core.util.UiText
import gsg.corp.core_ui.navigation.ROUTE_ID
import gsg.corp.driver_domain.model.RouteStateRequest
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RouteDetailViewModel @Inject constructor(
    private val driverUseCases: DriverUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(RouteDetailState())
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

    fun onEvent(event: RouteDetailEvent) {
        when (event) {
            is RouteDetailEvent.OnCommentEnter -> state.copy(comment = event.comment)
            is RouteDetailEvent.OnTakePhotoOrder -> state.copy(photoOrder = event.pathPhotoOrder)
            is RouteDetailEvent.OnTakePhotoCollect -> state.copy(photoCollect = event.pathPhotoCollect)
            is RouteDetailEvent.OnStateSelected -> state.copy(
                state = GeneralType(
                    event.id,
                    event.name
                )
            )
            is RouteDetailEvent.OnDateRescheduledEnter -> {
                val aea = event.dateRescheduled
                state.copy(dateRescheduled = aea)
            }
            RouteDetailEvent.OnUpdateOrder -> {
                updateState()
                state.copy()
            }
        }.also { state = it }
    }

    private fun getRouteDetail(idRoute: Int) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = driverUseCases.getRouteDetail(idRoute)) {
                is Resource.Success -> {
                    result.data?.let { routeDetail ->
                        //val routeListUi = routes.
                        state = state.copy(isLoading = false, routeDetail = routeDetail)
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




        val request = RouteStateRequest(
            idRoute = state.idRoute,
            id_state_tracking = state.state.id,
            comment = state.comment,
            dateRescheduled = state.dateRescheduled,
            photoOrder = state.photoOrder,
            photoCollect = state.photoCollect
        )

        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = driverUseCases.updateStateOrder(request)) {
                is Resource.Success -> {
                    result.data?.let { _ ->
                        //val routeListUi = routes.
                        state = state.copy(isLoading = false)
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

    fun onPathChange(item: String) {
//        state = state.copy(path = item)
    }

    fun onUploadPhoto() {
//        val file = File(state.path)
//        viewModelScope.launch {
//            driverUseCases.updateRoute(file = file, uri = Uri.fromFile(file), state.path)
//        }

    }

}