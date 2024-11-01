package com.LearnTableExport.TableExport.controller;

import com.LearnTableExport.TableExport.service.ExportDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExportDataController {
    public static final Logger LOG = LoggerFactory.getLogger(ExportDataController.class);

    @Autowired
    private ExportDataService exportDataService;

    @GetMapping("/exportTableData")
    public ResponseEntity<String> exportTableData(
            @RequestParam(name = "tableName") String tableName
    ) {
        LOG.info("Exporting data from table: {}", tableName);
        String res = exportDataService.exportTableDataWithColumnsRemoved(tableName);
        return ResponseEntity.ok(res);
    }

}
