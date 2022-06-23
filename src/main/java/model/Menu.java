package model;

import java.util.Date;

public class Menu {
    private int codigoMenu;
    private String nombreMenu;
    private String fkPlatillo;
    private String nombrePlatillo;
    private Date fechaMenu;
    private Double precioMenu = Double.valueOf(0);
    private String estadoMenu;

    public Menu() {
        precioMenu = 0d;
    }

    public int getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(int codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getFkPlatillo() {
        return fkPlatillo;
    }

    public void setFkPlatillo(String fkPlatillo) {
        this.fkPlatillo = fkPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    
    public Double getPrecioMenu() {
        return precioMenu;
    }

    public void setPrecioMenu(Double precioMenu) {
        this.precioMenu = precioMenu;
    }

    public String getEstadoMenu() {
        return estadoMenu;
    }

    public void setEstadoMenu(String estadoMenu) {
        this.estadoMenu = estadoMenu;
    }    

    public Date getFechaMenu() {
        return fechaMenu;
    }

    public void setFechaMenu(Date fechaMenu) {
        this.fechaMenu = fechaMenu;
    }

    
}
