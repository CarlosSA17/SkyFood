package controller;

import dao.PlatilloImp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Platillo;

@Named(value = "PlatilloC")
@SessionScoped
public class PlatilloC implements Serializable {

    private Platillo platillo;
    private PlatilloImp dao;
    private List<Platillo> listadopla;
    private int tipo = 1;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
            System.out.println("controller.PlatilloC.init()");
        }
    }

    public PlatilloC() {
        platillo = new Platillo();
        dao = new PlatilloImp();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(platillo);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar PlatilloC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(platillo);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar PlatilloC/modificar" + e.getMessage());
        }
    }

    public void eliminar(Platillo platillo) throws Exception {
        try {
            dao.eliminar(platillo);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar PlatilloC/eliminar" + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            listadopla = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en ListarPlatilloC: " + e.getMessage());
        }

    }
    
    public void limpiar() {
        platillo = new Platillo();
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    public PlatilloImp getDao() {
        return dao;
    }

    public void setDao(PlatilloImp dao) {
        this.dao = dao;
    }

    public List<Platillo> getListadopla() {
        return listadopla;
    }

    public void setListadopla(List<Platillo> listadopla) {
        this.listadopla = listadopla;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    
}
