package gsg.corp.driver_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.GeneralResponse
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_domain.model.RouteStateRequest
import gsg.corp.driver_domain.repository.DriverRepository

class UpdateStateOrder(private val repository: DriverRepository) {
    suspend operator fun invoke(request: RouteStateRequest): Resource<GeneralResponse> {
        return repository.updateStateOrder(request)
    }
}