package com.blog.repository;

import com.blog.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(@Param("name")String name);

    // ******************* 前端 ***********************
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
