package gsg.corp.core.data.network.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

fun isJsonValid(Json: String?): Boolean {
    val gson = Gson()
    return try {
        gson.fromJson(Json, Any::class.java)
        val jsonObjType: Any = gson.fromJson(Json, Any::class.java).javaClass
        jsonObjType != String::class.java
    } catch (ex: JsonSyntaxException) {
        false
    }
}