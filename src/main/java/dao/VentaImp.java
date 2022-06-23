package dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Venta;

public class VentaImp extends Conexion implements ICRUD<Venta> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void guardar(Venta venta) throws Exception {
        String sql = "INSERT INTO VENTA (FECVEN, IDEMP, IDCLI, ESTVEN) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, formatter.format(venta.getFechaVenta()));
            ps.setInt(2, venta.getFkEmpleado());
            ps.setInt(3, venta.getFkCliente());
            ps.setString(4, venta.getEstadoVenta());
            ps.executeUpdate();
            ps.close();

        } catch (Exception E) {
            System.out.println("Erroe en VentaImp/guardar" + E.getMessage());
        }
    }

    @Override
    public void modificar(Venta venta) throws Exception {
        String sql = "UPDATE VENTA SET FECVEN =?, IDEMP = ?, IDCLI =?, ESTVEN=? TOTALVEN=? where IDVEN=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, formatter.format(venta.getFechaVenta()));
            ps.setInt(2, venta.getFkEmpleado());
            ps.setInt(3, venta.getFkCliente());
            ps.setString(4, venta.getEstadoVenta());
            ps.setInt(5, venta.getCodigoVenta());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en VentaImp/modificar" + e.getMessage());
        }
    }

    @Override
    public void eliminar(Venta venta) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE VENTA SET ESTVEN='I' WHERE IDVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, venta.getCodigoVenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en VentaImp/eliminar: " + e.getMessage());
        }
    }

    @Override
    public List<Venta> listarTodos() throws Exception {
        List<Venta> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM VENTA";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta ven = new Venta();
                ven.setCodigoVenta(rs.getInt("IDVEN"));
                ven.setFechaVenta(rs.getDate("FECVEN"));
                ven.setFkEmpleado(rs.getInt("IDEMP"));
                ven.setFkCliente(rs.getInt("IDCLI"));
                ven.setEstadoVenta(rs.getString("ESTVEN"));
                listado.add(ven);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en VentaImp/listar" + e.getMessage());
        }
        return listado;
    }

    public void cambiarEstado(Venta venta) throws Exception {
        try {
            String sql = "UPDATE VENTA SET ESTVEN=? WHERE IDVEN=?";
            try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, venta.getEstadoVenta());
                ps.setInt(2, venta.getCodigoVenta());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en VentaImp/cambiarEstado: " + e.getMessage());
        }
    }
}
