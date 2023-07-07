package dao;

import domain.bean.Order;
import domain.dto.OrderParamsDTO;
import domain.vo.CommonVO;
import domain.vo.OrderVO;
import util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public CommonVO<OrderVO> getAllOrderByQueryPage(OrderParamsDTO paramsDTO){
        List<OrderVO> orderVOList = new ArrayList<>();
        int totalCount = 0;

        //sql拼接
        String sql_prefix = "SELECT * FROM tbl_order WHERE 1=1 ";
        String sql_suffix = " ORDER BY order_time LIMIT "+paramsDTO.getPageSize()+" OFFSET "+(paramsDTO.getPageNumber()-1)* paramsDTO.getPageSize();
        String sql_condition = "";
        if (paramsDTO.getCusNo() != null && !paramsDTO.getCusNo().equals("")){
            sql_condition = sql_condition + " AND customer_number='"+paramsDTO.getCusNo().toUpperCase().trim()+"'";
        }
        String sql = sql_prefix + sql_condition + sql_suffix;
        String totalCountSql = "select count(*) from tbl_order where 1=1 "+sql_condition;

        DBUtil dbUtil = new DBUtil();
        ResultSet totalCountResult = null;
        ResultSet orderResult = null;
        try{
            totalCountResult = dbUtil.executeQuery(totalCountSql);
            while (totalCountResult.next()){
                totalCount = totalCountResult.getInt(1);
                if (totalCount != 0){
                    orderResult = dbUtil.executeQuery(sql);
                    while (orderResult.next()){
                        int id = orderResult.getInt("id");
                        String custNo = orderResult.getString("customer_number");
                        int age = orderResult.getInt("age");
                        BigDecimal amount = orderResult.getBigDecimal("amount");
                        LocalDateTime orderTime = orderResult.getTimestamp("order_time").toLocalDateTime();

                        OrderVO orderVO = new OrderVO(id,custNo,age,amount,orderTime);
                        orderVOList.add(orderVO);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.close(dbUtil.getConn(),totalCountResult,orderResult);
        }

        return new CommonVO<>(paramsDTO.getPageNumber(), paramsDTO.getPageSize(), totalCount,orderVOList);
    }

    public boolean add(Order order){
        String sql = "insert into tbl_order values(null,?,?,?,?)";
        DBUtil dbUtil = new DBUtil();
        PreparedStatement ps = null;
        try{
            ps = dbUtil.getConn().prepareStatement(sql);
            ps.setString(1,order.getCustomerNumber());
            ps.setInt(2,order.getAge());
            ps.setBigDecimal(3,order.getAmount());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.close(dbUtil.getConn(),null);
        }
    }

    public Order getById(int id){
        Order order = null;
        DBUtil dbUtil = new DBUtil();
        String sql = "select * from tbl_order where id="+id;
        ResultSet rs = null;
        try{
            rs = dbUtil.executeQuery(sql);
            if(rs.next()) {
                int beanId = rs.getInt("id");
                String custNo = rs.getString("customer_number");
                int age = rs.getInt("age");
                BigDecimal amount = rs.getBigDecimal("amount");
                LocalDateTime orderTime = rs.getTimestamp("order_time").toLocalDateTime();
                order = new Order(beanId,custNo,age,amount,orderTime);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.close(dbUtil.getConn(),rs);
        }
        return order;
    }

    public int update(Order order){
        int result = 0;
        String sql = "update tbl_order set customer_number=?,age=?,amount=?,order_time=? where id=?";
        DBUtil dbUtil = new DBUtil();
        PreparedStatement ps = null;
//        ResultSet rs = null;
        try{
            ps = dbUtil.getConn().prepareStatement(sql);
            ps.setString(1,order.getCustomerNumber());
            ps.setInt(2,order.getAge());
            ps.setBigDecimal(3,order.getAmount());

            result = ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.close(dbUtil.getConn(), null);
        }
        return result;
    }

    public boolean deleteById(int id){
        boolean result = false;
        String sql = "delete from tbl_order where id="+id;
        DBUtil dbUtil = new DBUtil();
        Statement s = null;
        try{
            s = dbUtil.getConn().createStatement();
            result = s.execute(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.close(dbUtil.getConn(),null);
        }
        return result;
    }

}
