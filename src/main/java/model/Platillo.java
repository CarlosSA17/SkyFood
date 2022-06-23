package model;

public class Platillo {

    private int codigoPlatillo;
    private String tipoPlatillo;
    private String nombrePlatillo;
    //private Double precioPlatillo = Double.valueOf(0);
    private String estadoPlatillo;

    //public Platillo() {
       // precioPlatillo = 0d; Para implementarlo en Menu y Venta
    //}

    public int getCodigoPlatillo() {
        return codigoPlatillo;
    }

    public void setCodigoPlatillo(int codigoPlatillo) {
        this.codigoPlatillo = codigoPlatillo;
    }

    public String getTipoPlatillo() {
        return tipoPlatillo;
    }

    public void setTipoPlatillo(String tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public String getEstadoPlatillo() {
        return estadoPlatillo;
    }

    public void setEstadoPlatillo(String estadoPlatillo) {
        this.estadoPlatillo = estadoPlatillo;
    }

    
    
}