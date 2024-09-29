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
    public List<calcTO> listar(){
        List<calcTO>listc=new ArrayList<>();
        try {
            ps=conn.prepareStatement("SELECT * FROM calculadora");
            rs=ps.executeQuery();
            while(rs.next()){
                calcTO calcto=new calcTO();
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
    public void insertar(calcTO calto){
        try {
         ps=conn.prepareStatement("INSERT into calculadora (num1,num2,operador,resultado) values(?,?,?,?);");
         ps.setString(1,calto.getNum1());
         ps.setString(2,calto.getNum2());
         ps.setString(3,String.valueOf(calto.getOperador()));
         ps.setString(4,calto.getResultado());
         ps.executeUpdate();//este retorna nada

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
