package com.LearnTableExport.TableExport.service.impl;

import java.util.List;
import java.util.Map;

public final class ExportDataUtil {

    public static final Map<String, List<String>> piiColumnsPerTable = Map.of(
            "Question", List.of("givenAnswer", "quiz")
    );
}
