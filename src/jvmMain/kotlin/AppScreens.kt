sealed class AppScreens {
    object ContactList : AppScreens()
    object CreateContact : AppScreens()
    data class UpdateContact(val numero: Long) : AppScreens()

    object SearchContact : AppScreens()
}
