package dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Ventadetalle;

public class VentadetalleImp extends Conexion implements ICRUD<Ventadetalle> {

    @Override
    public void guardar(Ventadetalle ventadetalle) throws Exception {
        String sql = "INSERT INTO VENTA_DETALLE (IDMEN, IDVEN, CANTVEND, PRECVENDET, ESTVENDET) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, ventadetalle.getFkMenu());
            ps.setInt(2, ventadetalle.getFkVenta());
            ps.setInt(3, ventadetalle.getCantidadVentaDetalle());
            ps.setDouble(4, ventadetalle.getPrecioVentaDetalle());
            ps.setString(5, ventadetalle.getEstadoVentaDetalle());
            ps.executeUpdate();
            ps.close();

        } catch (Exception E) {
            System.out.println("Erroe en VentadetalleImp/guardar" + E.getMessage());
        }
    }

    @Override
    public void modificar(Ventadetalle ventadetalle) throws Exception {
        String sql = "UPDATE VENTA_DETALLE SET IDMEN =?, IDVEN = ?, CANTVEND =?, PRECVENDET=? ESTVENDET=? where IDVENDET=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, ventadetalle.getFkMenu());
            ps.setInt(2, ventadetalle.getFkVenta());
            ps.setInt(3, ventadetalle.getCantidadVentaDetalle());
            ps.setDouble(4, ventadetalle.getPrecioVentaDetalle());
            ps.setInt(5, ventadetalle.getCodigoVentaDetalle());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en VentadetalleImp/modificar" + e.getMessage());
        }
    }

    @Override
    public void eliminar(Ventadetalle ventadetalle) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE VENTA_DETALLE SET ESTVENDET='I' WHERE IDVENDET=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, ventadetalle.getCodigoVentaDetalle());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en VentadetalleImp/eliminar: " + e.getMessage());
        }
    }

    @Override
    public List<Ventadetalle> listarTodos() throws Exception {
        List<Ventadetalle> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM VENTA_DETALLE";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventadetalle vendet = new Ventadetalle();
                vendet.setFkMenu(rs.getInt("IDMEN"));
                vendet.setFkVenta(rs.getInt("IDVEN"));
                vendet.setCantidadVentaDetalle(rs.getInt("CANTVEND"));
                vendet.setPrecioVentaDetalle(rs.getDouble("PRECVENDET"));
                vendet.setEstadoVentaDetalle(rs.getString("ESTVENDET"));
                listado.add(vendet);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en VentaImp/listar" + e.getMessage());
        }
        return listado;
    }

    public void cambiarEstado(Ventadetalle ventadetalle) throws Exception {
        try {
            String sql = "UPDATE VENTA_DETALLE SET ESTVENDET=? WHERE IDVENDET=?";
            try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, ventadetalle.getEstadoVentaDetalle());
                ps.setInt(2, ventadetalle.getCodigoVentaDetalle());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en VentadetalleImp/cambiarEstado: " + e.getMessage());
        }
    }
}
