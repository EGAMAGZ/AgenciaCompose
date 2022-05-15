import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import entities.Contact

class ContactStorage : FileAccess {

    private var contacts by mutableStateOf(mutableListOf<Contact>())

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
        contacts.let { this.dump(it as ArrayList<Contact>) }

    fun exists(telefono: Long): Boolean {
        val index = contacts.binarySearchBy(key = telefono) { it.telefono }
        return index >= 0
    }

    fun findIndex(telefono: Long): Int {
        return contacts.binarySearchBy(key = telefono) { it.telefono }
    }
}