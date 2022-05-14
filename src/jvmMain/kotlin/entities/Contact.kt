package entities

/**
 *
 * @author mike
 */
class Contact(var nombre: String, var apellido: String, var email: String, var telefono: Long) {

    override fun toString(): String {
        return ("Contact [apellido=" + apellido + ", email=" + email + ", nombre=" + nombre + ", telefono=" + telefono
                + "]")
    }
}