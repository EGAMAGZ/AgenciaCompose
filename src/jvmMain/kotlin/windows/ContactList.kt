package windows

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import components.PersonaCard
import entities.Contact

@Composable
fun ContactList(onEdit: (Long) -> Unit, onClickCreate: () -> Unit) {

    var listaContacts = remember {
        mutableStateListOf(
            Contact("Gamaliel", "Garcia", "ejemplo@hotmail.com", 5951140476),
            Contact("Gamaliel", "Garcia", "ejemplo@hotmail.com", 5951140476),
            Contact("Gamaliel", "Garcia", "ejemplo@hotmail.com", 5951140476),
        )
    }

    var isDialogOpen by remember { mutableStateOf(false) }
    var contactName by remember { mutableStateOf("") }
    var contactToDelete by remember { mutableStateOf<Contact?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Contactos") }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onClickCreate() },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar Contaco"
                    )
                },
                text = { Text("Agregar") }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(items = listaContacts) {
                PersonaCard(
                    contact = it,
                    onClickDelete = {
                        isDialogOpen = true
                        contactToDelete = it
                        contactName = "${it.nombre} ${it.apellido}"
                    },
                    onClickEdit = { onEdit(it.telefono) }
                )
            }
        }
        if (isDialogOpen) {
            Dialog(
                onCloseRequest = { isDialogOpen = false },
                title = "Confirmación",
                resizable = false
            ) {
                Column(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        text = "¿Seguro de eliminar al contacto: $contactName?"
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { isDialogOpen = false }
                        ) {
                            Text("Cancelar")
                        }
                        Button(
                            onClick = {
                                isDialogOpen = false
                                listaContacts.remove(contactToDelete)
                            }
                        ) {
                            Text("Aceptar")
                        }
                    }
                }
            }
        }
    }
}