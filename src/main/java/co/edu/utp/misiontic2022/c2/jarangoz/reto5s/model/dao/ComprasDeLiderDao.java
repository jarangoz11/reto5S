package co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.util.JDBCUtilities;

public class ComprasDeLiderDao {

    public List<ComprasDeLiderVo> listarProyectos() throws SQLException {
        List<ComprasDeLiderVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            var query = "SELECT  l.Nombre ||' '|| l.Primer_Apellido ||' '|| l.Segundo_Apellido LIDER, sum(c.Cantidad*mc.Precio_Unidad) VALOR"
            + " FROM Lider l"
            + " join Proyecto p on (l.ID_Lider=p.ID_Lider)" 
            + " join Compra c on (p.ID_Proyecto = c.ID_Proyecto)"
            + " join MaterialConstruccion mc on (c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion)"
            + " GROUP BY Lider" 
            + " ORDER BY VALOR DESC" 
            + " LIMIT 10";
            stmt = conn.prepareStatement(query);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new ComprasDeLiderVo();
                vo.setLider(rset.getString("LIDER"));
                vo.setValor(rset.getDouble("VALOR"));
                
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
