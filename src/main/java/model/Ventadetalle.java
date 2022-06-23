package model;

public class Ventadetalle {

    private int codigoVentaDetalle;
    private int fkMenu;
    private int fkVenta;
    private int cantidadVentaDetalle;
    private double precioVentaDetalle; 
    private String estadoVentaDetalle;

    public int getCodigoVentaDetalle() {
        return codigoVentaDetalle;
    }

    public void setCodigoVentaDetalle(int codigoVentaDetalle) {
        this.codigoVentaDetalle = codigoVentaDetalle;
    }

    public int getFkMenu() {
        return fkMenu;
    }

    public void setFkMenu(int fkMenu) {
        this.fkMenu = fkMenu;
    }

    public int getFkVenta() {
        return fkVenta;
    }

    public void setFkVenta(int fkVenta) {
        this.fkVenta = fkVenta;
    }

    public int getCantidadVentaDetalle() {
        return cantidadVentaDetalle;
    }

    public void setCantidadVentaDetalle(int cantidadVentaDetalle) {
        this.cantidadVentaDetalle = cantidadVentaDetalle;
    }

    public double getPrecioVentaDetalle() {
        return precioVentaDetalle;
    }

    public void setPrecioVentaDetalle(double precioVentaDetalle) {
        this.precioVentaDetalle = precioVentaDetalle;
    }

    public String getEstadoVentaDetalle() {
        return estadoVentaDetalle;
    }

    public void setEstadoVentaDetalle(String estadoVentaDetalle) {
        this.estadoVentaDetalle = estadoVentaDetalle;
    }

    
}
