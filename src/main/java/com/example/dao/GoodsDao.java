package com.example.dao;

import com.example.db.DBManager;
import com.example.entity.Goods;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Types;

@Component
public class GoodsDao {
    public int insertGoods(String name, String brand) {
        NamedParameterJdbcTemplate jdbcTemplate = DBManager.getNamedJdbcTemplate();
        String sql = "insert into goods(name, brand, inventory) values (:name, :brand, 5)";
        Goods goods = new Goods();
        goods.setName(name);
        goods.setBrand(brand);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(goods));
    }

    public int insertGoodsJPA(String name, String brand) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setBrand(brand);
        goods.setPic_url("baidu.com");
        JdbcTemplate jdbcTemplate = DBManager.getJdbcTemplate();
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("goods");

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(
                goods);
        return insert.execute(parameters);
    }

    public String getGoods() {
        JdbcTemplate jdbcTemplate = DBManager.getJdbcTemplate();
        Goods goods = jdbcTemplate.queryForObject("select * from goods where name = ?", new Object[]{"ryan"}, new int[]{Types.VARCHAR},new BeanPropertyRowMapper<>(Goods.class));
        return goods.getBrand();
    }
}
