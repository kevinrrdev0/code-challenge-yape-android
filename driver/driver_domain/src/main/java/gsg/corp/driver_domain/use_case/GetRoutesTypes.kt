package gsg.corp.driver_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.RouteType
import gsg.corp.driver_domain.repository.DriverRepository

class GetRoutesTypes (private val repository: DriverRepository) {
    suspend operator fun invoke(): Resource<List<RouteType>> {
        return repository.getRouteType()
    }
}