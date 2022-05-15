package windows

import ContactStorage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ErrorTextField


@Composable
fun UpdateContact(numeroOriginal: Long, onBack: () -> Unit) {
    val contactStorage = ContactStorage()
    val contactIndex = contactStorage.findIndex(numeroOriginal)
    val contactInfo = contactStorage.get(contactIndex)

    var nombre = ""
    var apellido = ""
    var email = ""
    var telefono = ""

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
                onClick = {}
            ) {
                Text("Editar Contacto")
            }
        }
    }

}