package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorTextField(onValidate: (String) -> Boolean) {
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(onValidate(name)) }

    Column {
        TextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            label = { Text("Nombre") },
            isError = nameError
        )

        var assistiveElementText = if (nameError) "Error: Obligatorio" else "*Obligatorio"
        val assistiveElementColor = if (nameError) {
            MaterialTheme.colors.error
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }

        Text(
            text = assistiveElementText,
            color = assistiveElementColor,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(Modifier.size(16.dp))
    }

}