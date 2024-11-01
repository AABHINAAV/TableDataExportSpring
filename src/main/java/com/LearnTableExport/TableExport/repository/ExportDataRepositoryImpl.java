package com.LearnTableExport.TableExport.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExportDataRepositoryImpl implements ExportDataRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void exportTableToCsvWithColumnsRemoved(String tableName, String filePath, List<String> columnNames) {
        String columns = String.join(",", columnNames);
        String sqlQuery = String.format("SELECT %s INTO OUTFILE '%s' FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\n' FROM %s", columns, filePath, tableName);
//        String sqlQuery = String.format(
//                "SELECT %s UNION SELECT %s INTO OUTFILE '%s' FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\n' FROM %s",
//                columns, columns, filePath, tableName
//        );
        jdbcTemplate.execute(sqlQuery);
    }
}
