package controller;

import dao.VentaImp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Venta;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private Venta venta;
    private VentaImp dao;
    private List<Venta> listadoven;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
        }
    }

    public VentaC() {
        venta = new Venta();
        dao = new VentaImp();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar VentaC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar VentaC/modificar" + e.getMessage());
        }
    }

    public void eliminar(Venta venta) throws Exception {
        try {
            dao.eliminar(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar VentaC/eliminar" + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            listadoven = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarVentaC: " + e.getMessage());
        }

    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public VentaImp getDao() {
        return dao;
    }

    public void setDao(VentaImp dao) {
        this.dao = dao;
    }

    public List<Venta> getListadoven() {
        return listadoven;
    }

    public void setListadoven(List<Venta> listadoven) {
        this.listadoven = listadoven;
    }

}
