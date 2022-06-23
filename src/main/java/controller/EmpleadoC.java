package controller;

import dao.PersonaImp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Persona;

@Named(value = "EmpleadoC")
@SessionScoped
public class EmpleadoC implements Serializable {

    private Persona persona;
    private PersonaImp dao;
    private List<Persona> listadoper;
    private int tipo = 4;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
        }
    }

    public EmpleadoC() {
        persona = new Persona();
        dao = new PersonaImp();
    }

    public void registrar() throws Exception {
        try {
            persona.setTipoPersona("E");
            dao.guardar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar PersonaC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar PersonaC/modificar" + e.getMessage());
        }
    }

    public void eliminar(Persona persona) throws Exception {
        try {
            dao.eliminar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar PersonaC/eliminar" + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            listadoper = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en ListarPersonaC: " + e.getMessage());
        }

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaImp getDao() {
        return dao;
    }

    public void setDao(PersonaImp dao) {
        this.dao = dao;
    }

    public List<Persona> getListadoper() {
        return listadoper;
    }

    public void setListadoper(List<Persona> listadoper) {
        this.listadoper = listadoper;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
