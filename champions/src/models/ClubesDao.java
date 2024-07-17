package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ClubesDao {
    private Connection conexion;

    public ClubesDao() {
        conexion = Conexion.conectar();
    }
  
    public List<Clubes> obtenerTodosLosClubes() {
        List<Clubes> clubes = new ArrayList<>();        
        String consulta = "SELECT * FROM clubes";
        
        try (PreparedStatement st = conexion.prepareStatement(consulta);            
             ResultSet rs = st.executeQuery()) {           
            while(rs.next()) {
                Clubes club = new Clubes();
                club.setId(rs.getInt("id"));
                club.setNombre(rs.getString("nombre"));
                club.setPais(rs.getString("pais"));
                club.setBombo(rs.getInt("bombo")); 
                clubes.add(club);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los clubes: " + e.getMessage());
        }
        return clubes;   
    }  
}
