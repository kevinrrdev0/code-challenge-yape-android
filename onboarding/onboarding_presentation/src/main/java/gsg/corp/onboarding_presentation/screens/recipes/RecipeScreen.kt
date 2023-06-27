package gsg.corp.onboarding_presentation.screens.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.global_components_dialogs.GlobalDialogError
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowMediumLarge
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmallMedium
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.onboarding_presentation.screens.recipes.componets.RecipeCardItem
import gsg.corp.onboarding_presentation.screens.recipes.componets.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeScreen(
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit,
    onClickDetail: (Int) -> Unit,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!state.loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                SearchTextField(
                    text = state.searchQuery,
                    hint="Buscar por ingredientes",
                    onValueChange = {
                        onEvent(RecipeEvent.OnQueryChange(it))
                    },
                    shouldShowHint = false,
                    onSearch = {
                        keyboardController?.hide()
                    },
                    onFocusChanged = {

                    }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = 16.dp
                    )
                ) {
                    items(state.listRecipesShow) { recipe ->
                        RecipeCardItem(
                            recipeUiState = recipe,
                            onClick = { onEvent(RecipeEvent.OnToggleRouteClick(recipe))  },
                            onGoDetail = { onClickDetail(recipe.recipe.id)})
                    }
                }
            }

        }

        BoxLoadAnimation(state.loading)

        //error se ve parte superiror versiones android 13
        Box(
            contentAlignment = Alignment.Center
        ) {
            when {
                state.messageError.isVisible -> {
                    GlobalDialogError(
                        state.messageError.isVisible,
                        text = state.messageError.description.asString(context),
                        onConfirm = {
                            onEvent(RecipeEvent.OnHideError)
                        })
                }
            }
        }
    }

}