package modelo

object Sentencias {
    val selectAll: String = "SELECT * FROM productos;"
    val selectDato: String = "SELECT * FROM productos where Nombre = '"
    val insertFila: String = "INSERT INTO modelo.Productos VALUES('Manzana',0.56);"
    val actualizarFila: String = "UPDATE modelo.Productos SET Nombre = 'Pera', Precio = 0.83 WHERE Nombre = '"
    val borrarFila: String = "DELETE FROM modelo.Productos WHERE Nombre = '"
}