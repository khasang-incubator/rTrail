package io.khasang.rtrail.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable() {
    }

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String createTableStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS cats");
            jdbcTemplate.execute("CREATE TABLE PUBLIC.cats\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    name character varying(255),\n" +
                    "    description character varying(255),\n" +
                    "    color_id integer,\n" +
                    "    CONSTRAINT cats_pkey PRIMARY KEY (id)\n" +
                    ")");
            return "table created";
        } catch (Exception e) {
            return "Table creation failed: " + e;
        }
    }

    public String getCatByName(String name) {
        return String.valueOf(jdbcTemplate.query("select * from cats where name = " + "'" + name + "'", new RowMapper<Cat>() {
            @Override
            public Cat mapRow(ResultSet rs, int rownumber) throws SQLException {
                Cat e = new Cat();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setDescription(rs.getString(3));
                e.setColorid(rs.getInt(4));
                return e;
            }
        }));

    }



    public String joinTables() {
        return String.valueOf(jdbcTemplate.query("select cats.name, cats.description, color.color from cats LEFT JOIN color ON cats.color_id=color.id", new RowMapper<CatDescription>() {
            @Override
            public CatDescription mapRow(ResultSet rs, int rownumber) throws SQLException {
                CatDescription e = new CatDescription();
                e.setName(rs.getString(1));
                e.setDescription(rs.getString(2));
                e.setColor(rs.getString(3));
                return e;
            }
        }));
    }

    public String innerSelect(String catColor) {
        return String.valueOf(jdbcTemplate.query("SELECT cats.name, color.color FROM cats, color WHERE color =" +
                " (select color from color where color.id=cats.color_id and color=" + "'" + catColor + "'" + ");", new RowMapper<CatDescription>() {
            @Override
            public CatDescription mapRow(ResultSet rs, int rownumber) throws SQLException {
                CatDescription e = new CatDescription();
                e.setName(rs.getString(1));
                e.setColor(rs.getString(2));
                return e;
            }
        }));
    }

}
