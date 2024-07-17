package models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Grupos {
    private String nombre;
    private ArrayList<Clubes> clubes;
    private List<String> fixture;
    private ArrayList<Partidos> partidos;

    public Grupos(String nombre) {
        this.nombre = nombre;
        this.clubes = new ArrayList<>();
        this.fixture = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }
    
    public void addClub(Clubes club) {
        clubes.add(club);
    }
    
    public boolean estaCompleto() {
        return clubes.size() >= 4;
    }
    
    public List<Clubes> getClubes() {
        return clubes;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean tieneClubDelMismoPais(String pais) {
        for (Clubes club : clubes) {
            if (club.getPais().equals(pais)) {
                return true;
            }
        }
        return false;
    }
    
    public String getNacionalidadRepresentada() {
        if (clubes.isEmpty()) {
            return "";
        }
        return clubes.get(0).getPais();
    }
    
    public boolean tieneClubDelBombo(int bombo) {
        for (Clubes club : clubes) {
            if (club.getBombo() == bombo) {
                return true;
            }
        }
        return false;
    }
    
    public void mostrarIntegrantes() {
        for (Clubes club : clubes) {
            System.out.println(club.getNombre() + " (" + club.getPais() + ")");
        }
    }
    
    public void generarFixture() {
        String[] formulaFechas = {"1 vs 4","3 vs 2", "4 vs 3","2 vs 1", "4 vs 2","3 vs 1", "4 vs 1","2 vs 3", "1 vs 2","3 vs 4", "2 vs 4","1 vs 3"};     
        // reemplazo los nro de bombo por los nombre de los clubes en el fixture
        for (int i = 0; i < formulaFechas.length; i++) {
            String partido = formulaFechas[i];
            String[] clubesPartido = partido.split(" vs ");
            String clubBombo1 = obtenerNombreClubPorBombo(Integer.parseInt(clubesPartido[0]));
            String clubBombo2 = obtenerNombreClubPorBombo(Integer.parseInt(clubesPartido[1]));
            String partidoFormato = "Fecha " + (i / 2 + 1) + ": " + clubBombo1 + " vs " + clubBombo2;
            fixture.add(partidoFormato);
            
            Clubes local = obtenerClubPorNombre(clubBombo1);
            Clubes visita = obtenerClubPorNombre(clubBombo2);
            Partidos nuevoPartido = new Partidos(local, visita);
            this.agregarPartido(nuevoPartido);
        }
    }
    
    private Clubes obtenerClubPorNombre(String nombre) {
        for (Clubes club : clubes) {
            if (club.getNombre().equals(nombre)) {
                return club;
            }
        }
        return null;
    }

    private String obtenerNombreClubPorBombo(int bombo) {
        String nombreClub = "";
        for (Clubes club : clubes) {
            if (club.getBombo() == bombo) {
                nombreClub = club.getNombre();
                break;
            }
        }
        return nombreClub;
    }
    
    public void mostrarFixture() {
        for (String partido : fixture) {
            System.out.println(partido);
        }
    }
    
    public List<Partidos> getPartidos() {
        return partidos;
    }
    
    public void agregarPartido(Partidos partido) {
        partido.simularPartido();
        actualizarTablaDePosiciones(partido);
        this.partidos.add(partido);
    }
    
    private void actualizarTablaDePosiciones(Partidos partido) {
        for (Clubes club : this.clubes) {
            if (club.equals(partido.getClubLocal())) {
                club.setPartidosJugados(club.getPartidosJugados() + 1);
                club.setPartidosGanados(club.getPartidosGanados() + (partido.getGolesLocal() > partido.getGolesVisita() ? 1 : 0));
                club.setPartidosEmpatados(club.getPartidosEmpatados() + (partido.getGolesLocal() == partido.getGolesVisita() ? 1 : 0));
                club.setPartidosPerdidos(club.getPartidosPerdidos() + (partido.getGolesLocal() < partido.getGolesVisita() ? 1 : 0));
                club.setGolesAFavor(club.getGolesAFavor() + partido.getGolesLocal());
                club.setGolesEnContra(club.getGolesEnContra() + partido.getGolesVisita());
                club.setDiferenciaGoles(club.getGolesAFavor() - club.getGolesEnContra());
                club.setPuntos(club.getPartidosGanados() * 3 + club.getPartidosEmpatados());
            } else if (club.equals(partido.getClubVisita())) {
                club.setPartidosJugados(club.getPartidosJugados() + 1);
                club.setPartidosGanados(club.getPartidosGanados() + (partido.getGolesVisita() > partido.getGolesLocal() ? 1 : 0));
                club.setPartidosEmpatados(club.getPartidosEmpatados() + (partido.getGolesVisita() == partido.getGolesLocal() ? 1 : 0));
                club.setPartidosPerdidos(club.getPartidosPerdidos() + (partido.getGolesVisita() < partido.getGolesLocal() ? 1 : 0));
                club.setGolesAFavor(club.getGolesAFavor() + partido.getGolesVisita());
                club.setGolesEnContra(club.getGolesEnContra() + partido.getGolesLocal());
                club.setDiferenciaGoles(club.getGolesAFavor() - club.getGolesEnContra());
                club.setPuntos(club.getPartidosGanados() * 3 + club.getPartidosEmpatados()); 
            }
        }
    }
    
    public void mostrarTablaDePosiciones() {
        Collections.sort(this.clubes, new Comparator<Clubes>() {
            @Override
            public int compare(Clubes club1, Clubes club2) {
                if (club2.getPuntos() != club1.getPuntos()) {
                    return club2.getPuntos() - club1.getPuntos();
                } else if (club2.getDiferenciaGoles() != club1.getDiferenciaGoles()) {
                    return club2.getDiferenciaGoles() - club1.getDiferenciaGoles();
                } else if (club2.getGolesAFavor() != club1.getGolesAFavor()) {
                    return club2.getGolesAFavor() - club1.getGolesAFavor();
                } else {
                    int resultado = 0;
                    for (Partidos partido : partidos) {
                        if ((partido.getClubLocal().equals(club1) && partido.getClubVisita().equals(club2)) ||
                            (partido.getClubLocal().equals(club2) && partido.getClubVisita().equals(club1))) {
                            resultado = Integer.compare(partido.getGolesLocal(), partido.getGolesVisita());
                            break;
                        }   
                    }
                    return resultado;
                }
            }           
        });
        
        System.out.println("Tabla de posiciones - Grupo " + nombre);
        System.out.println();
        System.out.println("|--------|--------------|-------|------|------|------|------|------|------|--------|");
        System.out.println("|  Pos.  |    Club     |  Pts  |  PJ  |  PG  |  PE  |  PP  |  GF  |  GC  |  Dif.  |");
        System.out.println("|--------|--------------|-------|------|------|------|------|------|------|--------|");
           
        int posicion = 1;
        for (Clubes club : clubes) {
            System.out.printf("| %-5d | %-18s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d |\n", posicion, club.getNombre(),
            club.getPuntos(), club.getPartidosJugados(), club.getPartidosGanados(), club.getPartidosEmpatados(), club.getPartidosPerdidos(), 
            club.getGolesAFavor(), club.getGolesEnContra(), club.getDiferenciaGoles());
            posicion++;
        }       
        System.out.println("|------|--------------|-----|----|----|----|----|----|----|------|");
    }
    
    public void clasificarClubesGrupo(List<Clubes> clubes) {
        Collections.sort(this.getClubes(), (club1, club2) -> club2.getPuntos() - club1.getPuntos());
        
        for (int i = 0; i < 2; i++) {
            Clubes club = this.getClubes().get(i);
            club.setClasificadosUCL(true);
        }
        
        if (this.getClubes().size() >= 3) {
            Clubes club = this.getClubes().get(2);
            club.setClasificadosUEL(true);
        }
    } 
}