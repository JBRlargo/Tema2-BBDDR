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
        if(con != null) {
            val st2: Statement = con!!.createStatement()
            val nFilas: Int = st2.executeUpdate(Sentencias.insertFila)

            if (nFilas > 0) println("${nFilas} afectada(s)") else println("Ninguna fila afectada")
        }else {
            println("[Base de Datos no conectada]")
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
}