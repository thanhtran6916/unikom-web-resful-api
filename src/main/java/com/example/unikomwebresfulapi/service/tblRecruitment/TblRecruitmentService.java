package com.example.unikomwebresfulapi.service.tblRecruitment;

import com.example.unikomwebresfulapi.dto.request.TblRecruitmentRequest;
import com.example.unikomwebresfulapi.dto.response.TblRecruitmentResponse;
import com.example.unikomwebresfulapi.exception.ApplicationException;
import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.repository.ITblRecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TblRecruitmentService implements ITblRecruitmentService {

    @Autowired
    private ITblRecruitmentRepository tblRecruitmentRepository;

    @Override
    public Page<TblRecruitmentResponse> findAll() {
        return null;
    }

    @Override
    public TblRecruitmentResponse findById(Long id) {
        return tblRecruitmentRepository.findByIdAndDeletedFalse(id).map(TblRecruitmentResponse::new)
                .orElseThrow(() -> new ApplicationException("err.not-found"));
    }

    @Override
    public TblRecruitmentResponse save(TblRecruitmentRequest tblRecruitmentRequest) {
        TblRecruitment tblRecruitment = new TblRecruitment(tblRecruitmentRequest);
        return new TblRecruitmentResponse(tblRecruitmentRepository.save(tblRecruitment));
    }

    @Override
    public TblRecruitmentResponse edit(Long id, TblRecruitmentRequest tblRecruitmentRequest) {
        tblRecruitmentRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ApplicationException("err.not-found"));
        tblRecruitmentRequest.setId(id);
        return new TblRecruitmentResponse(tblRecruitmentRepository.save(new TblRecruitment(tblRecruitmentRequest)));
    }

    @Override
    public void delete(Long id) {
        TblRecruitment tblRecruitment = tblRecruitmentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException("err.not-found"));
        tblRecruitment.setDeleted(true);
        tblRecruitmentRepository.save(tblRecruitment);
    }

    @Override
    public Page<TblRecruitmentResponse> findTblRecruitmentsExist(String name, String salary, Pageable pageable) {
        Page<TblRecruitment> tblRecruitments = tblRecruitmentRepository.findTblRecruitmentsExist(name, salary, pageable);
        if (tblRecruitments == null) {
            throw new ApplicationException("err.not-found");
        }
        Page<TblRecruitmentResponse> tblRecruitmentResponses = tblRecruitments.map(TblRecruitmentResponse::new);
        return tblRecruitmentResponses;
    }

}

