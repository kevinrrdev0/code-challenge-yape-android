package gsg.corp.driver_domain.repository

import android.net.Uri
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.driver_domain.model.Route
import java.io.File

interface DriverRepository {

    suspend fun updateRoute(file: File, uri:Uri,path:String)

    suspend fun getRoute(): Resource<List<Route>>

}