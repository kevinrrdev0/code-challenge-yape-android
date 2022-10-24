package gsg.corp.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings

class ConnectionUtilsImpl(private val applicationContext: Context): ConnectionUtils {

    override fun isNetworkAvailable(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability((NetworkCapabilities.NET_CAPABILITY_INTERNET)) ?: false
    }

    override fun isNetworkLowBandWidth(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val bandwidth: Int = connectivityManager.activeNetwork?.let { activeNetwork ->
            connectivityManager.getNetworkCapabilities(activeNetwork)?.linkDownstreamBandwidthKbps
        } ?: -1
        return bandwidth in (0 until MIN_BANDWIDTH_KBPS)
    }

    override fun isAirplaneModeActive(): Boolean = this.let {
        return Settings.Global.getInt(applicationContext.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }

}