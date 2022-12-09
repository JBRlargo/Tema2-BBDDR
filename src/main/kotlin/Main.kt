import controlador.Controlador
import vista.CVista

fun main() {

    val conexion1: GestorBBDD = GestorBBDD.getInstance()

    conexion1.conectarBBDD()

    val v = CVista()
    val c = Controlador(v)

    c.onStart()

}