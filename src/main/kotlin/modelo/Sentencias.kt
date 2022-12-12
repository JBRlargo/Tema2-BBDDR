package modelo

object Sentencias {
    val selectAll: String = "SELECT * FROM productos;"
    val selectDato: String = "SELECT * FROM productos where nombre = ?"
    val insertFila: String = "INSERT INTO productos(nombre, precio) VALUES(?,?);"
    val actualizarNombre: String = "UPDATE productos SET nombre = ? WHERE nombre = ?"
    val actualizarPrecio: String = "UPDATE productos SET precio = ? WHERE nombre = ?"
    val borrarFila: String = "DELETE FROM productos WHERE nombre = ?"
    val crearTabla: String = "CREATE TABLE if not exists`productos` (`nombre` varchar(30) NOT NULL,`precio` decimal(10,2) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
}