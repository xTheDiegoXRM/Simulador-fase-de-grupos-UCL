package models;

public class Clubes {
    private int id;
    private String nombre;
    private String pais;
    private int bombo;       
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
    private int diferenciaGoles;
    private boolean clasificadosUCL;
    private boolean clasificadosUEL;
    
    public Clubes() {
    }

    public Clubes(String nombre, String pais, int bombo, int puntos, int partidosJugados, int partidosGanados, int partidosEmpatados, int partidosPerdidos, int golesAFavor, int golesEnContra, int diferenciaGoles) {
        this.nombre = nombre;
        this.pais = pais;
        this.bombo = bombo;
        this.puntos = 0;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
        this.diferenciaGoles = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getBombo() {
        return bombo;
    }

    public void setBombo(int bombo) {
        this.bombo = bombo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public boolean isClasificadoUCL() {
        return clasificadosUCL;
    }

    public void setClasificadosUCL(boolean clasificadosUCL) {
        this.clasificadosUCL = clasificadosUCL;
    }
    
    public boolean isClasificadoUEL() {
        return clasificadosUEL;
    }

    public void setClasificadosUEL(boolean clasificadosUEL) {
        this.clasificadosUEL = clasificadosUEL;
    } 
    
    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Clubes other = (Clubes) obj;
        if (nombre == null) {
        if (other.nombre != null)
            return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;    
    }
}