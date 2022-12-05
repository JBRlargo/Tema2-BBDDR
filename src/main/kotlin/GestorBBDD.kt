import java.sql.*


class GestorBBDD private constructor(){

    val url: String = "jdbc:mysql://localhost/"
    val bd: String = "almacen"
    val user: String = "root"
    val pass: String = ""

    companion object{
        private var instance: GestorBBDD? = null

        fun getInstance() :GestorBBDD{
            if(instance == null) {
                instance = GestorBBDD()
            }
            return instance!!

        }
    }
    private var con : Connection? = null
    var savePoint: Savepoint? = null

    fun conectarBBDD(){
        try {
            // Cargamos el Driver que permite conectarme a una BBDD Mysql
            //Ya tendríamos un "traductor" entre MySQL y nuestra aplicación
            Class.forName("com.mysql.cj.jdbc.Driver")

            //Obtenemos un objeto Connection.
            //Ese objeto ES la conexión que se ha realizado con la base de datos
            //Utilizamos la clase DriverManager
            con = DriverManager.getConnection(url + bd, user, pass)

            if (con != null)
                println("[Conexion realizada]")
            else
                println("No existe conexion a la Base de Datos")
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
            println("Desconexion realizada")
        } else{
            println("No existe conexion a la Base de Datos")
        }
    }

    fun insertarFilaProductos(){
        try{
            con!!.autoCommit = false
            if(con != null) {
                val st2: Statement = con!!.createStatement()
                val nFilas: Int = st2.executeUpdate(Sentencias.insertFila)
                con!!.commit()

                if (nFilas > 0) println("${nFilas} afectada(s)") else println("Ninguna fila afectada")

            }else {
                println("[Base de Datos no conectada]")
            }
        }catch (e: Exception){
            con!!.rollback(savePoint)
        }
    }

    fun selectAll(){
        if(con != null){
            val st : Statement = con!!.createStatement()
            val rs1 : ResultSet = st.executeQuery(Sentencias.selectAll)

            while (rs1.next()) {
                println(rs1.getString(1))
                println(rs1.getString(2))

            }
        } else {
            println("[Base de Datos no conectada]")
        }
    }

    fun update(){
        try{
            con!!.autoCommit = false
        if(con != null) {
            val st3: Statement = con!!.createStatement()
            val nFilas: Int = st3.executeUpdate(Sentencias.actualizarFila)
            con!!.commit()

            if (nFilas > 0) println("${nFilas} afectada(s)") else println("Ninguna fila afectada")
        }else {
            println("[Base de Datos no conectada]")
        }
        }catch (e: Exception){
            con!!.rollback(savePoint)
        }
    }

    fun delete(){
        try{
            con!!.autoCommit = false
        if(con != null) {
            val st4: Statement = con!!.createStatement()
            val nFilas: Int = st4.executeUpdate(Sentencias.actualizarFila)
            con!!.commit()

            if (nFilas > 0) println("${nFilas} afectada(s)") else println("Ninguna fila afectada")
        }else {
            println("[Base de Datos no conectada]")
        }
        }catch (e: Exception){
            con!!.rollback(savePoint)
        }
    }






}