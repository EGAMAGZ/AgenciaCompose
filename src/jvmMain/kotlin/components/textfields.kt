package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorTextField(
    modifier: Modifier = Modifier,
    labelText: String,
    defaultValue: String = "",
    onChange: (String) -> Unit
) {
    var name by remember { mutableStateOf(defaultValue) }
    var nameError by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        TextField(
            value = name,
            onValueChange = {
                name = it
                nameError = it.isBlank()
                onChange(it)
            },
            label = { Text(labelText) },
            isError = nameError,
            modifier = Modifier.fillMaxWidth()
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
    }

}