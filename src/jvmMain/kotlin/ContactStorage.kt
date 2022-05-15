import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import entities.Contact

class ContactStorage : FileAccess {

    private var contacts: ArrayList<Contact> by mutableStateOf(arrayListOf())

    constructor() {
        this.contacts = this.load()
    }

    fun add(contact: Contact) =
        contacts.add(contact)

    fun get(index: Int) =
        contacts[index]

    fun delete(index: Int) =
        contacts.removeAt(index)

    fun update(index: Int, contact: Contact) =
        contacts.set(index, contact)

    fun count() =
        contacts.count()

    fun getAll() =
        contacts

    fun save() =
        contacts.let { this.dump(it) }
}