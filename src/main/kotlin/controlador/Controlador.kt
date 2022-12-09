package controlador

import GestorBBDD
import Productos
import vista.CVista


class Controlador(private val  vista: CVista) {

    fun onStart(): Int{
        var opc: Int = vista.inicio()
        return opc;

    }

    fun onAlta(){
        val modelo: GestorBBDD = GestorBBDD.getInstance()
        modelo.conectarBBDD()

        val productor :Productos?




    }



}