package com.blog.service.impl;

import com.blog.exception.NotFoundException;
import com.blog.pojo.Tag;
import com.blog.repository.TagRepository;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagRepository.findById(id).orElse(null);
        if (tag1 == null){
            throw new NotFoundException("不存在该类型！");
        }
        tag.setId(id);
        return tagRepository.save(tag);
    }

    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    /**
     * 通过 ids 查询列表
     * @param ids
     * @return
     */
    @Override
    public List<Tag> listTags(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    // ******************* 前端 ***********************
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        return tagRepository.findTop(PageRequest.of(0, size, sort));
    }
}
