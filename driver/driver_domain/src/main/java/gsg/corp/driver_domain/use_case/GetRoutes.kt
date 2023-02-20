package gsg.corp.driver_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.repository.DriverRepository

class GetRoutes (private val repository: DriverRepository){
    suspend operator fun invoke(): Resource<List<Route>> {
        return repository.getRoute()
    }
}