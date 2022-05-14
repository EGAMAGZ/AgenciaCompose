package com.mike.agenda

import java.util.*

class listaContactos {
    private var contactos: LinkedList<Agenda?>? = null
    fun agregar(contacto: Agenda?) {
        contactos!!.add(contacto)
    }

    fun eliminar(indice: Int) {
        contactos!!.removeAt(indice)
    }

    fun obtener(indice: Int): String {
        return contactos!![indice].toString()
    }

    /**
     * Metodo que carge los contactos de la lista de
     * Contactos
     */
    fun cargarContactos() {
        val bd = DatosContactos()
        contactos = bd.obtener()
    }

    /**
     * Método que guarda los contactos en el archivo
     * Primero elimina y despues carga todo lo que tiene
     * en la lista de contactos
     */
    fun guardarContactos() {
        val bd = DatosContactos()
        bd.borrarTodo()
        for (i in contactos!!.indices) {
            bd.registrarProducto(contactos!![i])
        }
    }

    /**
     * Método que imprime todos los contactos
     * dependiendo de la lista almacenada en memoria ram
     */
    fun imprimir() {
        for (i in contactos!!.indices) {
            println(
                contactos!![i]!!.telefono
                    .toString() + " " + contactos!![i]!!.nombre + " " + contactos!![i]!!.apellido
            )
        }
    }

    /**
     * Método que ordena el atributo telefoo de la clase Agenda
     * Por metodo burbuja
     */
    fun ordenarPorTelefono() {
        var aux: Agenda?
        for (i in 0 until contactos!!.size - 1) {
            for (j in 0 until contactos!!.size - 1) {
                if (contactos!![j]!!.telefono > contactos!![j + 1]!!.telefono) {
                    aux = contactos!![j]
                    contactos!![j] = contactos!![j + 1]
                    contactos!![j + 1] = aux
                }
            }
        }
    }

    /**
     * Método que ordena el atributo telefoo de la clase Agenda
     * Por metodo quick sort
     */
    fun ordenarPorTelefonoQuickSort() {
        quickSort(contactos, 0, contactos!!.size - 1)
    }

    private fun quickSort(contactos: LinkedList<Agenda?>?, izq: Int, der: Int) {
        var i = izq
        var j = der
        val pivote = contactos!![(i + j) / 2]!!.telefono
        while (i <= j) {
            while (contactos[i]!!.telefono < pivote) {
                i++
            }
            while (contactos[j]!!.telefono > pivote) {
                j--
            }
            if (i <= j) {
                val aux = contactos[i]
                contactos[i] = contactos[j]
                contactos[j] = aux
                i++
                j--
            }
        }
        if (izq < j) {
            quickSort(contactos, izq, j)
        }
        if (i < der) {
            quickSort(contactos, i, der)
        }
    }
}