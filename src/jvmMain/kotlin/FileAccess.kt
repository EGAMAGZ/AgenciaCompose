import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

open class FileAccess {

    fun <T> dump(fileName: String, value: T) {
        val file = FileOutputStream(fileName)
        val outStream = ObjectOutputStream(file)

        outStream.writeObject(value)

        outStream.close()
        file.close()
    }

    fun <T> load(fileName: String): T {
        val file = FileInputStream(fileName)
        val inStream = ObjectInputStream(file)

        val item = inStream.readObject() as T

        inStream.close()
        file.close()

        return item
    }
}