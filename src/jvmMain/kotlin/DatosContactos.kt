package com.mike.agenda

import java.util.*

/**
 *
 * @author mike
 */
class DatosContactos {
    fun obtener(): LinkedList<Agenda?>? {
        var productos: LinkedList<Agenda?>? = null
        val archivo = Archivo("contactos.txt")
        val lineas = archivo.obtenerTextoDelArchivo()
        if (lineas != null) {
            productos = LinkedList<Agenda?>()
            for (i in lineas.indices) {
                val linea = lineas[i]
                val tokens = StringTokenizer(linea, ";")
                val telefono = tokens.nextToken().toInt()
                val nombre = tokens.nextToken()
                val apellido = tokens.nextToken()
                val email = tokens.nextToken()
                productos.add(Agenda(nombre, apellido, email, telefono))
            }
        }
        return productos
    }

    fun registrarProducto(p: Agenda?): Boolean {
        val archivo = Archivo("contactos.txt")
        return archivo.registrar(
            p?.telefono.toString() + ";"
                    + p?.nombre + ";"
                    + p?.apellido + ";"
                    + p?.email
        )
    }

    fun borrarTodo(): Boolean {
        val archivo = Archivo("contactos.txt")
        return archivo.borrarContenido()
    }
}