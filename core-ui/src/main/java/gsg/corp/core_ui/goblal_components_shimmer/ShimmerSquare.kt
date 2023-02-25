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
fun ShimmerSquare() {
    Box(modifier = Modifier
        .width(50.dp)
        .height(50.dp)
        .clip(shape = RoundedCornerShape(5.dp))
        .shimmerEffect()
    )
}