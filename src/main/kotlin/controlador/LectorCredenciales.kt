package controlador

import java.io.FileReader
import java.io.Reader
import java.util.*

object LectorCredenciales {
    // Lectura de conf
    private val properties = Properties()
    private val reader: Reader = FileReader(
        System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "resources" +
                System.getProperty("file.separator") +
                "credenciales.conf")
    var url = ""
    var bd = ""
    var user = ""
    var pass = ""

    init {
        properties.load(reader)
        url = properties.getProperty("url")
        bd = properties.getProperty("bd")
        user = properties.getProperty("user")
        pass = properties.getProperty("pass")
    }
}