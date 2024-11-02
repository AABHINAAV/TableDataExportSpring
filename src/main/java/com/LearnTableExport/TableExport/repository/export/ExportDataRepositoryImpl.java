package com.LearnTableExport.TableExport.repository.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ExportDataRepositoryImpl<T> implements ExportDataRepository<T> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void exportTableToCsvWithColumnsRemoved(String tableName, String filePath, List<String> columnNames) {
        String columns = String.join(",", columnNames);
        String sqlQuery = String.format("SELECT %s INTO OUTFILE '%s' FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\n' FROM %s", columns, filePath, tableName);
        jdbcTemplate.execute(sqlQuery);
    }
}
