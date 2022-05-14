package com.mike.agenda

import java.io.*
import java.net.URISyntaxException
import java.util.*
import javax.swing.JOptionPane

/**
 *
 * @author mike
 */
class Archivo(private val nombre: String) {
    private fun obtenerArchivo(): File? {
        return try {
            val url = javaClass.classLoader.getResource("archivos/$nombre")
            File(url.toURI())
        } catch (ex: URISyntaxException) {
            ex.printStackTrace()
            null
        }
    }

    fun obtenerTextoDelArchivo(): LinkedList<String?>? {
        var lineasDeTexto: LinkedList<String?>? = null
        try {
            val archivo = obtenerArchivo()
            if (archivo!!.exists()) {
                lineasDeTexto = LinkedList<String?>()
                val br = BufferedReader(FileReader(archivo))
                var linea: String?
                while (br.readLine().also { linea = it } != null) {
                    println(linea)
                    lineasDeTexto.add(linea)
                }
                br.close()
            } else {
                JOptionPane.showMessageDialog(null, "El archivo solicitado no existe favor de ingresar uno valido")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            JOptionPane.showMessageDialog(null, "Error al leer el archivo")
        }
        return lineasDeTexto
    }

    fun registrar(linea: String?): Boolean {
        val archivo = obtenerArchivo()
        try {
            if (archivo!!.exists()) {
                val fw = FileWriter(archivo, true)
                val bw = BufferedWriter(fw)
                val pw = PrintWriter(bw)
                pw.println(linea)
                pw.flush()
                pw.close()
                return true
            }
        } catch (error: Exception) {
            error.printStackTrace()
        }
        return false
    }

    fun borrarContenido(): Boolean {
        try {
            val archivo = obtenerArchivo()
            val directorio = archivo!!.parent
            archivo.delete()
            FileWriter("$directorio/contactos.txt", true)
            return true
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return false
    }
}