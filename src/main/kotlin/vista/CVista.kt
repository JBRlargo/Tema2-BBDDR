package vista

class CVista {
    /*
    Función que da inicio
     */
    fun inicio(): Int? {
        print("Bienvenido a la aplicación." + separador() +
                "(1). Ingresar datos" + separador() +
                "(2). Consulta general" + separador() +
                "(3). Consultar dato determinado" + separador() +
                "(4). Actualización de registro" + separador() +
                "(5). Eliminación de registro" + separador() +
                "(0). Salir de la aplicación" + separador() +
                "Elija la operación que quiere realizar: ")
        return  readln().toIntOrNull()
    }
}
private fun separador(): String{
    return System.getProperty("line.separator")
}