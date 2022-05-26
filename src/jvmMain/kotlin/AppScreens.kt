sealed class AppScreens {
    object ContactList : AppScreens()
    object CreateContact : AppScreens()
    data class UpdateContact(val numero: Long) : AppScreens()

    object SearchContactByTelefono : AppScreens()

    object SearchContactByNombre : AppScreens()

    object Login : AppScreens()
}
