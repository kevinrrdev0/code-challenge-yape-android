package gsg.corp.core.util

 interface ConnectionUtils{
    fun isNetworkAvailable() : Boolean
    fun isNetworkLowBandWidth(): Boolean
    fun isAirplaneModeActive(): Boolean
}