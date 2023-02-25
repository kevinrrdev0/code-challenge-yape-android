package gsg.corp.core_ui.goblal_components_shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRectangleExtraLarge () {
    Box(modifier = Modifier
        .width(270.dp)
        .height(30.dp)
        .clip(shape = RoundedCornerShape(5.dp))
        .shimmerEffect()
    )
}

