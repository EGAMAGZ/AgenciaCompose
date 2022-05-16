package windows

import ContactStorage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ErrorTextField
import entities.Contact


@Composable
fun UpdateContact(numeroOriginal: Long, onBack: () -> Unit) {
    val contactStorage = ContactStorage()
    val contactIndex = contactStorage.findIndex(numeroOriginal)
    val contactInfo = contactStorage.get(contactIndex)

    var isInvalid by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    var nombre = contactInfo.nombre
    var apellido = contactInfo.apellido
    var email = contactInfo.email
    var telefono = contactInfo.telefono.toString()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Editar Contacto: ${contactInfo?.nombre} ${contactInfo?.apellido}") },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Regresar"
                    )
                }
            })
    }) {
        Column(
            modifier = Modifier.padding(it).fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Información del Contacto", style = MaterialTheme.typography.h5)
                    AnimatedVisibility(
                        visible = isInvalid
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.error,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = errorMsg,
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        ErrorTextField(
                            modifier = Modifier.weight(1f),
                            labelText = "Nombre",
                            defaultValue = contactInfo?.nombre!!
                        ) { value -> nombre = value }
                        Spacer(modifier = Modifier.width(10.dp))
                        ErrorTextField(
                            modifier = Modifier.weight(1f),
                            labelText = "Apellido",
                            defaultValue = contactInfo?.apellido
                        ) { value -> apellido = value }
                    }

                    ErrorTextField(
                        modifier = Modifier.fillMaxWidth(),
                        labelText = "Email",
                        defaultValue = contactInfo?.email!!
                    ) { value -> email = value }

                    ErrorTextField(
                        modifier = Modifier.fillMaxWidth(),
                        labelText = "Telefono",
                        defaultValue = contactInfo?.telefono!!.toString()
                    ) { value -> telefono = value }

                }

            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    isInvalid = false
                    nombre = nombre.trim()
                    apellido = apellido.trim()
                    email = email.trim()
                    telefono = telefono.trim()


                    if (telefono.isBlank() || telefono.length != 10 || !isNumeric(telefono) || contactStorage.exists(
                            telefono.toLong()
                        )
                    ) {
                        isInvalid = true
                        errorMsg = "Telefono invalido. Debe ser unico, solo numeros y no estar vacio."
                    }
                    if (email.isBlank()) {
                        isInvalid = true
                        errorMsg = "Email invalido. No debe estar vacio."
                    }
                    if (apellido.isBlank()) {
                        isInvalid = true
                        errorMsg = "Apellido invalido. No debe estar vacio."
                    }
                    if (nombre.isBlank()) {
                        isInvalid = true
                        errorMsg = "Nombre invalido. No debe estar vacio."
                    }

                    if (!isInvalid) {
                        contactStorage.update(
                            contactIndex,
                            Contact(nombre, apellido, email, telefono.toLong())
                        )
                        contactStorage.save()
                        onBack()
                    }
                }
            ) {
                Text("Editar Contacto")
            }
        }
    }

}