package com.LearnTableExport.TableExport.repository.mainrepos;

import com.LearnTableExport.TableExport.model.Question;
import com.LearnTableExport.TableExport.repository.export.ExportDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, ExportDataRepository<Question> {
}
