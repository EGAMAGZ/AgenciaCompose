package windows

import ContactStorage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ErrorTextField
import entities.Contact

@Composable
fun CreateContact(onBack: () -> Unit) {
    val contactStorage = ContactStorage()

    var nombre = ""
    var apellido = ""
    var email = ""
    var telefono = ""

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
                    Text("Informacion del Contacto", style = MaterialTheme.typography.h5)
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
                    if (
                        nombre.isNotBlank() && apellido.isNotBlank() &&
                        email.isNotBlank() && telefono.isNotBlank() &&
                        telefono.length == 10 && !contactStorage.exists(telefono.toLong())
                    ) {
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