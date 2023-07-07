package util;

import java.sql.*;

public class DBUtil {

    private Connection conn = null;

    public DBUtil(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/table_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false","root","root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        return this.conn;
    }

    public ResultSet executeQuery(String sql){
        ResultSet rs=null;
        try{
            Statement stmt=this.conn.createStatement();
            rs=stmt.executeQuery(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public void close(Connection conn, ResultSet...rs){
        for (int i=0;i<rs.length;i++){
            if(rs[i]!=null){//关闭结果集
                try{
                    rs[i].close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(conn!=null){//关闭连接
            try{
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
