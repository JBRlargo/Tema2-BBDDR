package controlador

import GestorBBDD
import Productos
import vista.CVista
import vista.CVista.Operador


class Controlador(private val  vista: CVista) {

    fun onStart()/*: Int*/{
        var opc: Int? = vista.inicio()

        val modelo: GestorBBDD = GestorBBDD.getInstance()
        modelo.conectarBBDD()

        when(opc){
            1-> {
                //vista.ingresoDatos(modelo.insertarFilaProductos())
                onStart()
            }
            2-> {
                //vista.consultaGeneral(modelo.selectAll())
                onStart()

            }
            3-> {
                modelo.selectDato(vista.busqueda(Operador.BUSQUEDA))
                onStart()
            }
            4-> {
                modelo.update(vista.busqueda(Operador.MODIFICACION))
                onStart()
            }
            5-> {
                modelo.delete(vista.busqueda(Operador.ELIMINACION))
                onStart()
            }
            0 -> modelo.desconexionBBDD()
        }
        //return opc;

    }
}