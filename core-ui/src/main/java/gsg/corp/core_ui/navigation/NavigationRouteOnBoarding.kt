package gsg.corp.core_ui.navigation

const val RECIPE_ID = "recipe_id"


const val LAT = "lat"
const val LNG = "lng"
const val NAME_RECIPE = "name_recipe"

sealed class NavigationRouteOnBoarding(val route: String) {

    object Login : NavigationRouteOnBoarding("login")
    object Recipe : NavigationRouteOnBoarding("recipe")
    object RecipeDetail : NavigationRouteOnBoarding("recipe_detail?$RECIPE_ID={$RECIPE_ID}") {
        fun passId(id: Int): String {
            return "recipe_detail?$RECIPE_ID=$id"
        }
    }
    object RecipeMap : NavigationRouteOnBoarding("map")

//    object RecipeMapWithLatLng : NavigationRouteOnBoarding("recipe_detail?$LAT={$LAT}&$LNG={$LNG}") {
//        fun passLatLng(lat: String, lng: String): String {
//            return "recipe_detail?$LAT=$lat&$LNG=$lng"
//        }
//    }

    object RecipeMapWithLatLng : NavigationRouteOnBoarding("map?$LAT={$LAT}&$LNG={$LNG}&$NAME_RECIPE={$NAME_RECIPE}") {
        fun passLatLng(lat: String, lng: String, name: String): String {
            return "map?$LAT=$lat&$LNG=$lng&$NAME_RECIPE=$name"
        }
    }
}