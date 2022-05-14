package windows

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun UpdateContact(numeroOriginal: Long, onBack: () -> Unit) {
    var numero by remember { mutableStateOf<Long>(numeroOriginal) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Editar Contacto") }, navigationIcon = {
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
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = TextFieldValue(""),
                            label = { Text("Nombre") },
                            onValueChange = {}
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = TextFieldValue(""),
                            label = { Text("Apellido") },
                            onValueChange = {}
                        )
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        label = { Text("Email") },
                        onValueChange = {

                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = numero.toString(),
                        label = { Text("Número telefonico") },
                        onValueChange = {
                            numero = it.toLong()
                        }
                    )

                }

            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Guardar")
            }
        }
    }

}