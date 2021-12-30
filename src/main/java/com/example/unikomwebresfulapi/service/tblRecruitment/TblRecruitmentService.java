package com.example.unikomwebresfulapi.service.tblRecruitment;

import com.example.unikomwebresfulapi.exception.ExceptionMessage;
import com.example.unikomwebresfulapi.exception.NotFoundException;
import com.example.unikomwebresfulapi.exception.NotSaveException;
import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.repository.ITblRecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TblRecruitmentService implements ITblRecruitmentService {

    @Autowired
    private ITblRecruitmentRepository tblRecruitmentRepository;

    @Override
    public Page<TblRecruitment> findTblRecruitmentsExist(Pageable pageable) {
        Page<TblRecruitment> tblRecruitments = tblRecruitmentRepository.findTblRecruitmentsExist(pageable);
        if (tblRecruitments == null) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND_ENTITY);
        }
        return tblRecruitments;
    }

    @Override
    public Iterable<TblRecruitment> findAll() {
        Iterable<TblRecruitment> tblRecruitments = tblRecruitmentRepository.findAll();
        if (tblRecruitments == null) {
            throw new NotFoundException(ExceptionMessage.NOT_FOUND_ENTITY);
        }
        return tblRecruitments;
    }

    @Override
    public Optional<TblRecruitment> findById(Long id) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentRepository.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            throw new NotFoundException(ExceptionMessage.ID_NOT_FOUND);
        }
        return tblRecruitmentRepository.findById(id);
    }

    @Override
    public TblRecruitment save(TblRecruitment tblRecruitment) {
        TblRecruitment tblRecruitmentResponse = tblRecruitmentRepository.save(tblRecruitment);
        if (tblRecruitmentResponse == null) {
            throw new NotSaveException(ExceptionMessage.SAVE_PERSIST);
        }
        return tblRecruitmentRepository.save(tblRecruitment);
    }

    @Override
    public boolean delete(Long id) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentRepository.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            throw new NotFoundException(ExceptionMessage.ID_NOT_FOUND);
        }
        TblRecruitment tblRecruitment = tblRecruitmentOptional.get();
        tblRecruitment.setDeleted(true);
        tblRecruitmentRepository.save(tblRecruitment);
        return true;
    }
}

