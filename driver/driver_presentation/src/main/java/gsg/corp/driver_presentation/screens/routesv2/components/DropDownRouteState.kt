package gsg.corp.driver_presentation.screens.routesv2.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_texts.TextBody

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDown(
    listItems: List<GeneralType>,
    stateSelected: GeneralType,
    label:String = "Estado del pedido",
    modifier:Modifier=Modifier.fillMaxWidth(),
    onEventDropDown: (Int,String) -> Unit,
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
            modifier=modifier,
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
            modifier=modifier,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedItem = option
                    // put event
                    onEventDropDown(
                        selectedItem.id,
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