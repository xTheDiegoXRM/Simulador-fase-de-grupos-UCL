package models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sorteo {
    private List<Clubes> clubes;
    private List<Grupos> grupos;
    
    public Sorteo() {
        this.clubes = new ArrayList<>();
        this.grupos = new ArrayList<>();
        for (char grupo = 'A'; grupo <= 'H'; grupo++) {
            grupos.add(new Grupos(String.valueOf(grupo)));
        }
    }

    public void realizarSorteo(List<Clubes> clubes) {
        this.clubes = clubes;
        List<Clubes> bombo1 = new ArrayList<>();
        List<Clubes> bombo2 = new ArrayList<>();
        List<Clubes> bombo3 = new ArrayList<>();
        List<Clubes> bombo4 = new ArrayList<>();        
        for (Clubes club : clubes) {
            int bombo = club.getBombo();
            switch (bombo) {
                case 1:
                    bombo1.add(club);
                    break;
                case 2:
                    bombo2.add(club);
                    break;
                case 3:
                    bombo3.add(club);
                    break;
                case 4:
                    bombo4.add(club);
                    break;
            }
        }       
        asignarCabezasDeSerie(bombo1);
        asignarClubesCondicionados(bombo2);
        asignarClubesCondicionados(bombo3);
        asignarClubesAleatorios(bombo4);
        simularPartidosFaseDegrupos();
    }

    private void asignarCabezasDeSerie(List<Clubes> bombo) {
        Collections.shuffle(bombo);
        int i = 0;
        for (Grupos grupo : grupos) {
            if (i < bombo.size()) {
                grupo.addClub(bombo.get(i));
                i++;
            }
        }
    }
    
    private void asignarClubesCondicionados(List<Clubes> bombo) {
        Collections.shuffle(bombo);
        for (Clubes club : bombo) {
            boolean asignado = false;
            while (!asignado) {
                Grupos grupo = grupos.get(new Random().nextInt(grupos.size()));
                if (!grupo.tieneClubDelMismoPais(club.getPais()) && grupo.getClubes().size() < 4 && !grupo.tieneClubDelBombo(club.getBombo())) {
                    grupo.addClub(club);
                    asignado = true;
                }
            }
        }
    }
    
    private void asignarClubesAleatorios(List<Clubes> bombo) {
        Collections.shuffle(bombo);
        for (Clubes club : bombo) {
            boolean asignado = false;
            while (!asignado) {
                Grupos grupo = grupos.get(new Random().nextInt(grupos.size()));
                if (grupo.getClubes().size() < 4 && !grupo.tieneClubDelBombo(club.getBombo())) {
                    grupo.addClub(club);
                    asignado = true;
                }
            }
        }
    }   

    public void mostrarSorteo() {
        for (Grupos grupo : grupos) {
            System.out.println("Grupo " + grupo.getNombre() + ":");
            grupo.mostrarIntegrantes();
            grupo.generarFixture();
            grupo.mostrarFixture();
        }
    }    

    private void simularPartidosFaseDegrupos() {
        for (Grupos grupo : grupos) {
            for (Partidos partido : grupo.getPartidos()) {
                partido.simularPartido();
            }
        }
    }
   
    public List<Grupos> getGrupos() {
        return this.grupos;
    }
}