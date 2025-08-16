package com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.service;

import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.document.CourseDocument;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.dto.CourseSearchResponseDto;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.elasticsearch.repository.CourseSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseSearchServiceImpl implements CourseSearchService {
    private final CourseSearchRepository courseSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public void indexCourses(List<Course> courses){
        log.info("ElasticSearch 색인 시작");
        List<CourseDocument> courseDocuments = courses.stream()
                .map(CourseDocument::from)
                .toList();
        courseSearchRepository.saveAll(courseDocuments);
        log.info("ElasticSearch 색인 완료");
    }

    @Override
    public List<CourseSearchResponseDto> searchByKeyword(String keyword){
        MultiMatchQuery multiMatchQuery = MultiMatchQuery.of(mmq -> mmq
                .query(keyword)
                .fields("title", "description", "tags"));

        Query query = Query.of(q -> q.multiMatch(multiMatchQuery));

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query).build();

        SearchHits<CourseDocument> searchHits = elasticsearchOperations.search(nativeQuery, CourseDocument.class);

        return searchHits.stream()
                .map(hit -> CourseSearchResponseDto.from(hit.getContent()))
                .collect(Collectors.toList());
    }


}
