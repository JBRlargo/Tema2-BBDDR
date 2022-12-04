fun main() {


    val conexion1: GestorBBDD = GestorBBDD.getInstance()

    conexion1.conectarBBDD()

    //conexion1.insertarFilaProductos()
    conexion1.selectAll()
    conexion1.desconexionBBDD()


}