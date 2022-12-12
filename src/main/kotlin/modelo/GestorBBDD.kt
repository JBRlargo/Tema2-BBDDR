package modelo

import java.sql.*


class GestorBBDD private constructor(val url: String, val bd: String, val user: String, val pass: String){

//    val url: String = "jdbc:mysql://localhost/"
//    val bd: String = "almacen"
//    val user: String = "root"
//    val pass: String = ""

    companion object{
        private var instance: GestorBBDD? = null

        fun getInstance(url: String, bd: String, user: String, pass: String) : GestorBBDD {
            if(instance == null) {
                instance = GestorBBDD(url, bd, user, pass)
            }
            return instance!!

        }
    }
    private var con : Connection? = null
    private var savePoint: Savepoint? = null

    fun conectarBBDD(){
        try {
            // Cargamos el Driver que permite conectarme a una BBDD Mysql
            //Ya tendríamos un "traductor" entre MySQL y nuestra aplicación
            Class.forName("com.mysql.cj.jdbc.Driver")

            //Obtenemos un objeto Connection.
            //Ese objeto ES la conexión que se ha realizado con la base de datos
            //Utilizamos la clase DriverManager
            con = DriverManager.getConnection(url + bd, user, pass)

//            if (con != null)
//                println("[Conexion realizada]")
//            else
//                println("No existe conexion a la Base de Datos")
        }
        catch (e: ClassNotFoundException){
            e.printStackTrace()
        } catch (e: SQLException){
            e.printStackTrace()
        }
    }

    fun desconexionBBDD(){
        if(con != null){
            con!!.close()
        }
    }

    fun insertarFilaProductos(producto: Producto): Int?{
        try{
            if(con != null) {
                con!!.autoCommit = false
                val pSProducto : PreparedStatement = con!!.prepareStatement(Sentencias.insertFila)
                pSProducto.setString(1, producto.nombre)
                pSProducto.setFloat(2, producto.precio!!)
                val nFilas: Int = pSProducto.executeUpdate()
                con!!.commit()

                return nFilas

            }else {
                return null
            }
        }catch (e: Exception){
            //con!!.rollback(savePoint)
            return null
        }
    }

    fun selectAll(): List<Producto>?{
        try {
            if(con != null){
                val st : Statement = con!!.createStatement()
                val rs1 : ResultSet = st.executeQuery(Sentencias.selectAll)

                val lista: MutableList<Producto> = mutableListOf()

                while (rs1.next()) {

                    val p = Producto(rs1.getString(1), rs1.getFloat(2))
                    lista.add(p)

                }

                return lista.toList()

            } else {
                return null
            }
        } catch (e: Exception) {
            return null
        }
    }
    fun selectDato(dato :String): Producto?{
        if(con != null){
            val pS : PreparedStatement = con!!.prepareStatement(Sentencias.selectDato)
            pS.setString(1, dato)
            val rs1 : ResultSet = pS.executeQuery()

            var producto: Producto? = null


            while (rs1.next()) {
                producto = Producto(rs1.getString(1), rs1.getFloat(2))

            }

            return producto
        } else {
            return null
        }
    }

    fun updateNombre(producto: Producto, dato :String): Int?{
        try{
            con!!.autoCommit = false
        if(con != null) {
            val psUpdate: PreparedStatement = con!!.prepareStatement(Sentencias.actualizarNombre)
            psUpdate.setString(1, dato)
            psUpdate.setString(2, producto.nombre)

            val nFilas: Int = psUpdate.executeUpdate()
            con!!.commit()

            return nFilas
        }else {
            return null
        }
        }catch (e: Exception){
            con!!.rollback(savePoint)
            return null
        }
    } fun updatePrecio(producto: Producto, dato :Float): Int?{
        try{
            con!!.autoCommit = false
            if(con != null) {
                val psUpdate: PreparedStatement = con!!.prepareStatement(Sentencias.actualizarPrecio)
                psUpdate.setFloat(1, dato)
                psUpdate.setString(2, producto.nombre)
                val nFilas: Int = psUpdate.executeUpdate()
                con!!.commit()

                return nFilas
            }else {
                return null
            }
        }catch (e: Exception){
            con!!.rollback(savePoint)
            return null
        }
    }

    fun delete(dato:String):Int?{
        try{
            con!!.autoCommit = false
            if(con != null) {
                val pSDelete: PreparedStatement = con!!.prepareStatement(Sentencias.borrarFila)
                pSDelete.setString(1, dato)
                val nFilas: Int = pSDelete.executeUpdate()
                con!!.commit()
                return nFilas
        }else {
            return null
        }
        }catch (e: Exception){
            con!!.rollback(savePoint)
            return null
        }
    }

    fun crearTabla() {
        try {
            if(con != null) {
                val statement: Statement = con!!.createStatement()
                statement.execute(Sentencias.crearTabla)
            }
        }catch (_: Exception){
        }
    }
}