package gsg.corp.core_ui.global_components_inputs

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GlobalCheckBox(
    state: Boolean,
    color: Color = MaterialTheme.colors.primary,
    onCheckedChange: (Boolean) -> Unit,
) {
    Checkbox(
        checked = state,
        onCheckedChange = { onCheckedChange(it)},
        colors = CheckboxDefaults.colors(color)
    )
}