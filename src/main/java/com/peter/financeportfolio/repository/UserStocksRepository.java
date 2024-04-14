package com.peter.financeportfolio.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.peter.financeportfolio.model.UserStocks;
import java.util.List;

@Repository
public class UserStocksRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserStocksRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserStocks> getAllUserStocks() {
        String sql = "SELECT u.username, us.user_id, us.stock_code, us.stock_number FROM user_stocks us " +
                "LEFT JOIN users u ON us.user_id = u.user_id";

        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new UserStocks(
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("stock_code"),
                        resultSet.getInt("stock_number")
                )
        );
    }
}
