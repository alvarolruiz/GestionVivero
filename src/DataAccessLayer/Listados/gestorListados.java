package DataAccessLayer.Listados;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class gestorListados {
    private final String SELECT_CLIENTES = "SELECT * FROM Clientes";
    private Connection conexion;

    public gestorListados() {
       conexion = new DatosConexion().getConexion();
    }

    public List<Cliente> getClientes(){
        try{
            Statement statement = conexion.createStatement();
            ResultSet result = statement.executeQuery(SELECT_CLIENTES);
            Cliente cliente = null;
            while (result.next()){
                cliente = new Cliente();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
