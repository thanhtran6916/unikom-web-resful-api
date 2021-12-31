package com.example.unikomwebresfulapi.service.tblRecruitment;

import com.example.unikomwebresfulapi.dto.request.TblRecruitmentRequest;
import com.example.unikomwebresfulapi.dto.response.TblRecruitmentResponse;
import com.example.unikomwebresfulapi.exception.ApplicationException;
import com.example.unikomwebresfulapi.exception.ExceptionMessage;
import com.example.unikomwebresfulapi.exception.NotFoundException;
import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.repository.ITblRecruitmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class TblRecruitmentService implements ITblRecruitmentService {

    @Autowired
    private ITblRecruitmentRepository tblRecruitmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<TblRecruitmentResponse> findAll() {
        return null;
    }

    @Override
    public TblRecruitmentResponse findById(Long id) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentRepository.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            throw new NotFoundException(ExceptionMessage.ID_NOT_FOUND);
        }
        return modelMapper.map(tblRecruitmentOptional.get(), TblRecruitmentResponse.class);
    }

    @Override
    public TblRecruitmentResponse save(TblRecruitmentRequest tblRecruitmentRequest) {
        TblRecruitment tblRecruitment = modelMapper.map(tblRecruitmentRequest, TblRecruitment.class);
        return modelMapper.map(tblRecruitmentRepository.save(tblRecruitment), TblRecruitmentResponse.class);
    }

    @Override
    public TblRecruitmentResponse edit(Long id, TblRecruitmentRequest tblRecruitmentRequest) {
        Optional<TblRecruitment> oldTblRecruitment = tblRecruitmentRepository.findById(id);
        oldTblRecruitment.orElseThrow(() -> new ApplicationException());
        tblRecruitmentRequest.setId(id);
        TblRecruitment tblRecruitment = modelMapper.map(tblRecruitmentRequest, TblRecruitment.class);
        return modelMapper.map(tblRecruitmentRepository.save(tblRecruitment), TblRecruitmentResponse.class);
    }

    @Override
    public void delete(Long id) {
        Optional<TblRecruitment> oldTblRecruitment = tblRecruitmentRepository.findById(id);
        oldTblRecruitment.orElseThrow(() -> new ApplicationException());
        tblRecruitmentRepository.deleteById(id);
    }

    @Override
    public Page<TblRecruitmentResponse> findTblRecruitmentsExist(String name, String salary, Pageable pageable) {
        Page<TblRecruitment> tblRecruitments = tblRecruitmentRepository.findTblRecruitmentsExist(name, salary, pageable);
        if (tblRecruitments == null) {
//            throw new Exception();
        }
        Page<TblRecruitmentResponse> tblRecruitmentResponses = tblRecruitments.map(new Function<TblRecruitment, TblRecruitmentResponse>() {
            @Override
            public TblRecruitmentResponse apply(TblRecruitment tblRecruitment) {
                return modelMapper.map(tblRecruitment, TblRecruitmentResponse.class);
            }
        });

        return tblRecruitmentResponses;
    }

}

