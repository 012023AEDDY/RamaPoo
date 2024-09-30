package pe.edu.upeu.calcfx.dao;

import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.conexion.ConnDB;
import pe.edu.upeu.calcfx.modelo.calcTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Component
public class CalculadoraDao {
    PreparedStatement ps;
    ResultSet rs;
    ConnDB con;
    Connection conn;
    CalculadoraDao(){//constructor lleva el mismo nombre de la clase
        con =new ConnDB();
        conn= con.getConn();
    }
    public int idGenerate(){
        int idx=0;
        try {
            ps=conn.prepareStatement("SELECT (MAX (c.id)+1)as id FROM calculadora c;");
            rs= ps.executeQuery();
            if(rs.next()){
                idx=rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return idx;
    }
    public List<calcTO> listar(){
        List<calcTO>listc=new ArrayList<>();
        try {
            ps=conn.prepareStatement("SELECT * FROM calculadora");
            rs=ps.executeQuery();
            while(rs.next()){
                calcTO calcto=new calcTO();
                calcto.setId(rs.getInt("id"));
                calcto.setNum1(rs.getString("num1"));
                calcto.setNum2(rs.getString("num2"));
                calcto.setOperador(rs.getString("operador").charAt(0));
                calcto.setResultado(rs.getString("resultado"));
                listc.add(calcto);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
     return listc;
    }
    public void insertar(calcTO calcTO){
        int idx=idGenerate();
        System.out.println(idx);
        int i=0;
        conn= con.getConn();
        try {
            ps=conn.prepareStatement("INSERT into calculadora(id, num1, num2, operador, resultado) values(?,?, ?, ?, ?);  ");
            ps.setInt(++i, idx);
            ps.setString(++i, calcTO.getNum1());
            ps.setString(++i, calcTO.getNum2());
            ps.setString(++i, String.valueOf(calcTO.getOperador()));
            ps.setString(++i, calcTO.getResultado());
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void actualizar(calcTO eddy,int id){
        int i=0;
        try {
            ps=conn.prepareStatement("UPDATE  calculadora SET num1 =?, num2 =?, operador =?, resultado =? WHERE id =?");
            ps.setString(++i, eddy.getNum1());
            ps.setString(++i, eddy.getNum2());
            ps.setString(++i, String.valueOf(eddy.getOperador()));
            ps.setString(++i, eddy.getResultado());
            ps.setInt(++i, id);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void eliminar(int id){
        try {
            ps=conn.prepareStatement("DELETE FROM calculadora WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
