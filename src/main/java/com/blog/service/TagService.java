package com.blog.service;

import com.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    Tag updateTag(Long id,Tag tag);

    Page<Tag> listTags(Pageable pageable);

    List<Tag> listTags();

    List<Tag> listTags(String ids);

    void deleteTag(Long id);

    // ******************* 前端 ***********************
    List<Tag> listTagTop(Integer size);
}
