package gsg.corp.onboarding_presentation.screens.recipes

import gsg.corp.onboarding_domain.model.Recipe

data class RecipeUiState(
    val recipe: Recipe,
    val isExpanded: Boolean =false
)