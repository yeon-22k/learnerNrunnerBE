package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.document.CourseDocument;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.repository.CourseSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseSearchServiceImpl implements CourseSearchService {
    private final CourseSearchRepository courseSearchRepository;

    @Override
    public void indexCourses(List<Course> courses){
        log.info("ElasticSearch 색인 시작");
        List<CourseDocument> courseDocuments = courses.stream()
                .map(CourseDocument::from)
                .toList();
        courseSearchRepository.saveAll(courseDocuments);
        log.info("ElasticSearch 색인 완료");
    }

}
