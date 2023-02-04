package gsg.corp.driver_presentation.screens.routes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.RouteType
import gsg.corp.driver_domain.use_case.DriverUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel
@Inject constructor(
    private val driverUseCases: DriverUseCases
): ViewModel() {


    fun onGetRoutes() {
        viewModelScope.launch {
            driverUseCases
                .getRoutes()
        }
    }

    val routeType: MutableState<List<RouteType>> = mutableStateOf(ArrayList())

    fun onGetRoutesTypes() {
        viewModelScope.launch {
            val result = driverUseCases.getRoutesTypes()
            when(result) {
                is Resource.Success -> {
                    result.data?.let {
                        routeType.value = it
                    }
                }
                else -> {}
            }
        }
    }
}