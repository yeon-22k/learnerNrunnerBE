package com.learnerNrunnerBE.learnerNrunnerBE.domain.course.service;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.course.entity.Tag;

public interface TagService {
    Tag findOrCraateTag(String tagName);
}
