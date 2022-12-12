import controlador.Controlador
import vista.CVista

fun main() {

    val v = CVista()
    val c = Controlador(v)
    c.iniciar()
    c.onStart()

}