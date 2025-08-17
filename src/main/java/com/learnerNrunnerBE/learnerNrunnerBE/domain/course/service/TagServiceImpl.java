package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Tag;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag findOrCraateTag(String tagName){
        return tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(new Tag(tagName)));
    }
}
