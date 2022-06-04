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
import components.ErrorMessage
import components.ErrorTextField
import entities.Contact

fun isNumeric(value: String) = value.all { char -> char.isDigit() }


@Composable
fun CreateContact(onBack: () -> Unit) {
    val contactStorage = ContactStorage()
    var isInvalid by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    var nombre = ""
    var apellido = ""
    var email = ""
    var telefono = ""
    val regex = Regex("^[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Contacto") },
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
        Column(
            modifier = Modifier.padding(it).fillMaxSize().padding(16.dp),
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
                    Text("Informacion del Contacto", style = MaterialTheme.typography.h5)
                    AnimatedVisibility(
                        visible = isInvalid
                    ) {
                        ErrorMessage(message = errorMsg)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ErrorTextField(
                            modifier = Modifier.weight(1f),
                            labelText = "Nombre"
                        ) { value -> nombre = value }
                        Spacer(modifier = Modifier.width(10.dp))
                        ErrorTextField(
                            modifier = Modifier.weight(1f),
                            labelText = "Apellido"
                        ) { value -> apellido = value }
                    }

                    ErrorTextField(
                        modifier = Modifier.fillMaxWidth(),
                        labelText = "Email",
                    ) { value -> email = value }

                    Column {
                        ErrorTextField(
                            modifier = Modifier.fillMaxWidth(),
                            labelText = "Telefono"
                        ) { value -> telefono = value }
                    }
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
                        errorMsg = "Telefono invalido. Solo numeros y tener una longitud de 10 cifras."
                    }
                    if (telefono.isNotBlank() && isNumeric(telefono)) {
                        if (contactStorage.exists(telefono.toLong())) {
                            isInvalid = true
                            errorMsg = "Telefono invalido. Otro contacto ya lo tiene."
                        }
                    }
                    if (!(regex matches email)) {
                        isInvalid = true
                        errorMsg = "Email invalido."
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
                        contactStorage.add(
                            Contact(nombre, apellido, email, telefono.toLong())
                        )
                        contactStorage.save()
                        onBack()
                    }
                }
            ) {
                Text("Guardar Contacto")
            }
        }
    }
}