package gsg.corp.driver_data.remote.request

import android.net.Uri

class MetadataRequest(
    val idRoute: Int = 0,
    val id_state_tracking: Int = 0,
    val comment: String = "",
    val dateRescheduled: String = ""
)