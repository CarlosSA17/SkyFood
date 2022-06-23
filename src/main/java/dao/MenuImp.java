package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Menu;

public class MenuImp extends Conexion implements ICRUD<Menu> {

    DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void guardar(Menu menu) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO MENU"
                    + " (NOMMEN, IDPLA, FECMEN, PRECMEN, ESTMEN)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, menu.getNombreMenu());
            ps.setString(2, menu.getFkPlatillo());
            ps.setString(3, fecha.format(menu.getFechaMenu()));
            ps.setDouble(4, menu.getPrecioMenu());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en MenuImpl/guardar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Menu menu) throws Exception {
        this.conectar();
        String sql = "UPDATE MENU SET NOMMEN=?,IDPLA=?,FECMEN=?,PRECMEN=?,ESTMEN=? where IDMEN=?";
        try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
            ps.setString(1, menu.getNombreMenu());
            ps.setString(2, menu.getFkPlatillo());
            ps.setString(3, fecha.format(menu.getFechaMenu()));
            ps.setDouble(4, menu.getPrecioMenu());
            ps.setString(5, "A");
            ps.setInt(6, menu.getCodigoMenu());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en MenuImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Menu menu) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE MENU SET ESTMEN='I' WHERE IDMEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, menu.getCodigoMenu());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en MenuImp/eliminar: " + e.getMessage());
        }
    }

    public List<String> autoCompletePlatillo(String consulta) throws Exception {
        this.conectar();
        List<String> lista = new ArrayList<>();
        String sql = "SELECT top 5 NOMPLA FROM PLATILLO WHERE NOMPLA LIKE ?";
        try {
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("NOMPLA"));
            }
        } catch (Exception e) {
            System.out.println("Error en MenuImp/autoCompleteProductoDao" + e.getMessage());
        }
        return lista;
    }

    public String obtenerIDPlatillo(String cadenaPla) throws SQLException, Exception {
        this.conectar();
        String sql = "SELECT IDPLA FROM PLATILLO WHERE NOMPLA =?";
        try {
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, cadenaPla);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDPLA");
            }
            return rs.getString("IDPLA");
        } catch (Exception e) {
            System.out.println("Error en obtenerIdEntrada " + e.getMessage());
            throw e;
        }
    }

    public List<Menu> listarTodos(int tipo) throws Exception {
        this.conectar();
        List<Menu> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM V_MENU_PLATILLO WHERE ESTMEN = 'A' ORDER BY IDMEN desc";
                break;
            case 2:
                sql = "SELECT * FROM V_MENU_PLATILLO WHERE ESTMEN = 'I' ORDER BY IDMEN desc";
                break;
            case 3:
                sql = "SELECT * FROM V_MENU_PLATILLO ORDER BY IDMEN desc";
                break;
        }
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu men = new Menu();
                men.setCodigoMenu(rs.getInt("IDMEN"));
                men.setNombreMenu(rs.getString("NOMMEN"));
                men.setFkPlatillo(rs.getString("IDPLA"));
                men.setNombrePlatillo(rs.getString("NOMPLA"));
                men.setPrecioMenu(rs.getDouble("PRECMEN"));
                men.setEstadoMenu(rs.getString("ESTMEN"));
                listado.add(men);
            }
        } catch (SQLException e) {
            System.out.println("Error en MenuImpl/Listar: " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return listado;
    }

    public void cambiarEstado(Menu menu) throws Exception {
        try {
            String sql = "UPDATE MENU SET ESTMEN=? WHERE IDMEN=?";
            try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, menu.getEstadoMenu());
                ps.setInt(2, menu.getCodigoMenu());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en MenuImpl/cambiarEstado: " + e.getMessage());
        }
    }

    @Override
    public List<Menu> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
