package gsg.corp.driver_presentation.screens.routesv2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.core.global_models.MessageError
import gsg.corp.core.util.UiEvent
import gsg.corp.core.util.UiText
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val pref: Preferences,
    private val driverUseCases: DriverUseCases,
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(RouteState())
        private set
    init {
        getRoutes(id = pref.loadUserInfo()!!.id)
    }

    fun onEvent(event: RouteEvent){
        when(event){
            is RouteEvent.OnToggleRouteClick -> state = state.copy(listRoutes = state.listRoutes.map {
                if(it.route.id == event.routeUiState.route.id){
                    it.copy(isExpanded = !it.isExpanded)
                }else it
            })
            is RouteEvent.OnHideError -> state = state.copy(messageError = MessageError(isVisible = false))
        }
    }
    private fun getRoutes(id: Int) {
        state = state.copy(loading = true)
        viewModelScope.launch {
            val result =  driverUseCases
                .getRoutes()
            when (result) {
                is Resource.Success -> {
                    result.data?.let{ routes->
                        val routeListUi = routes.map {
                            RouteUiState(it,false)
                        }
                        state = state.copy(listRoutes = routeListUi,loading = false)
                    }

                    _uiEvent.send(UiEvent.Success)
                }
                is Resource.Error -> {
                    state = state.copy(
                        loading = false,
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