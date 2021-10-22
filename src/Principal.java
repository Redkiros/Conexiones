import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Connection miCon= lecturaDatos.conectar();
            PreparedStatement stnt = miCon.prepareStatement("select * from cancion where autor like 'El Chivi'");
            ResultSet results = stnt.executeQuery();
            while (results.next()){
                System.out.println("Nombre: "+results.getString("nombre"));
                System.out.println("Duracion: "+results.getString("duracion"));
                System.out.println("Autor: "+results.getString("autor"));
            }
        } catch (SQLException throwables) {
            System.out.println("Excepcion "+throwables);
        }
    }
}
