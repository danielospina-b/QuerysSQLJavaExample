/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daniel.queryssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class QuerySQL {
    private static Connection con;
    public static void main(String args[]) {
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="bdprueba";
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
            
            query2();
            con.commit();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuerySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void printRes(ResultSet res) throws SQLException {
        ResultSetMetaData md = res.getMetaData();
        String format = "%1$20s";
        int columns = md.getColumnCount();
        while (!res.isLast()) {
            res.next();
            for (int x = 1; x <= columns; x++) {
                System.out.printf(format, res.getString(x));
            }
            System.out.println("");
        }
    }
    
    public static void query1() throws SQLException{
        PreparedStatement query;
        String queryString = getQuery1();
        query = con.prepareStatement(queryString);
        ResultSet res = query.executeQuery();
        printRes(res);
    }
    
    public static void query2() throws SQLException{
        PreparedStatement query;
        String queryString = getQuery2();
        query = con.prepareStatement(queryString);
        int res = query.executeUpdate();
        System.out.println(res);
    }
    
    public static void query3() throws SQLException{
        PreparedStatement query;
        String queryString = getQuery3();
        query = con.prepareStatement(queryString);
        ResultSet res = query.executeQuery();
        printRes(res);
    }
    
    public static void query4() throws SQLException{
        PreparedStatement query;
        String queryString = getQuery4();
        query = con.prepareStatement(queryString);
        ResultSet res = query.executeQuery();
        printRes(res);
    }
    private static String getQuery1() {
        return "select\n" +
"        c.nombre,\n" +
"        c.documento,\n" +
"        c.telefono,\n" +
"        c.direccion,\n" +
"        c.email,\n" +
"        c.vetado,\n" +
"\n" +
"        ir.id as ir_id,\n" +
"        ir.CLIENTES_documento as ir_CLIENTES_documento,\n" +
"        ir.fechainiciorenta as ir_fechainiciorenta,\n" +
"        ir.fechafinrenta as ir_fechafinrenta,\n" +
"\n" +
"        i.id as ir_i_id,\n" +
"        i.nombre as ir_i_nombre,\n" +
"        i.descripcion as ir_i_descripcion,\n" +
"        i.fechalanzamiento as ir_i_fechalanzamiento,\n" +
"        i.tarifaxdia as ir_i_tarifaxdia,\n" +
"        i.formatorenta as ir_i_formatorenta,\n" +
"        i.genero as ir_i_genero,        \n" +
"        \n" +
"        ti.id as ir_i_ti_id,\n" +
"        ti.descripcion as ir_i_ti_descripcion\n" +
"\n" +
"        FROM VI_CLIENTES as c \n" +
"        left join VI_ITEMRENTADO as ir on c.documento=ir.CLIENTES_documento \n" +
"        left join VI_ITEMS as i on ir.ITEMS_id=i.id \n" +
"        left join VI_TIPOITEM as ti on i.TIPOITEM_id=ti.id ";

    
    }

    private static String getQuery2() {
        return ""
//                + "INSERT INTO VI_ITEMRENTADO VALUES(5678567, 1026585665, 5, '151111', '161112')";
//        + "DELETE FROM VI_ITEMRENTADO WHERE id='1026585672'";
                + "INSERT INTO VI_ITEMS VALUES(1274542, 'Tirador', 'Pelicula Tirador accion', '151111', '2000', 'DVD', 'accion', 2)";
    }
    
    private static String getQuery3() {
        return "" +
//                "select * from VI_CLIENTES";
                
                "select * from VI_ITEMRENTADO";
        
        
//                "        select\n" +
//"            i.id,\n" +
//"            i.nombre,\n" +
//"            i.descripcion,\n" +
//"            i.fechalanzamiento,\n" +
//"            i.tarifaxdia,\n" +
//"            i.formatorenta,\n" +
//"            i.genero,\n" +
//"            i.TIPOITEM_id,\n" +
//"            ti.id as ti_id,\n" +
//"            ti.descripcion as ti_descripcion\n" +
//"        FROM\n" +
//"            VI_ITEMS as i\n" +
//"        left join VI_TIPOITEM as ti on i.TIPOITEM_id=ti.id";
    }

    private static String getQuery4() {
        return "SELECT * FROM VI_ITEMS WHERE id NOT IN (SELECT ITEMS_id FROM VI_ITEMRENTADO)";
    }
}
