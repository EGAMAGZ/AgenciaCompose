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

    private fun fillDefaultData(fileName: String) {
        val file = File(fileName)
        if (!file.exists()) {
            file.createNewFile()
            when (fileName) {
                "contactos.txt" -> {
                    val value = arrayListOf(
                        Contact("Francisco", "Dueñas Muñoz", "francisco@email.com", 5589234563),
                        Contact("Paul Isaac", "Garcia Maturano", "isaac@correo.com.mx", 5590123783),
                        Contact("Eduardo Gamaliel", "Garcia Zavala", "gamaliel@gmail.com", 5951140478),
                        Contact("Miguel Alexis", "Portilla Perez", "miguel@ejemplo.com", 5951150131),
                        Contact("Javier", "Hernandez", "javih@email.com", 5554955429),
                        Contact("Kathy", "F. Morgan", "KathyFMorgan@superrito.com", 5034315705),
                        Contact("Patricia", "B. Kelly", "PatriciaBKelly@gustr.com", 4142872239),
                        Contact("Veronica", "D. Churchwell", "VeronicaDChurchwell@gustr.com", 3109406224),
                        Contact("Thomas", "V. Larson", "ThomasVLarson@gustr.com", 4026735143),
                        Contact("Bernardo", "A. Sumler", "BernardoASumler@superrito.com", 2167914096),
                        Contact("Christopher", "M. Roger", "ChristopherMRoger@superrito.com", 9567824383),
                        Contact("Gregory", "N. Riley", "GregoryNRiley@superrito.com", 8598018231),
                        Contact("Dorothy", "R. Edwards", "DorothyREdwards@superrito.com", 2486131090),
                        Contact("Judith", "T. Martin", "JudithTMartin@superrito.com", 7814044162),
                        Contact("Frank", "D. Hansen", "FrankDHansen@gustr.com", 8708430539),
                        Contact("Diane", "R. Santoro", "DianeRSantoro@gustr.com", 5186867166),
                        Contact("Manuel", "J. Gordon", "ManuelJGordon@superrito.com", 5734855475),
                        Contact("Charles", "P. Stauffer", "CharlesPStauffer@superrito.com", 9714092323),
                        Contact("Alfred", "B. Scott", "AlfredBScott@superrito.com", 3305592571),
                        Contact("Melinda", "R. Maggard", "MelindaRMaggard@gustr.com", 4105436982),
                        Contact("Kevin", "A. Fritz", "KevinAFritz@gustr.com", 6027581571),
                        Contact("David", "M. Downes", "DavidMDownes@superrito.com", 3374930805)
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