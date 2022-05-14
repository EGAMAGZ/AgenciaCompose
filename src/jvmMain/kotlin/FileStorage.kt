package com.mike.agenda

import entities.Contact
import java.util.*

/**
 *
 * @author mike
 */
class FileStorage {
    fun obtener(): LinkedList<Contact?>? {
        var productos: LinkedList<Contact?>? = null
        val fileAccess = FileAccess("contactos.txt")
        val lineas = fileAccess.obtenerTextoDelArchivo()
        if (lineas != null) {
            productos = LinkedList<Contact?>()
            for (i in lineas.indices) {
                val linea = lineas[i]
                val tokens = StringTokenizer(linea, ";")
                val telefono = tokens.nextToken().toLong()
                val nombre = tokens.nextToken()
                val apellido = tokens.nextToken()
                val email = tokens.nextToken()
                productos.add(Contact(nombre, apellido, email, telefono))
            }
        }
        return productos
    }

    fun registrarProducto(p: Contact?): Boolean {
        val fileAccess = FileAccess("contactos.txt")
        return fileAccess.registrar(
            p?.telefono.toString() + ";"
                    + p?.nombre + ";"
                    + p?.apellido + ";"
                    + p?.email
        )
    }

    fun borrarTodo(): Boolean {
        val fileAccess = FileAccess("contactos.txt")
        return fileAccess.borrarContenido()
    }
}