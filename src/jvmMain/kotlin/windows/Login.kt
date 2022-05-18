package windows

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import components.ErrorTextField

@Composable
fun Login(onAccess: () -> Unit) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier.padding(it)
                    .widthIn(300.dp, 600.dp)
                    .align(Alignment.Center)
                    .padding(horizontal=16.dp),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Iniciar Sesión:",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ErrorTextField(
                        defaultValue = "",
                        labelText = "Usuario"
                    ) {}
                    Spacer(modifier = Modifier.height(8.dp))
                    ErrorTextField(
                        defaultValue = "",
                        labelText = "Contraseña"
                    ) {}
                    Button(onClick = {
                        onAccess()
                    }) {
                        Text("Acceder")
                    }
                }
            }
        }

    }
}