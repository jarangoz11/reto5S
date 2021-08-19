package co.edu.utp.misiontic2022.c2.jarangoz.reto5s.view;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ProyectosVo;
import co.edu.utp.misiontic2022.c2.jarangoz.reto5s.model.vo.ComprasDeLiderVo;

public class ReportesView {
    private ReportesController controller;

    public ReportesView() {
        controller = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        var respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void imprimirProyectosSinCasaCampestreNiCondominio() {
        try {
            System.out.println(repitaCaracter('=', 5) + " LISTADO DE PROYECTOS SIN CASAS CAMPESTRES NI CONDOMINIOS "
                    + repitaCaracter('=', 5));
            System.out.println(String.format("%-20s %-15s %-7s %-10s %-10s", "CIUDAD", "CLASIFICACION", "TOTAL",
                    "VIEJO", "RECIENTE"));
            System.out.println(repitaCaracter('-', 68));

            var lista = controller.listarProyectosExcluyendoClasificaciones("Casa Campestre", "Condominio");
            for (ProyectosVo proyecto : lista) {
                System.out.printf("%-20s %-15s %7d %-10s %-10s %n", proyecto.getCiudad(), proyecto.getClasificacion(),
                        proyecto.getTotal(), proyecto.getViejo(), proyecto.getReciente());
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void proyectosFinanciadosPorBanco(String banco) {
        try {
            if (banco != null && !banco.isBlank()) {
                System.out.println(
                        repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO " + repitaCaracter('=', 37));
                System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", "ID", "CONSTRUCTORA", "CIUDAD",
                        "CLASIFICACION", "ESTRATO", "LIDER"));
                System.out.println(repitaCaracter('-', 105));

                var lista = controller.listarProyectosPorBanco(banco);
                for (ProyectoBancoVo proyecto : lista) {
                    System.out.printf("%3d %-25s %-20s %-15s %7d %-30s %n", proyecto.getId(),
                            proyecto.getConstructora(), proyecto.getCiudad(), proyecto.getClasificacion(),
                            proyecto.getEstrato(), proyecto.getLider());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }
    

    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO " + repitaCaracter('=', 1));
        System.out.println(String.format("%3s %15s", "ID", "VALOR "));
        System.out.println(repitaCaracter('-', 29));
        try {
            if (limiteInferior != null) {              

                var lista = controller.listarDeudasPorProyecto(limiteInferior);
                for (DeudasPorProyectoVo proyecto : lista) {
                    System.out.printf("%3d %,15.1f %n", proyecto.getId(), proyecto.getValor());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void lideresQueMasGastan() {
        System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES " + repitaCaracter('=', 7));
        System.out.println(String.format("%-25s %15s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));
        try{
            var lista = controller.listarComprasDeLider();
                    for (ComprasDeLiderVo proyecto : lista) {
                        System.out.printf("%-25s %,15.1f %n", proyecto.getLider(), proyecto.getValor());
                }
            }catch(SQLException e) {
                System.err.println("Error: " + e);
                e.printStackTrace();
            }
    }
}
