package com.LearnTableExport.TableExport.service.impl;

import com.LearnTableExport.TableExport.model.Question;
import com.LearnTableExport.TableExport.repository.mainrepos.QuestionRepository;
import com.LearnTableExport.TableExport.service.ExportDataService;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExportDataServiceImpl implements ExportDataService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExportDataServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private QuestionRepository questionRepository;

    public Class<?> getEntityClassByName(String className) throws ClassNotFoundException {
        return Class.forName("com.LearnTableExport.TableExport.model." + className);
    }

    public List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();

        try {
            Class<?> entityClassByName = getEntityClassByName(tableName);
            Field[] fields = entityClassByName.getDeclaredFields();
            columnNames = Arrays.stream(fields)
                    .map(field -> {
                        if (field.getName().equals("serialVersionUID")) {
                            return null;
                        }

                        if (field.isAnnotationPresent(Column.class) && !field.getAnnotation(Column.class).name().isEmpty()) {
                            return field.getAnnotation(Column.class).name();
                        } else {
                            return field.getName();
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return columnNames;
    }

    public List<String> removePiiColumns(List<String> columnNames, String tableName) {
        List<String> piiColumns = ExportDataUtil.piiColumnsPerTable.get(tableName);

        if (piiColumns != null && !piiColumns.isEmpty()) {
            columnNames = columnNames
                    .stream()
                    .filter(column -> !piiColumns.contains(column))
                    .collect(Collectors.toList());
        }

        return columnNames;
    }

    @Override
    public String exportTableDataWithQueryAnnotation(String tableName) {
        String filePath = "D:/VIDA/SpringBoot/TableExport/dumps/" + tableName + ".csv";
        List<String> columnNames = getColumnNames(tableName);
        columnNames = removePiiColumns(columnNames, tableName);
        LOGGER.info("Column names in {} : {}", tableName, String.join(", ", columnNames));
        questionRepository.exportTableToCsvWithColumnsRemoved(tableName, filePath, columnNames);
        return "Exporting data from table: " + tableName + " completed successfully.";
    }

    @Override
    public List<Question> getAllData() {
        return questionRepository.findAll();
    }
}
