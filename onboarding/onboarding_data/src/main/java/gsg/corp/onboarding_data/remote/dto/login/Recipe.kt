package gsg.corp.onboarding_data.remote.dto.login

data class Recipe(
    val id: Int,
    val ingredients: String,
    val lat: String,
    val little_secret: String,
    val lng: String,
    val name_recipe: String,
    val url_image: String
)