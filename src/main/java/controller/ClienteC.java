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
import java.util.logging.Logger;

@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Persona persona;
    private PersonaImp dao;
    private List<Persona> listadoper;
    private int tipo = 1;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
            logger.log("Exception:" + e.getMessage());
        }
    }

    public ClienteC() {
        persona = new Persona();
        dao = new PersonaImp();
    }

    public void registrar() throws Exception {
        try {
            persona.setTipoPersona("C");
            dao.guardar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            logger.log("Error en registrar ClienteC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            logger.log("Error en modificar ClienteC/modificar" + e.getMessage());
        }
    }

    public void eliminar(Persona persona) throws Exception {
        try {
            dao.eliminar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
        } catch (Exception e) {
            logger.log("Error en eliminar ClienteC/eliminar" + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            listadoper = dao.listarTodos(tipo);
        } catch (Exception e) {
            logger.log("Error en ListarClienteC: " + e.getMessage());
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

    private static class logger {

        private static void log(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public logger() {
        }
    }

 
}
