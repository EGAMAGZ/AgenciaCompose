package windows

import ContactStorage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.ErrorMessage
import components.ErrorTextField
import components.PersonaInfoCard

@Composable
fun SearchContactByTelefono(onBack: () -> Unit) {
    val contactStorage = ContactStorage()
    var isInvalid by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }
    var contactIndex by remember { mutableStateOf(-1) }

    var telefono = ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar contacto por telefono ") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).padding(16.dp)) {
            Card(
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Busqueda por Telefono",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h5
                    )
                    AnimatedVisibility(
                        visible = isInvalid
                    ) {
                        ErrorMessage(message = errorMsg)
                    }
                    ErrorTextField(
                        labelText = "Telefono",
                        defaultValue = ""
                    ) { telefono = it }
                    Button(
                        onClick = {
                            isInvalid = false
                            if (telefono.isBlank() || telefono.length != 10 || !isNumeric(telefono)
                            ) {
                                isInvalid = true
                                errorMsg = "Telefono invalido. Solo numeros y tener una longitud de 10 cifras."
                            }

                            if (!isInvalid) {
                                contactIndex = contactStorage.findIndex(
                                    telefono.toLong()
                                )
                            }
                        },
                    ) {
                        Text("Buscar")
                    }
                }
            }

            if (contactIndex >= 0) {
                var contact = contactStorage.get(contactIndex)
                PersonaInfoCard(
                    modifier = Modifier.padding(vertical = 12.dp),
                    contact
                )
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    elevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("No se ha encontrado ningun contacto.", textAlign = TextAlign.Center)
                    }
                }
            }

        }
    }
}

@Composable
fun SearchContactByNombre(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar contactos por nombre") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ){

    }
}