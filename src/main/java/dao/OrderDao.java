package dao;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class OrderDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public  Order findByOrderId(int id){
        final Order order=new Order();
        String sql="SELECT id ,orderName,number,price from order where id=?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                order.setId(resultSet.getInt(1));
                order.setNumber(resultSet.getInt(2));
                order.setOrderName(resultSet.getString(3));
                order.setPrice(resultSet.getInt(4));
            }
        });
        return order;
    }
    public  int DeleteByOrderId(Order order){

        String sql="Delete * from order where id=?";
        return jdbcTemplate.update(sql,order.getNumber(),order.getOrderName(),order.getPrice());
    }
}
