package controller;

import dao.MenuImp;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Menu;

@Named(value = "MenuC")
@SessionScoped
public class MenuC implements Serializable {

    private Menu menu;
    private MenuImp dao;
    private List<Menu> listadomen;
    private int tipo = 1;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
            System.out.println("controller.MenuC.init()");
        }
    }

    public MenuC() {
        menu = new Menu();
        dao = new MenuImp();
    }

    public void registrar() throws Exception {
        try {
            menu.setFkPlatillo(dao.obtenerIDPlatillo(menu.getFkPlatillo()));
            dao.guardar(menu);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar PlatilloC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(menu);
            menu.setFkPlatillo(dao.obtenerIDPlatillo(menu.getFkPlatillo()));
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar PlatilloC/modificar" + e.getMessage());
        }
    }

    public void eliminar(Menu menu) throws Exception {
        try {
            dao.eliminar(menu);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar PlatilloC/eliminar" + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            listadomen = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en ListarPlatilloC: " + e.getMessage());
        }

    }

    public List<String> completeTextPlatillo(String query) throws Exception {
        MenuImp menudao = new MenuImp();
        return menudao.autoCompletePlatillo(query);
    }
    
    public void limpiar() {
        menu = new Menu();
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public MenuImp getDao() {
        return dao;
    }

    public void setDao(MenuImp dao) {
        this.dao = dao;
    }

    public List<Menu> getListadomen() {
        return listadomen;
    }

    public void setListadomen(List<Menu> listadomen) {
        this.listadomen = listadomen;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    

}
