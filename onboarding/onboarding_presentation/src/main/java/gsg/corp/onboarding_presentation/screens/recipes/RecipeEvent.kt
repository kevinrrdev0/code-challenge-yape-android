package gsg.corp.onboarding_presentation.screens.recipes

sealed class RecipeEvent {
    data class OnQueryChange(val query: String) : RecipeEvent()
    data class OnToggleRouteClick(val recipeUiState: RecipeUiState): RecipeEvent()
    object OnHideError : RecipeEvent()
}