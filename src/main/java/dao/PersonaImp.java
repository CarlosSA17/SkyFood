package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Persona;

public class PersonaImp extends Conexion implements ICRUD<Persona> {

    @Override
    public void guardar(Persona persona) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO PERSONA"
                    + " (TIPPER,NOMCOMPER,DNIPER,CELPER,TURPER,ESTPER)"
                    + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getTipoPersona());
            ps.setString(2, persona.getNombrePersona());
            ps.setString(3, persona.getDniPersona());
            ps.setString(4, persona.getCelularPersona());
            ps.setString(5, persona.getTurnoPersona());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en PersonaImpl/guardar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        this.conectar();
        String sql = "UPDATE PERSONA SET NOMCOMPER=?,DNIPER=?,CELPER=?,ESTPER=? where IDPER=?";
        try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
            ps.setString(1, persona.getNombrePersona());
            ps.setString(2, persona.getDniPersona());
            ps.setString(3, persona.getCelularPersona());
            ps.setString(4, "A");
            ps.setInt(5, persona.getCodigoPersona());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PersonaImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Persona persona) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PERSONA SET ESTPER='I' WHERE IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, persona.getCodigoPersona());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en PersonaImpl/eliminar: " + e.getMessage());
        }
    }

    //@Override
    public List<Persona> listarTodos(int tipo) {
        this.conectar();
        List<Persona> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = 'C' ORDER BY IDPER desc";
                break;
            case 2:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'I' AND TIPPER = 'C' ORDER BY IDPER desc";
                break;
            case 3:
                sql = "SELECT * FROM PERSONA WHERE TIPPER = 'C' ORDER BY IDPER desc";
                break;
            case 4:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = 'E' ORDER BY IDPER desc";
                break;
            case 5:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'I' AND TIPPER = 'E' ORDER BY IDPER desc";
                break;
            case 6:
                sql = "SELECT * FROM PERSONA WHERE TIPPER = 'E' ORDER BY IDPER desc";
                break;
        }
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona per = new Persona();
                per.setCodigoPersona(rs.getInt("IDPER"));
                per.setNombrePersona(rs.getString("NOMCOMPER"));
                per.setDniPersona(rs.getString("DNIPER"));
                per.setCelularPersona(rs.getString("CELPER"));
                per.setTurnoPersona(rs.getString("TURPER"));
                per.setEstadoPersona(rs.getString("ESTPER"));
                listado.add(per);
            }
        } catch (SQLException e) {
            System.out.println("Error en PersonaImpl/Listar: " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return listado;
    }

    public void cambiarEstado(Persona persona) throws Exception {
        try {
            String sql = "UPDATE PERSONA SET ESTPER=? WHERE IDPER=?";
            try (PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, persona.getEstadoPersona());
                ps.setInt(2, persona.getCodigoPersona());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en PersonaImp/cambiarEstado: " + e.getMessage());
        }
    }

    @Override
    public List<Persona> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
