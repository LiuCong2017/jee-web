package dao;

import domain.bean.Order;
import domain.dto.OrderParamsDTO;
import domain.vo.CommonVO;
import domain.vo.OrderVO;
import util.DBUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    };

}
