package vista

import modelo.Producto

class CVista {
    enum class Operador {
        BUSQUEDA, MODIFICACION, ELIMINACION
    }

    /*
Función que da inicio
Devuelvo un entero para mostrar cuál operador quiere el usuario
Si devuelve nulo es que no ha introducido un valor
 */
    fun inicio(): Int? {
        print(
            "Bienvenido a la aplicación" + separador() +
                    "(1). Ingresar datos" + separador() +
                    "(2). Consulta general" + separador() +
                    "(3). Consultar dato determinado" + separador() +
                    "(4). Actualización de registro" + separador() +
                    "(5). Eliminación de registro" + separador() +
                    "(0). Salir de la aplicación" + separador() +
                    "Elija la operación que quiere realizar: "
        )
        return readln().toIntOrNull()
    }

    /*
    Despide la aplicación
     */
    fun despedirse() {
        println("Cerrando aplicación. Hasta luego.")
    }

    /*
Si el inicio da error, esta función será llamada para avisar al usuario
 */
    fun errorInicio(op: Int?) {
        if (op == null) {
            print("Por favor, introduzca un entero válido.")
        } else {
            print("Por favor, introduzca un entero dentro del rango.")
        }
        println(" Solo son válidos los valores del '0' al '5'.")
        print(separador())
    }

    /*
Si el usuario llama a la función (1), comenzará el ingreso de datos
 */
    fun ingresoDatos(): Producto {
        print(
            "Comencemos la ingresión de datos" + separador() +
                    "Escriba el nombre del producto: "
        )
        val nombreProducto = readln()
        print("Escriba el precio del producto: ")
        val precioProducto: Float? = readln().toFloatOrNull()
        return Producto(nombreProducto, precioProducto)
    }

    /*
    Si ha habido algún error al introducir el precio, se llamará a esta función
     */
    fun errorIngresoPrecio():String {
        print("Error a la hora de introducir el precio." + separador() +
        "¿Quiere introducirlo de nuevo (s/N)? ")
        return readln()
    }

    /*
    Una vez que se haya finalizado la inserción, se avisará al usuario si ha habido algún error
     */
    fun respuestaIngreso(insercion: Int?) {
        when(insercion) {
            0 -> println("No se ha podido ingresar el producto")
            1 -> println("Se ha ingresado el producto correctamente")
            else -> println("Error en la base de datos. No se puede añadir el producto")
        }
        print(separador())
    }

    /*
Si el usuario llama a la función (2), mostrará el contenido de toda la tabla
 */
    fun consultaGeneral(lista: List<Producto>?) {
        if (lista != null) {
            if(lista.isNotEmpty()) {
                for(producto in lista) {
                    println("Producto:")
                    mostrarProducto(producto)
                }
            } else {
                println("No se ha encontrado ningún producto")
            }
        } else {
            println("La base de datos no está conectada")
        }
        print(separador())
    }

    /*
Si el usuario llama las funciones (3), (4) o (5), se llamará a esta función genérica a la que le especificará
lo que quiera realizar el usuario. Se manda el producto el cual quiere buscar
 */
    fun busqueda(operador: Operador): String {
        print("Ingrese el nombre del producto que quiere " + evaluadorOperador(operador) + ": ")
        return readln()
    }

    /*
Función para especificar lo que quiere realizar en la búsqueda genérica
 */
    private fun evaluadorOperador(operador: Operador): String {
        return when (operador) {
            Operador.BUSQUEDA -> "buscar"
            Operador.MODIFICACION -> "modificar"
            Operador.ELIMINACION -> "eliminar"
        }
    }

    /*
Función para mostrar producto en específico
 */
    fun consultaEspecifica(producto: Producto) {
        mostrarProducto(producto)
        print(separador())
    }

    /*
Mostrarle al usuario que ha habido un error al buscar el producto
 */
    fun errorBusquedaProducto(nombreProducto: String) {
        println("Error, el producto '$nombreProducto' no existe. Por favor, introduzca otro")
        print(separador())
    }

    /*
Pregunta al usuario que va a modificar del producto
 */
    fun modificarProducto(): Int? {
        print(
            "¿Qué atributo quiere modificar?" + separador() +
                    "(1) Nombre" + separador() +
                    "(2) Precio" + separador() +
                    "(0) Salir" + separador() +
            "Introduzca el operador: "
        )
        return readln().toIntOrNull()
    }

    /*
    Si el usuario ha introducido incorrectamente el operador, se le avisará
     */
    fun errorOperadorModificacion(): String {
        print("Error a la hora de introducir el operador." + separador() +
                "¿Quiere introducirlo de nuevo (s/N)? ")
        return readln()
    }

    /*
    Modificación de nombre del producto
    */
    fun modificarNombre(): String {
        print("Escribe el nuevo nombre del producto: ")
        return readln()
    }

    /*
Modificación del precio del producto
 */
    fun modificarPrecio(): Float? {
        print("Escribe el nuevo precio del producto: ")
        return readln().toFloatOrNull()
    }

    /*
Confirmación de la modificación del producto
 */
    fun productoModificado(modificacion: Int?) {
        when(modificacion) {
            0 -> println("No se ha podido modificar el producto")
            1 -> println("Se ha modificado el producto correctamente")
            else -> println("Error en la base de datos. No se puede modificar el producto")
        }
        print(separador())
    }

    /*
    Confirmación de la eliminación del producto
    */
    fun eliminacionRealizada(eliminacion: Int?) {
        when(eliminacion) {
            0 -> println("No se ha podido eliminar el producto")
            1 -> println("Se ha eliminado el producto correctamente")
            else -> println("Error en la base de datos. No se puede eliminar el producto")
        }
        print(separador())
    }

    /*
    Separador del sistema
     */
    fun separador(): String {
        return System.getProperty("line.separator")
    }

    /*
    Como se muestra el producto
     */
    private fun mostrarProducto(producto: Producto) {
        println("Nombre: " + producto.nombre + separador() +
                "Precio: " + producto.precio + "€")
    }
}