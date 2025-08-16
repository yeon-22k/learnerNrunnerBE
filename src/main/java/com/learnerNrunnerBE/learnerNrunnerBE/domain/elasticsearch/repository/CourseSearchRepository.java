package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.repository;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.document.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseSearchRepository extends ElasticsearchRepository<CourseDocument, Long> {
}
