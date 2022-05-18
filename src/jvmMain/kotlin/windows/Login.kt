package windows

import UserStorage
import androidx.compose.animation.AnimatedVisibility
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
                        "Iniciar Sesión:",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    AnimatedVisibility(
                        visible = !isValid
                    ) {
                        ErrorMessage(
                            modifier = Modifier.padding(vertical = 4.dp),
                            message = "Usuario o contraseña incorrectos."
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ErrorTextField(
                        defaultValue = "",
                        labelText = "Usuario",
                        onChange = { username = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ErrorTextField(
                        defaultValue = "",
                        labelText = "Contraseña",
                        onChange = { password = it },
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