import entities.Contact
import entities.User
import java.io.*

open class FileAccess {

    fun <T> dump(fileName: String, value: T) {
        val file = FileOutputStream(fileName)
        val outStream = ObjectOutputStream(file)

        outStream.writeObject(value)

        outStream.close()
        file.close()
    }

    fun fillDefaultData(fileName: String) {
        val file = File(fileName)
        if (!file.exists()) {
            file.createNewFile()
            when (fileName) {
                "contactos.txt" -> {
                    val value = arrayListOf(
                        Contact("Francisco", "Dueñas Muñoz", "francisco@email.com", 5589234563),
                        Contact("Paul Isaac", "Garcia Maturano", "isaac@correo.com.mx", 5590123783),
                        Contact("Fulanito", "de Apellido","fulanito@hotmail.com", 5951140476),
                        Contact("Eduardo Gamaliel", "Garcia Zavala", "gamaliel@gmail.com", 5951140478),
                        Contact("Miguel Alexis", "Portilla Perez", "miguel@ejemplo.com", 5951150131)
                    )
                    dump(fileName, value)
                }
                "usuario.txt" -> {
                    val value = User("Gamaliel", "GAMA3012")
                    dump(fileName, value)
                }
            }
        }
    }

    fun <T> load(fileName: String): T {
        fillDefaultData(fileName)
        val file = FileInputStream(fileName)
        val inStream = ObjectInputStream(file)

        val item = inStream.readObject() as T

        inStream.close()
        file.close()

        return item
    }
}