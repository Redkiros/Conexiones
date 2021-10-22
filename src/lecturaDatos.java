import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class lecturaDatos {
    private static final  String URL = "jdbc:mysql://localhost:3306/canciones";
    private static final String USUARIO = "jorge";
    private static final  String CLAVE = "admin1234";

    public static Connection conectar(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión OK");

        }catch (SQLException e){
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }
        return conexion;
    }

    public static void main(String[] args) {
        Connection miCon= conectar();

    }
}
