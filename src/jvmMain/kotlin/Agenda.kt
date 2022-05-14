package com.mike.agenda

/**
 *
 * @author mike
 */
class Agenda(var nombre: String, var apellido: String, var email: String, var telefono: Int) {

    override fun toString(): String {
        return ("Agenda [apellido=" + apellido + ", email=" + email + ", nombre=" + nombre + ", telefono=" + telefono
                + "]")
    }
}