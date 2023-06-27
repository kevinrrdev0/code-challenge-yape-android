package gsg.corp.onboarding_presentation.screens.recipes.detail


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import gsg.corp.core.util.toUpperCaseLocale
import gsg.corp.core_ui.R
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.onboarding_presentation.screens.recipes.componets.CommonRowCardItem
import gsg.corp.onboarding_presentation.screens.recipes.componets.HtmlText

@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onNavigateMap: (String, String,String) -> Unit,
    onNavigateUp: () -> Unit
) {
    val item = state.recipe

    Column {
        Column(Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)) {

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
            GlobalExtraSpacerSmall()
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_ingredients),
                    contentDescription = null
                )
                GlobalExtraSpacerSmallRow()
                HtmlText(item.ingredients)
            }

            GlobalExtraSpacerSmall()
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_chat_message),
                    contentDescription = null
                )
                GlobalExtraSpacerSmallRow()
               TextBody(text =  item.littleSecret.toUpperCaseLocale())
            }

            GlobalSpacerSmall()

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNavigateMap(item.lat, item.lng,item.nameRecipe)
                }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextBody(text = "Ver en el mapa", textColor = RedGsg, boldHighlighting = true)
            GlobalExtraSpacerSmallRow()
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "go detail action",
                tint = RedGsg
            )
        }
        GlobalExtraSpacerSmall()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextButton(
                onClick = { onNavigateUp() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.Red
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null
                )
                Text(text = "Regresar a las recetas")
            }
        }

    }

}