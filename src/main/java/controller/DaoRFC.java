package controller;
import MySQLConnection.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRFC {


    Connection con;
    PreparedStatement pstm;
    private ResultSet rs;


    String registro_nuevo = "INSERT INTO personas(" +
            "id,Nombre,Apellido1,Apellido2,CURP,Fecha,RFC) VALUE(?,?,?,?,?,?,?)";

    String mostrar = "Select * from personas where RFC=?";

    String mostarRFC = "Select RFC from personas where RFC=?";
    String modificar = "Update personas set Nombre=?,Apellido1=?,Apellido2=?,CURP=?,Fecha=?,RFC=? WHERE RFC=?;";

    String eliminar="Delete from personas where RFC=?";

    public BeanRFC insertar(int id, String nombre, String Apellido1, String Apellido2, String curp, String fecha, String rfc) {
        BeanRFC bean = new BeanRFC();
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(registro_nuevo);
            pstm.setInt(1, id);
            pstm.setString(2, nombre);
            pstm.setString(3, Apellido1);
            pstm.setString(4, Apellido2);
            pstm.setString(5, curp);
            pstm.setString(6, fecha);
            pstm.setString(7, rfc);
            int resultado = pstm.executeUpdate();
            if (resultado == 1) {
                bean.setId(id);
                bean.setNombre(nombre);
                bean.setApellido1(Apellido1);
                bean.setApellido2(Apellido2);
                bean.setCurp(curp);
                bean.setFecha(fecha);
                bean.setRfc(rfc);
            } else
                bean = null;
        } catch (SQLException e) {
            System.out.println("Error al registrar" + e.getMessage());
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones" + e.getMessage());
            }
        }
        return bean;

    }

    public String consultar(String rfc){
        BeanRFC bean =null;
        String result="";
        try{
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(mostrar);
            pstm.setString(1,rfc);
            rs = pstm.executeQuery();
            if (rs.next()){
                bean=new BeanRFC();
                String nom,ap1,ap2,curp,fec,rfc2;
                nom= bean.setNombre(rs.getString("Nombre"));
                ap1=bean.setApellido1(rs.getString("Apellido1"));
                ap2=bean.setApellido2(rs.getString("Apellido2"));
                curp=bean.setCurp(rs.getString("CURP"));
                fec=bean.setFecha(rs.getString("Fecha"));
                rfc2=bean.setRfc(rs.getString("RFC"));
                result="Nombre: "+nom+"\nPrimer Apellido: "+ap1+"\nSegundo Apellido: "+ap2+"\nCURP: "+curp+"\nFecha de nacimiento: "+fec+"\nRFC: "+rfc2;
            }

        }catch (SQLException e){
            System.out.println("Error en consultar "+e.getMessage());
        }finally {
            try{
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones "+e.getMessage());
            }
        }
        return result;
    }

    /*
    public List<BeanRFC> consultar() throws SQLException {
        List lista = new ArrayList();
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(mostrar);
            rs = pstm.executeQuery();
            while (rs.next()) {
                BeanRFC bean = new BeanRFC();
                bean.setId(rs.getInt("id"));
                bean.setNombre(rs.getString("Nombre"));
                bean.setApellido1(rs.getString("Apellido1"));
                bean.setApellido2(rs.getString("Apellido2"));
                bean.setCurp(rs.getString("CURP"));
                bean.setFecha(rs.getString("Fecha"));
                bean.setRfc(rs.getString("RFC"));
                lista.add(bean);
            }
        } catch (SQLException e) {
            System.out.println("Error consulta");
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones" + e.getMessage());
            }
        }
        return lista;
    }

     */

    public boolean modificar(String nombre, String Apellido1, String Apellido2, String curp, String fecha, String rfc,String rfc2) {
        boolean resultado = false;
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(modificar);

            pstm.setString(1, nombre);
            pstm.setString(2, Apellido1);
            pstm.setString(3, Apellido2);
            pstm.setString(4, curp);
            pstm.setString(5, fecha);
            pstm.setString(6,rfc);
            pstm.setString(7,rfc2);
            resultado = pstm.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("Error en modificar " + e.getMessage());
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones");
            }
        }
        return resultado;
    }

    public boolean mostrarRFC(String RFC) {
        boolean resultado = false;
        try {
            con = MySQLConnection.getConnection();
            pstm = con.prepareStatement(mostarRFC);
            pstm.setString(1,RFC);
            rs = pstm.executeQuery();
            while (rs.next()) {
                resultado = true;
            }
        } catch (SQLException e) {
            System.out.printf("Error en consulta " + e.getMessage());
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar " + e.getMessage());
            }
            return resultado;
        }

    }
    public boolean elminar(String rfc){
        boolean resultado=false;
        try{
            con=MySQLConnection.getConnection();
            pstm=con.prepareStatement(eliminar);
            pstm.setString(1,rfc);
            resultado=pstm.executeUpdate()==1;

        }catch (SQLException e){
            System.out.println("Error en eliminar "+e.getMessage());
        }finally {
            try{
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones "+e.getMessage());
            }
        }
        return resultado;
    }

}
