package gsg.corp.onboarding_data.mapper

import gsg.corp.onboarding_data.remote.dto.login.LoginDto
import gsg.corp.onboarding_data.remote.dto.login.RecipeDto
import gsg.corp.onboarding_data.remote.dto.login.RecipesDto
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_domain.model.UserInfo

fun LoginDto.toUserInfo(token: String): UserInfo {
    return UserInfo(
        id = user.uid,
        name = user.name,
        telephone = user.telephone,
        user = user.username,
        role = user.role,
        token = token,
        imageUrl = user.image
    )
}

fun RecipesDto.toRecipesDomain(): List<Recipe> {
    return recipes.map { r ->
        Recipe(
            r.id, r.ingredients, r.lat, r.little_secret, r.lng, r.name_recipe, r.url_image
        )
    }

}

fun RecipeDto.toRecipeDomain(): Recipe {
    return Recipe(
        recipe.id,
        recipe.ingredients,
        recipe.lat,
        recipe.little_secret,
        recipe.lng,
        recipe.name_recipe,
        recipe.url_image
    )
}