import entities.Contact
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

open class FileAccess {

    private val fileName = "contactos.txt"

    fun dump(contacts: ArrayList<Contact>){
        val file = FileOutputStream(fileName)
        val outStream = ObjectOutputStream(file)

        outStream.writeObject(contacts)

        outStream.close()
        file.close()
    }

    fun load(): ArrayList<Contact> {
        val file = FileInputStream(fileName)
        val inStream = ObjectInputStream(file)

        val item = inStream.readObject() as ArrayList<Contact>

        inStream.close()
        file.close()

        return item
    }
}