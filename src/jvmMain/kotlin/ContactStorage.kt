import entities.Contact

class ContactStorage : FileAccess {

    private var contacts: ArrayList<Contact>? = null

    constructor() {
        this.contacts = this.load()
    }

    fun add(contact: Contact) =
        contacts?.add(contact)

    fun get(index: Int) =
        contacts?.get(index)

    fun delete(index: Int) =
        contacts?.removeAt(index)

    fun update(index: Int, contact: Contact) =
        contacts?.set(index, contact)

    fun count() =
        contacts?.count()

    fun getAll() =
        contacts

    fun save() =
        contacts?.let { this.dump(it) }
}