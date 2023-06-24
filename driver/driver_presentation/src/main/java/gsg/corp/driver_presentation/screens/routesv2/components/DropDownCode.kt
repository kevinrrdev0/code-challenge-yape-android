package gsg.corp.driver_presentation.screens.routesv2.components

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.core.domain.model.GeneralTypeCode
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_texts.TextBody

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownCode(
    listItems: List<GeneralTypeCode>,
    stateSelected: GeneralTypeCode,
    onEventDropDown: (String,String) -> Unit,
    label:String = "",
    readOnly:Boolean = false
) {
    val contextForToast = LocalContext.current.applicationContext

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(stateSelected)
    }

    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        OutlinedTextField(
            value = selectedItem.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        //menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedItem = option
                    // put event
                    onEventDropDown(
                        selectedItem.code,
                        selectedItem.name
                    )
                    Toast.makeText(contextForToast, option.name, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = option.name)
                }
            }
        }
    }

}