package Datos;


public class Dgastos {
    
    private long cod_gastos;
    private String descripcion;
    private long valor;
    private String obs;

    public Dgastos() {
    }

    public Dgastos(long cod_gastos, String descripcion, long valor, String obs) {
        this.cod_gastos = cod_gastos;
        this.descripcion = descripcion;
        this.valor = valor;
        this.obs = obs;
    }

    public long getCod_gastos() {
        return cod_gastos;
    }

    public void setCod_gastos(long cod_gastos) {
        this.cod_gastos = cod_gastos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
    
    
}
