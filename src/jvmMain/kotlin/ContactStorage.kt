import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import entities.Contact

class ContactStorage : FileAccess {

    private var contacts by mutableStateOf(arrayListOf<Contact>())
    private val fileName = "contactos.txt"

    constructor() {
        this.contacts = this.load(fileName)
    }

    fun add(contact: Contact) {
        contacts.add(contact)
        sort()
    }

    fun get(index: Int) =
        contacts[index]

    fun delete(index: Int) {
        contacts.removeAt(index)
        sort()
    }

    fun update(index: Int, contact: Contact) {
        contacts.set(index, contact)
        sort()
    }

    fun count() =
        contacts.count()

    fun sort() {
        contacts = quickSort(contacts)
    }

    fun quickSort(array: ArrayList<Contact>): ArrayList<Contact> {
        if (array.size <= 1) {
            return array
        }
        val pivot = array[array.size / 2]
        val less = array.filter { it.telefono < pivot.telefono } as ArrayList<Contact>
        val equal = array.filter { it.telefono == pivot.telefono } as ArrayList<Contact>
        val greater = array.filter { it.telefono > pivot.telefono } as ArrayList<Contact>
        return (quickSort(less) + equal + quickSort(greater)) as ArrayList<Contact>
    }

    fun getAll() =
        contacts

    fun save() =
        this.dump(fileName, contacts)

    fun exists(telefono: Long): Boolean {
        val index = binarySearch(telefono) { it.telefono }
        return index >= 0
    }

    private fun binarySearch(key: Long, keyExtractor: (Contact) -> Long): Int {
        var low = 0
        var high = contacts.count() - 1
        while (low <= high) {
            val mid = (low + high) / 2
            val midVal = keyExtractor(contacts[mid])
            when {
                midVal < key -> low = mid + 1
                midVal > key -> high = mid - 1
                else -> return mid
            }
        }
        return -1
    }

    fun findIndex(telefono: Long): Int {
        return binarySearch(telefono) { it.telefono }
    }
}