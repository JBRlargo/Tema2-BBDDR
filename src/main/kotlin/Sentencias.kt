object Sentencias {
    val selectAll: String = "SELECT * FROM productos;"
    val insertFila: String = "INSERT INTO Productos VALUES('Manzana',0.56);"
    val actualizarFila: String = "UPDATE Productos SET Nombre = 'Pera', Precio = 0.83 WHERE Nombre = 'Manzana';"
    val borrarFila: String = "DELETE FROM Productos WHERE Nombre = 'Pera';"
}