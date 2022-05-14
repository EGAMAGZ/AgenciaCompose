// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import entities.Contact
import com.mike.agenda.ContactStorage
import windows.ContactList
import windows.CreateContact
import windows.UpdateContact

@Composable
@Preview
fun App() {
    val l = ContactStorage()
    l.cargarContactos();
    l.agregar(Contact("Miguel", "Portilla", "Mportilla@mail.com", 1112315));
    l.ordenarPorTelefonoQuickSort();
    l.imprimir();
    l.guardarContactos();
    var screenState by rememberSaveable { mutableStateOf<AppScreens>(AppScreens.ContactList) }

    MaterialTheme {
        when (val screen = screenState) {
            is AppScreens.ContactList ->
                ContactList(
                    onEdit = {
                        screenState = AppScreens.UpdateContact(it)
                    },
                    onClickCreate = { screenState = AppScreens.CreateContact }
                )
            is AppScreens.UpdateContact -> UpdateContact(
                numeroOriginal = screen.numero,
                onBack = { screenState = AppScreens.ContactList }
            )
            is AppScreens.CreateContact -> CreateContact()
        }

    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "Agenda de Contactos",
        state = rememberWindowState(width = 450.dp, height = 600.dp)
    ) {
        App()
    }
}
