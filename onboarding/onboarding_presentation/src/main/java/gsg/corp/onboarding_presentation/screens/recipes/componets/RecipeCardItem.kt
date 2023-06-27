package gsg.corp.onboarding_presentation.screens.recipes.componets

import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import gsg.corp.core.util.toUpperCaseLocale
import gsg.corp.core_ui.R
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowMediumLarge
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_presentation.screens.recipes.RecipeUiState


@Preview(showBackground = true, heightDp = 600, widthDp = 600)
@Composable
fun PreviewRouteCardItem() {
    Column {
        RecipeCardItem(
            recipeUiState = RecipeUiState(
                Recipe(
                    1,
                    "d",
                    "",
                    "",
                    "",
                    "",
                    "https://www.ajinomoto.com.pe:8085/img/receta/5.-Caiguas-rellenas-de-quinua.jpg"
                )
            ), onClick = { /*TODO*/ }, onGoDetail = { /*TODO*/ })
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCardItem(
    recipeUiState: RecipeUiState,
    onClick: () -> Unit,
    onGoDetail: () -> Unit,
    modifier: Modifier = Modifier
) {

    val item = recipeUiState.recipe

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp, modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp), onClick = onClick
    ) {
        Column {
            Column(modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.urlImage)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image),
                    contentDescription = "Image Server",//stringResource(R.string.description)
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                GlobalExtraSpacerSmall()
                CommonRowCardItem(
                    R.drawable.ic_food,
                    item.nameRecipe.toUpperCaseLocale()
                )
                AnimatedVisibility(visible = recipeUiState.isExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        GlobalExtraSpacerSmall()
                        CommonRowCardItem(
                            R.drawable.ic_chat_message,
                            item.littleSecret.toUpperCaseLocale()
                        )
                        GlobalSpacerSmall()
                    }
                }
            }

            if (!recipeUiState.isExpanded) {
                GlobalSpacerSmall()
            }
            Divider(thickness = 1.dp, color = RedGsg)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onGoDetail()
                    }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextBody(text = "Ir a detalle", textColor = RedGsg, boldHighlighting = true)
                GlobalExtraSpacerSmallRow()
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_login_action),
                    contentDescription = "go detail action",
                    tint = RedGsg
                )
            }
        }
    }
}


@Composable
fun CommonRowCardItem(idDrawable: Int, text: String, boldText: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = idDrawable),
            contentDescription = null
        )
        GlobalExtraSpacerSmallRow()
        TextBody(
            text = text, maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            boldHighlighting = boldText
        )
    }
}



