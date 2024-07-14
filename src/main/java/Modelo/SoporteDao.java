package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoporteDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List ListarSoporte() {
        List<Soporte> ListarSop = new ArrayList();
        String sql = "SELECT * FROM soporte";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Soporte sop = new Soporte();
                sop.setId(rs.getInt("id"));
                sop.setNombre(rs.getString("nombre"));
                sop.setCorreo(rs.getString("correo"));
                sop.setMensaje(rs.getString("mensaje"));
                sop.setFecha(rs.getTimestamp("fecha"));
                sop.setEstado(rs.getString("estado"));
                ListarSop.add(sop);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListarSop;
    }

    public Soporte obtenerSoporte(int id) {
        String sql = "SELECT * FROM soporte WHERE id = ?";
        Soporte soporte = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                soporte = new Soporte();
                soporte.setId(rs.getInt("id"));
                soporte.setNombre(rs.getString("nombre"));
                soporte.setCorreo(rs.getString("correo"));
                soporte.setMensaje(rs.getString("mensaje"));
                soporte.setFecha(rs.getTimestamp("fecha"));
                soporte.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return soporte;
    }

   public boolean actualizarEstado (int id_pedido){
        String sql = "UPDATE soporte SET estado = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "FINALIZADO");
            ps.setInt(2, id_pedido);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
   
   
   public Soporte verSoporte(int id_soporte) {
    Soporte sop = new Soporte();
    String sql = "SELECT * FROM soporte WHERE id = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id_soporte);
        rs = ps.executeQuery();
        if (rs.next()) {
            sop.setId(rs.getInt("id"));
            sop.setNombre(rs.getString("nombre"));
            sop.setCorreo(rs.getString("correo"));
            sop.setMensaje(rs.getString("mensaje"));
            sop.setFecha(rs.getTimestamp("fecha"));
            sop.setEstado(rs.getString("estado"));
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return sop;
}
   /*
       public Pedidos verSoporte(int id_soporte){
        Pedidos ped = new Pedidos();
       String sql = "SELECT p.*, s.nombre FROM pedidos p INNER JOIN salas s ON p.id_sala = s.id WHERE p.id = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setInt(1, id_pedido);
           rs = ps.executeQuery();
            if (rs.next()) {               
               
               ped.setId(rs.getInt("id"));
               ped.setFecha(rs.getString("fecha"));
               ped.setSala(rs.getString("nombre"));
               ped.setNum_mesa(rs.getInt("num_mesa"));
               ped.setTotal(rs.getDouble("total"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ped;
   }
   */
   
}
