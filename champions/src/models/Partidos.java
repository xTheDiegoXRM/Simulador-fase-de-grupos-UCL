package models;
import java.util.Random;

public class Partidos {
    private Clubes clubLocal;
    private Clubes clubVisita;
    private int golesLocal;
    private int golesVisita;

    public Partidos(Clubes clubLocal, Clubes clubVisita) {
        this.clubLocal = clubLocal;
        this.clubVisita = clubVisita;
        this.golesLocal = 0;
        this.golesVisita = 0;
        simularPartido();
    }

    public Clubes getClubLocal() {
        return clubLocal;
    }

    public Clubes getClubVisita() {
        return clubVisita;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisita() {
        return golesVisita;
    }

    public void simularPartido() {
        Random random = new Random();
        this.golesLocal = random.nextInt(4);       
        this.golesVisita = random.nextInt(4);   
    }   
    
    public void setGoles(int golesLocal, int golesVisita) {
        this.golesLocal = golesLocal;
        this.golesLocal = golesVisita;
    }
    
    public String getResultado() {
        return this.clubLocal.getNombre() + this.golesLocal + " vs " + this.golesVisita + this.clubVisita.getNombre();
    }
    
    @Override
    public String toString() {
        return this.clubLocal.getNombre() +  " vs " + this.clubVisita.getNombre();
    }
}