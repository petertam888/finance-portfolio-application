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

    public List<UserStocks> getAllUserStocks(){
        String sql = "SELECT * FROM user_stocks";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new UserStocks(
                        resultSet.getLong("user_id"),
                        resultSet.getLong("stock_code"),
                        resultSet.getInt("number")
                )
        );
    }
}
