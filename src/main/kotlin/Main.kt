fun main() {


    val conexion1: GestorBBDD = GestorBBDD.getInstance()

    conexion1.conectarBBDD()

    println("(1). Ingresar datos")
    println("(2). Consulta general")
    println("(3). Consultar dato determinado")
    println("(4). Actualización de registro")
    println("(5). Eliminación de registro")
    println("(0). Salir de la aplicación")
    var opcion : Int = readLine()?.toInt() as Int

    when(opcion){
        1 -> conexion1.insertarFilaProductos()
        2 -> println(conexion1.selectAll())
        3 -> conexion1.selectDato(readln())
        4 -> conexion1.update(readln())
        5 -> conexion1.delete()
        0 -> conexion1.desconexionBBDD()

    }



    conexion1.selectAll()
    //conexion1.update()
    conexion1.selectAll()
    //conexion1.delete()
    conexion1.selectAll()


    conexion1.desconexionBBDD()



}