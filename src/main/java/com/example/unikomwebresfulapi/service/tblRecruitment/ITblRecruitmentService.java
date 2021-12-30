package com.example.unikomwebresfulapi.service.tblRecruitment;

import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITblRecruitmentService extends IGeneralService<TblRecruitment> {

    Page<TblRecruitment> findTblRecruitmentsExist(Pageable pageable);
}
