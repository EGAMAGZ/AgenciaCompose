package windows

import ContactStorage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import components.PersonaCard

@Composable
fun ContactList(onEdit: (Long) -> Unit, onClickCreate: () -> Unit, onSearch: () -> Unit) {

    val contactStorage = ContactStorage()

    var isDialogOpen by remember { mutableStateOf(false) }
    var contactName by remember { mutableStateOf("") }
    var indexToDelete = 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Contactos") },
                actions = {
                    IconButton( onClick = { onSearch() }){
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Buscar"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onClickCreate() },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar Contacto"
                    )
                },
                text = { Text("Agregar") }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        if (contactStorage.count() > 0) {
            val scrollState = rememberLazyListState()
            LazyColumn(modifier = Modifier.padding(it), state = scrollState) {
                itemsIndexed(items = contactStorage.getAll()) { index, it ->
                    PersonaCard(
                        contact = it,
                        onClickDelete = {
                            isDialogOpen = true
                            indexToDelete = index
                            contactName = "${it.nombre} ${it.apellido}"
                        },
                        onClickEdit = { onEdit(it.telefono) }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay contactos registrados",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6.copy(
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.SemiBold
                    )
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
                    modifier = Modifier.padding(8.dp).fillMaxSize(),
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
                                contactStorage.delete(indexToDelete)
                                contactStorage.save()
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