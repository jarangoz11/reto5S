package co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.util.JDBCUtilities;

public class ProyectoBancoDao {

    public List<ProyectoBancoVo> listarProyectos(String banco) throws SQLException {
        List<ProyectoBancoVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            var query = "SELECT p.ID_Proyecto ID, p.Constructora , p.Ciudad , p.Clasificacion , t.Estrato , l.Nombre ||' '||  l.Primer_Apellido ||' '|| l.Segundo_Apellido LIDER"
                + " FROM Proyecto p"  
                + " JOIN Tipo t ON (p.ID_Tipo=t.ID_Tipo )"
                + " JOIN Lider l ON (p.ID_Lider = l.ID_Lider)"
                + " WHERE p.Banco_Vinculado = ?"
                + " ORDER BY p.Fecha_Inicio DESC, Ciudad ASC , Constructora";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, banco);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new ProyectoBancoVo();
                vo.setId(rset.getInt("ID"));
                vo.setConstructora(rset.getString("CONSTRUCTORA"));
                vo.setCiudad(rset.getString("CIUDAD"));
                vo.setClasificacion(rset.getString("CLASIFICACION"));
                vo.setEstrato(rset.getInt("ESTRATO"));
                vo.setLider(rset.getString("LIDER"));

                respuesta.add(vo);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta;
    }
    
}
