package windows

import ContactStorage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.*
import entities.Contact

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
                NotFoundCard(
                    modifier = Modifier.padding(top = 16.dp),
                    "No se ha encontrado ningun contacto."
                )
            }

        }
    }
}

@Composable
fun SearchContactByNombre(onBack: () -> Unit) {
    val contactStorage = ContactStorage()
    var isInvalid by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }
    var contactList by remember { mutableStateOf(arrayListOf<Contact>()) }

    var nombre = ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar contactos por nombre") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).padding(16.dp)) {
            Card(elevation = 2.dp) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Busqueda por Nombre",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h5
                    )
                    AnimatedVisibility(
                        visible = isInvalid
                    ) {
                        ErrorMessage(message = errorMsg)
                    }
                    ErrorTextField(
                        labelText = "Nombre",
                        defaultValue = "",
                        onChange = { value -> nombre = value }
                    )
                    Button(
                        onClick = {
                            isInvalid = false

                            if (nombre.isBlank()) {
                                isInvalid = true
                                errorMsg = "Nombre invalido. No debe estar vacio."
                            }

                            if (!isInvalid) {
                                contactList = contactStorage.findContactsByName(nombre)
                            }
                        }
                    ) {
                        Text("Buscar")
                    }
                }
            }

            if (contactList.size > 0) {
                val scrollState = rememberLazyListState()
                Column(
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text("Contactos encontrados:", style = MaterialTheme.typography.h5)
                    LazyColumn(
                        state = scrollState,
                    ) {
                        items(contactList) { contact ->
                            PersonaInfoCardClickable(
                                contact = contact
                            )
                        }
                    }
                }
            } else {
                NotFoundCard(
                    modifier = Modifier.padding(top = 16.dp),
                    "No se han encontrado contactos."
                )
            }
        }
    }
}