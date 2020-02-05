package com.blog.repository;

import com.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(@Param("name") String name);

    // ********************* 前端 **********************
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
