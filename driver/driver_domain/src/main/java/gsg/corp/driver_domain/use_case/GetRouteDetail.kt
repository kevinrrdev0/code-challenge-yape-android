package gsg.corp.driver_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_domain.repository.DriverRepository

class GetRouteDetail(private val repository: DriverRepository) {
    suspend operator fun invoke(idRoute:Int): Resource<RouteDetail> {
        return repository.getRouteDetail(idRoute)
    }
}