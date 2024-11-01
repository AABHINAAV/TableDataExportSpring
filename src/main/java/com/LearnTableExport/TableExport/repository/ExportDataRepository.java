package com.LearnTableExport.TableExport.repository;

import java.util.List;

public interface ExportDataRepository {
    void exportTableToCsvWithColumnsRemoved(String tableName, String filePath, List<String> columnNames);
}
