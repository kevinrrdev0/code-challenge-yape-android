package gsg.corp.driver_domain.model

data class UserInfo(
    val id:Int,
    val name:String,
    val telephone:String,
    val user:String,
    val role:String,
    val token:String,
    val image:String
)
