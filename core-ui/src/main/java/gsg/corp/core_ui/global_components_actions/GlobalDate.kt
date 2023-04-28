package gsg.corp.core_ui.global_components_actions


import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import gsg.corp.core_ui.global_components_ui.GlobalErrorCaption
import java.util.*

@Composable
fun DatePickerComponent(
    date:String="",
    label:String="Date",
    isVisible:Boolean = true,
    isError: Boolean = false,
    msgError:String ="",
    onDateSelected: (String,String) -> Unit
){
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            val day = if(d<10) "0$d" else d
            val month = if((m+1)<10) "0${m+1}" else (m+1)

            onDateSelected("$day/"+month+"/$y","$y-$month-$day")
        }, year, month, day
    )

    if(isVisible){
        Column(modifier = Modifier
            .fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                value = date,
                onValueChange = {},
                placeholder = { Text(label) },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        datePickerDialog.show()
                    }) {
                        Icon(Icons.Outlined.CalendarToday, contentDescription = "password_icon")
                    }
                }
            )
            if (isError) {
                GlobalErrorCaption(msgError)
            }
        }
    }
}