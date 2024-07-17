package main;
import java.util.List;
import models.Clubes;
import models.ClubesDao;
import models.Grupos;
import models.Partidos;
import models.Sorteo;

public class Principal {
    public static void main(String[] args) {
        ClubesDao clubesDao = new ClubesDao();
        List<Clubes> clubes = clubesDao.obtenerTodosLosClubes();
        
        Sorteo sorteo = new Sorteo();
        sorteo.realizarSorteo(clubes);
        
        for (Grupos grupo : sorteo.getGrupos()) {
            System.out.println("Grupo " + grupo.getNombre());
            System.out.println("-------");
            grupo.mostrarIntegrantes();
            grupo.generarFixture();
            System.out.println();
            
            System.out.println("Fixture ");
            System.out.println("--------");
            grupo.mostrarFixture();
            System.out.println();
             
            System.out.println("Resultados");
            System.out.println("----------");
            for (Partidos partido : grupo.getPartidos()) {
                System.out.println(partido.getClubLocal().getNombre() + " " + partido.getGolesLocal() + " - " +
                                   partido.getGolesVisita() + " " + partido.getClubVisita().getNombre());
            }     
            
            System.out.println();
            grupo.mostrarTablaDePosiciones();
            System.out.println();
            grupo.clasificarClubesGrupo(clubes);
        }
        
        System.out.println("Clasifican a los 8vos de final de la UEFA Champions League:");
        for (Grupos grupo : sorteo.getGrupos()) {
            for (Clubes club : grupo.getClubes()) {
                if (club.isClasificadoUCL()) {
                    System.out.println(club.getNombre());
                }
            }
        }
        
        System.out.println();
        System.out.println("Acceden a los 16vos de final de la UEFA Europa League:");
        for (Grupos grupo : sorteo.getGrupos()) {
            for (Clubes club : grupo.getClubes()) {
                if (club.isClasificadoUEL()) {
                    System.out.println(club.getNombre());
                }
            }
        }
    }
}
