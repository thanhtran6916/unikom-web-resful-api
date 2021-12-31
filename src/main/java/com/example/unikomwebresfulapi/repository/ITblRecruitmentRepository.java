package com.example.unikomwebresfulapi.repository;

import com.example.unikomwebresfulapi.model.TblRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITblRecruitmentRepository extends JpaRepository<TblRecruitment, Long> {

    @Query(value = "SELECT t FROM TblRecruitment t WHERE t.name like %:name% and t.salary like %:salary% and t.isDeleted = false ")
    Page<TblRecruitment> findTblRecruitmentsExist(@Param("name") String name,
                                                  @Param("salary") String salary,
                                                  Pageable pageable);

}
