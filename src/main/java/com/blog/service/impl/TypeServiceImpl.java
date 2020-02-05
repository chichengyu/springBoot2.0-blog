package com.blog.service.impl;

import com.blog.exception.NotFoundException;
import com.blog.pojo.Type;
import com.blog.repository.TypeRepository;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public Type updateType(Long id, Type type) {
        if (getType(id) == null){
            throw new NotFoundException("不存在该类型！");
        }
        type.setId(id);
        return typeRepository.save(type);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    // ********************** 前端 **********************
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        return typeRepository.findTop(PageRequest.of(0, size, sort));
    }
}
