package vista

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
            "Bienvenido a la aplicación." + separador() +
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
Si el inicio da error, esta función será llamada para avisar al usuario
 */
    fun errorInicio(op: Int?) {
        if (op == null) {
            print("Por favor, introduzca un entero válido.")
        } else {
            print("Por favor, introduzca un entero dentro del rango.")
        }
        println("Solo son válidos los valores del '0' al '5'.")
    }

    /*
Si el usuario llama a la función (1), comenzará el ingreso de datos
 */
    fun ingresoDatos() {
        /*
    TODO Se tiene que devolver un producto
     */
        print(
            "Comencemos la ingresión de datos" + separador() +
                    "Escriba el nombre del producto: "
        )
        val nombreProducto = readln()
        print("Escriba el precio del producto: ")
        val precioProducto: Float? = readln().toFloatOrNull()
    }

    /*
Si el usuario llama a la función (2), mostrará el contenido de toda la tabla
 */
    fun consultaGeneral() {
        /*
    TODO Recibir una lista y mostrarla
     */
    }

    /*
Si el usario llama las funciones (3), (4) o (5), se llamará a esta función genérica a la que le especificará
lo que quiera realizar el usuario. Se manda el producto el cual quiere buscar
 */
    fun busqueda(operador: Operador): String {
        print("Ingrese el nombre del producto que busca a " + evaluadorOperador(operador) + ": ")
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
    fun consultaEspecifica() {
        /*
    TODO Recibir un producto y mostrarlo por pantalla
     */
    }

    /*
Mostrarle al usuario que ha habido un error al buscar el producto
 */
    fun errorBusquedaProducto(nombreProducto: String) {
        println("Error, el producto '$nombreProducto' no existe. Por favor, introduzca otro")
    }

    /*
Pregunta al usuario que va a modificar del producto
 */
    fun modificarProducto(): Int? {
        print(
            "¿Qué atributo quiere modificar?" + separador() +
                    "(1) Nombre" + separador() +
                    "(2) Precio" + separador() +
                    "(0) Salir"
        )
        return readln().toIntOrNull()
    }

    /*
Modificación de nombre del producto
 */
    fun modificarNombre(): String {
        print("Escribe el nuevo nombre del producto")
        return readln()
    }

    /*
Modificación del precio del producto
 */
    fun modificarPrecio(): Float? {
        print("Escribe el nuevo precio del producto")
        return readln().toFloatOrNull()
    }

    /*
Confirmación de la modificación del producto
 */
    fun productoModificado() {
        println("El producto ha sido modificado")
    }

    /*
Informar de error a la hora de modificar el precio
 */
    fun errorModificacionPrecio() {
        println("Error a la hora de modificar el precio. Introduzca un valor válido")
    }

    /*
Confirmación de la eliminación del producto
 */
    fun eliminacionRealizada() {
        println("Se ha eliminado el producto")
    }

    private fun separador(): String {
        return System.getProperty("line.separator")
    }
}