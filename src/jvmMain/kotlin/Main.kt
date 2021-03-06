// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import windows.*

@Composable
@Preview
fun App() {
    var screenState by rememberSaveable { mutableStateOf<AppScreens>(AppScreens.Login) }
    MaterialTheme {
        when (val screen = screenState) {
            is AppScreens.ContactList ->
                ContactList(
                    onEdit = {
                        screenState = AppScreens.UpdateContact(it)
                    },
                    onClickCreate = { screenState = AppScreens.CreateContact },
                    onSearchTelefono = { screenState = AppScreens.SearchContactByTelefono },
                    onSearchNombre = { screenState = AppScreens.SearchContactByNombre },
                    onLogout = { screenState = AppScreens.Login }
                )
            is AppScreens.UpdateContact -> UpdateContact(
                numeroOriginal = screen.numero,
                onBack = { screenState = AppScreens.ContactList }
            )
            is AppScreens.CreateContact -> CreateContact { screenState = AppScreens.ContactList }

            is AppScreens.SearchContactByTelefono -> SearchContactByTelefono { screenState = AppScreens.ContactList }

            is AppScreens.SearchContactByNombre -> SearchContactByNombre { screenState = AppScreens.ContactList }

            is AppScreens.Login -> Login { screenState = AppScreens.ContactList }
        }

    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "Agenda de Contactos",
        state = rememberWindowState(placement = WindowPlacement.Maximized)
    ) {
        App()
    }
}
