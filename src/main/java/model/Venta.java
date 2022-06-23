package model;

import java.util.Date;

public class Venta {

    private int codigoVenta;
    private Date fechaVenta;
    private int fkEmpleado;
    private int fkCliente;
    private String estadoVenta;

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getFkEmpleado() {
        return fkEmpleado;
    }

    public void setFkEmpleado(int fkEmpleado) {
        this.fkEmpleado = fkEmpleado;
    }

    public int getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(int fkCliente) {
        this.fkCliente = fkCliente;
    }

    public String getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
    
}