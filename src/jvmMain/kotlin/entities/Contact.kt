package entities

import java.io.Serializable

class Contact(var nombre: String, var apellido: String, var email: String, var telefono: Long): Serializable {

    override fun toString(): String {
        return ("Contact [apellido=" + apellido + ", email=" + email + ", nombre=" + nombre + ", telefono=" + telefono
                + "]")
    }
}