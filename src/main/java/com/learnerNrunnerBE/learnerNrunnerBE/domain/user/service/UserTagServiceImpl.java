package com.learnerNrunnerBE.learnerNrunnerBE.domain.user.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Course;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Tag;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service.TagService;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.UserTag;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserTagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserTagServiceImpl implements UserTagService {
    private final UserTagRepository userTagRepository;
    private final TagService tagService;

    @Override
    public void addTagsToUserFromCourse(User user, Course course){
      log.info("태그 업데이트");

      Stream.of(course.getCategory(), course.getTag())
              .filter(tagName -> tagName != null && !tagName.isBlank())
              .forEach(tagName -> {
                  Tag tag = tagService.findOrCraateTag(tagName);
                  boolean alreadyExists = userTagRepository.existsByUserAndTag(user, tag);
                  if (!alreadyExists) {
                      userTagRepository.save(new UserTag(user, tag));
                      log.info("태그 {} 추가", tagName);
                  }
              });
    }
}
