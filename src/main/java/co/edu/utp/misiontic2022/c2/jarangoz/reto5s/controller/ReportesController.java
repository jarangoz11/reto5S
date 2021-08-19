package co.edu.utp.misiontic2022.c2.jarangoz.reto5s.controller;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ProyectosVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.dao.ProyectosDao;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.dao.DeudasPorProyectoDao;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.dao.ComprasDeLiderDao;


public class ReportesController {

    private ProyectosDao proyectosDao;
    private ProyectoBancoDao proyectoBancoDao;
    private DeudasPorProyectoDao deudasPorProyectoDao;
    private ComprasDeLiderDao comprasDeLiderDao;

    
    public ReportesController() {
        proyectosDao = new ProyectosDao();
        proyectoBancoDao = new ProyectoBancoDao();
        deudasPorProyectoDao = new DeudasPorProyectoDao();
        comprasDeLiderDao = new ComprasDeLiderDao();
    }

    public List<ProyectosVo> listarProyectosExcluyendoClasificaciones(String clasificacion1, String clasificacion2) throws SQLException {
        return proyectosDao.listarProyectos(clasificacion1, clasificacion2);
    }

    public List<ProyectoBancoVo> listarProyectosPorBanco(String banco) throws SQLException {
        return proyectoBancoDao.listarProyectos(banco);
    }

    public List<DeudasPorProyectoVo> listarDeudasPorProyecto(Double limiteInferior) throws SQLException {
        return deudasPorProyectoDao.listarProyectos(limiteInferior);
    }

    public List<ComprasDeLiderVo> listarComprasDeLider() throws SQLException {
        return comprasDeLiderDao.listarProyectos();
    }
}
