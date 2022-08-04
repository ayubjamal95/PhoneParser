package com.example.GermanPhoneParser.repository;

import com.example.GermanPhoneParser.domain.ParserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sun.java2d.pipe.GlyphListLoopPipe;

import java.math.BigDecimal;
import java.util.List;

public interface ParserRepository extends JpaRepository<ParserDomain, String> {

    List<ParserDomain> findAllByTaskId(Long taskId);
    String deleteByTaskId(Long taskId);

    @Query("select distinct taskId from ParserDomain")
    List<Long> findDistinctTasks();

    @Query( "select max(taskId) from ParserDomain")
    Integer findMaxTaskId();
}