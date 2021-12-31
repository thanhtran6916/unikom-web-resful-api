package com.example.unikomwebresfulapi.service.tblRecruitment;

import com.example.unikomwebresfulapi.dto.request.TblRecruitmentRequest;
import com.example.unikomwebresfulapi.dto.response.TblRecruitmentResponse;
import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITblRecruitmentService extends IGeneralService<TblRecruitmentRequest, TblRecruitmentResponse> {

    Page<TblRecruitmentResponse> findTblRecruitmentsExist(String name, String salary, Pageable pageable);
}
