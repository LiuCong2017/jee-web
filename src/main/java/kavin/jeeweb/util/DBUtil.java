package kavin.jeeweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public DBUtil(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/table_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false","root","root");
    }

    public void close(Connection conn){
        if(conn!=null){//关闭连接
            try{
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
