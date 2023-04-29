package gsg.corp.driver_data.remote.request

import android.net.Uri

class MetadataRequest(
    val idRoute: Int = 0,
    val idUser: Int = 0,
    val idStateTracking: Int = 0,
    val comment: String = "",
    val dateRescheduled: String = ""
)