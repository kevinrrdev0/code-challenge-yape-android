package gsg.corp.core_ui.global_components_inputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gsg.corp.core_ui.LocalSpacing

@Composable
fun GlobalSpacerMid(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
    }
}
@Composable
fun GlobalSpacerMidRow(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
    }
}
@Composable
fun GlobalSpacerSmall(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
    }
}
@Composable
fun GlobalExtraSpacerSmall(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
    }
}
@Composable
fun GlobalSpacerExtraSmall(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
    }
}
@Composable
fun GlobalExtraSpacerSmallRow(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
    }
}

@Composable
fun GlobalSpacerLarge(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
    }
}

@Composable
fun GlobalSpacerRowMedium(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
    }
}
@Composable
fun GlobalSpacerRowLarge(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceLarge))
    }
}

@Composable
fun GlobalSpacerRowSmall(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
    }
}
@Composable
fun GlobalSpacerRowSmallMedium(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceSmallMedium))
    }
}
@Composable
fun GlobalSpacerRowMediumLarge(isVisible:Boolean = true) {
    if (isVisible){
        val spacing = LocalSpacing.current
        Spacer(modifier = Modifier.width(spacing.spaceMediumLarge))
    }
}