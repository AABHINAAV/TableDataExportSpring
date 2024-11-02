package com.LearnTableExport.TableExport.repository.export;

import java.util.List;

public interface ExportDataRepository<T> {
    void exportTableToCsvWithColumnsRemoved(String tableName, String filePath, List<String> columnNames);
}
