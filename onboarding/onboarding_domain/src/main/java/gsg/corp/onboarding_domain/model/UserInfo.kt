package gsg.corp.onboarding_domain.model

data class UserInfo(
    val id:Int,
    val name:String,
    val telephone:String,
    val user:String,
    val role:String,
    val token:String,
    val imageUrl:String
)
