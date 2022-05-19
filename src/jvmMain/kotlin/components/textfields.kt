package components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ErrorTextField(
    modifier: Modifier = Modifier,
    labelText: String,
    defaultValue: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: (String) -> Unit
) {
    var name by remember { mutableStateOf(defaultValue) }
    var nameError by remember { mutableStateOf(false) }
    val inputColor by animateColorAsState(
        if (nameError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = 100,
            easing = LinearEasing
        )
    )

    Column(modifier = modifier) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = it.isBlank()
                onChange(it)
            },
            label = { Text(labelText) },
            isError = nameError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = visualTransformation
        )

        var assistiveElementText = if (nameError) "Error: Obligatorio" else "*Obligatorio"

        Text(
            text = assistiveElementText,
            color = inputColor,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }

}