package com.LearnTableExport.TableExport.service;

import com.LearnTableExport.TableExport.model.Question;

import java.util.List;

public interface ExportDataService {
    public String exportTableDataWithQueryAnnotation(String tableName);
    public List<Question> getAllData();
}
