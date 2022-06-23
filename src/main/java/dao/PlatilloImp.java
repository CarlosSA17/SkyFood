package dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Platillo;

public class PlatilloImp extends Conexion implements ICRUD<Platillo> {

    @Override
    public void guardar(Platillo platillo) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO PLATILLO"
                    + " (TIPPLA, NOMPLA, ESTPLA)"
                    + " values (?, ?, ?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, platillo.getTipoPlatillo());
            ps.setString(2, platillo.getNombrePlatillo());
            ps.setString(3, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en PlatilloImpl/guardar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Platillo platillo) throws Exception {
        this.conectar();
        String sql = "UPDATE PLATILLO SET TIPPLA=?,NOMPLA=?,ESTPLA=? where IDPLA=?";
        try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
            ps.setString(1, platillo.getTipoPlatillo());
            ps.setString(2, platillo.getNombrePlatillo());
            ps.setString(3, "A");
            ps.setInt(4, platillo.getCodigoPlatillo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PlatilloImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Platillo platillo) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PLATILLO SET ESTPLA='I' WHERE IDPLA=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, platillo.getCodigoPlatillo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en PlatilloImp/eliminar: " + e.getMessage());
        }
    }

    //@Override
    public List<Platillo> listarTodos(int tipo) {
        this.conectar();
        List<Platillo> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM PLATILLO WHERE ESTPLA = 'A' ORDER BY IDPLA desc";
                break;
            case 2:
                sql = "SELECT * FROM PLATILLO WHERE ESTPLA = 'I' ORDER BY IDPLA desc";
                break;
            case 3:
                sql = "SELECT * FROM PLATILLO ORDER BY IDPLA desc";
                break;
        }
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Platillo pla = new Platillo();
                pla.setCodigoPlatillo(rs.getInt("IDPLA"));
                pla.setTipoPlatillo(rs.getString("TIPPLA"));
                pla.setNombrePlatillo(rs.getString("NOMPLA"));
                pla.setEstadoPlatillo(rs.getString("ESTPLA"));
                listado.add(pla);
            }
        } catch (SQLException e) {
            System.out.println("Error en PlatilloImpl/Listar: " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return listado;
    }

    public void cambiarEstado(Platillo platillo) throws Exception {
        try {
            String sql = "UPDATE PLATILLO SET ESTPLA=? WHERE IDPLA=?";
            try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, platillo.getEstadoPlatillo());
                ps.setInt(2, platillo.getCodigoPlatillo());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en PlatilloImp/cambiarEstado: " + e.getMessage());
        }
    }

    @Override
    public List<Platillo> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
