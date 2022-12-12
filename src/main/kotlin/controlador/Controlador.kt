package controlador

import modelo.GestorBBDD
import vista.CVista
import vista.CVista.Operador
import java.util.*


class Controlador(private val  vista: CVista) {

    fun onStart() {

        val lectorCredenciales = LectorCredenciales


        val modelo: GestorBBDD = GestorBBDD.getInstance(lectorCredenciales.url, lectorCredenciales.bd, lectorCredenciales.user, lectorCredenciales.pass)
        modelo.conectarBBDD()

        when(val opc: Int? = vista.inicio()){
            1-> {
                val producto = vista.ingresoDatos()
                var validacion = false
                while (!validacion) {
                    if(producto.precio == null) {
                        val op = vista.errorIngresoPrecio()
                        if (op.lowercase(Locale.getDefault()) == "n" || op.lowercase(Locale.getDefault()) != "s") {
                            validacion = true
                        }else {
                            producto.precio = vista.modificarPrecio()
                        }
                    } else {
                        validacion = true
                        vista.respuestaIngreso(modelo.insertarFilaProductos(producto))
                    }
                }
                onStart()
            }
            2-> {
                vista.consultaGeneral(modelo.selectAll())
                onStart()

            }
            3-> {
                val nombreProducto =vista.busqueda(Operador.BUSQUEDA)
                val producto = modelo.selectDato(nombreProducto)
                if (producto == null) {
                    vista.errorBusquedaProducto(nombreProducto)
                } else {
                    vista.consultaEspecifica(producto)
                }
                onStart()
            }
            4-> {
                val nombreProducto = vista.busqueda(Operador.MODIFICACION)
                val producto = modelo.selectDato(nombreProducto)
                if (producto == null) {
                    vista.errorBusquedaProducto(nombreProducto)
                } else {
                    var validacionOperador = false
                    while (!validacionOperador) {
                        when(vista.modificarProducto()) {
                            1 -> {
                                validacionOperador = true
                                val nuevoNombreProducto = vista.modificarNombre()
                                vista.productoModificado(modelo.updateNombre(producto, nuevoNombreProducto))
                            }
                            2 -> {
                                validacionOperador = true
                                var nuevoPrecioProducto = vista.modificarPrecio()
                                var validacion = false
                                while (!validacion) {
                                    if(nuevoPrecioProducto == null) {
                                        val op = vista.errorIngresoPrecio()
                                        if (op.lowercase(Locale.getDefault()) == "n" || op.lowercase(Locale.getDefault()) != "s") {
                                            validacion = true
                                        }else {
                                            nuevoPrecioProducto = vista.modificarPrecio()
                                        }
                                    } else {
                                        validacion = true
                                        vista.productoModificado(modelo.updatePrecio(producto, nuevoPrecioProducto))
                                    }
                                }
                            }
                            else -> {
                                val op = vista.errorOperadorModificacion()
                                if (op.lowercase(Locale.getDefault()) == "n" || op.lowercase(Locale.getDefault()) != "s") {
                                    validacionOperador = true
                                }
                            }
                        }
                    }
                }
                onStart()
            }
            5-> {

                val nombreProducto = vista.busqueda(Operador.ELIMINACION)
                val producto = modelo.selectDato(nombreProducto)
                if (producto == null) {
                    vista.errorBusquedaProducto(nombreProducto)
                } else {
                    vista.eliminacionRealizada(modelo.delete(nombreProducto))
                }
                onStart()
            }
            0 -> {
                modelo.desconexionBBDD()
                vista.despedirse()
            }
            else -> {
                vista.errorInicio(opc)
                onStart()
            }

        }

    }

    fun iniciar() {
        val lectorCredenciales = LectorCredenciales


        val modelo: GestorBBDD = GestorBBDD.getInstance(lectorCredenciales.url, lectorCredenciales.bd, lectorCredenciales.user, lectorCredenciales.pass)
        modelo.conectarBBDD()

        modelo.crearTabla()
    }
}