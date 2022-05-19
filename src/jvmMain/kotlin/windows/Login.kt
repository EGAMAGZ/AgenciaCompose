package windows

import UserStorage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import components.ErrorMessage
import components.ErrorTextField

@Composable
fun Login(onAccess: () -> Unit) {

    val userStorage = UserStorage()

    var isValid by remember { mutableStateOf(true) }
    var username = ""
    var password = ""

    Scaffold {
        Box(
            modifier = Modifier.padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colors.primarySurface)
        ) {
            Card(
                modifier = Modifier.padding(it)
                    .widthIn(300.dp, 600.dp)
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Agenda",
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.h3.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "Iniciar Sesión:",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimatedVisibility(
                        visible = !isValid
                    ) {
                        ErrorMessage(
                            modifier = Modifier.padding(vertical = 4.dp),
                            message = "Usuario o contraseña incorrectos."
                        )
                    }
                    ErrorTextField(
                        defaultValue = "",
                        labelText = "Usuario",
                        onChange = { value -> username = value }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ErrorTextField(
                        defaultValue = "",
                        labelText = "Contraseña",
                        onChange = { value -> password = value },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Button(onClick = {
                        isValid = userStorage.isValid(username, password)

                        if (isValid) {
                            onAccess()
                        }
                    }) {
                        Text("Acceder")
                    }
                }
            }
        }

    }
}