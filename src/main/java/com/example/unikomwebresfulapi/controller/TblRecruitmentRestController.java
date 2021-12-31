package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.dto.TblRecruitmentResponse;
import com.example.unikomwebresfulapi.helper.ResultResp;
import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.service.tblRecruitment.ITblRecruitmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tblRecruitments")
@CrossOrigin("*")
public class TblRecruitmentRestController {

    @Autowired
    private ITblRecruitmentService tblRecruitmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<TblRecruitment>> findAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TblRecruitment> tblRecruitments = tblRecruitmentService.findTblRecruitmentsExist(pageable);
        return new ResponseEntity<>(tblRecruitments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResultResp<TblRecruitmentResponse> findById(@PathVariable Long id) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentService.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            return new ResultResp<>(HttpStatus.NOT_FOUND);
        }
        TblRecruitmentResponse tblRecruitmentResponse = modelMapper.map(tblRecruitmentOptional.get(), TblRecruitmentResponse.class);
        ResultResp<?> resultResp = ResultResp.success(tblRecruitmentResponse);
        return new ResultResp<>(resultResp, HttpStatus.OK);
    }

    @PostMapping
    public ResultResp<TblRecruitmentResponse> save(@RequestBody TblRecruitment tblRecruitment) {
        TblRecruitment tblRecruitmentResult = tblRecruitmentService.save(tblRecruitment);
        TblRecruitmentResponse tblRecruitmentResponse = modelMapper.map(tblRecruitmentResult, TblRecruitmentResponse.class);
        ResultResp<?> resultResp = ResultResp.success(tblRecruitmentResponse);
        return new ResultResp<>(resultResp, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResultResp<TblRecruitment> edit(@PathVariable Long id, @RequestBody TblRecruitment tblRecruitment) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentService.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            return new ResultResp<>(HttpStatus.BAD_REQUEST);
        }
        TblRecruitment oldTblRecruitment = tblRecruitmentOptional.get();
        tblRecruitment.setId(oldTblRecruitment.getId());
        TblRecruitment tblRecruitmentResult = tblRecruitmentService.save(tblRecruitment);
        TblRecruitmentResponse tblRecruitmentResponse = modelMapper.map(tblRecruitmentResult, TblRecruitmentResponse.class);
        ResultResp<?> resultResp = ResultResp.success(tblRecruitmentResponse);
        return new ResultResp<>(resultResp, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TblRecruitment> delete(@PathVariable Long id) {
        boolean isDelete = tblRecruitmentService.delete(id);
        if (isDelete) {
            ResultResp<?> resultResp = ResultResp.delete();
            return new ResultResp<>(resultResp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
