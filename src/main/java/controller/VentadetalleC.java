package controller;

import dao.VentadetalleImp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Ventadetalle;

@Named(value = "VentadetalleC")
@SessionScoped
public class VentadetalleC implements Serializable {

    private Ventadetalle ventadetalle;
    private VentadetalleImp dao;
    private List<Ventadetalle> listadovendet;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
        }
    }

    public VentadetalleC() {
        ventadetalle = new Ventadetalle();
        dao = new VentadetalleImp();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(ventadetalle);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar VentadetalleC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(ventadetalle);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar VentadetalleC/modificar" + e.getMessage());
        }
    }

    public void eliminar(Ventadetalle ventadetalle) throws Exception {
        try {
            dao.eliminar(ventadetalle);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar VentadetalleC/eliminar" + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            listadovendet = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarVentadetalleC: " + e.getMessage());
        }

    }

    public Ventadetalle getVentadetalle() {
        return ventadetalle;
    }

    public void setVentadetalle(Ventadetalle ventadetalle) {
        this.ventadetalle = ventadetalle;
    }

    public VentadetalleImp getDao() {
        return dao;
    }

    public void setDao(VentadetalleImp dao) {
        this.dao = dao;
    }

    public List<Ventadetalle> getListadovendet() {
        return listadovendet;
    }

    public void setListadovendet(List<Ventadetalle> listadovendet) {
        this.listadovendet = listadovendet;
    }

}
