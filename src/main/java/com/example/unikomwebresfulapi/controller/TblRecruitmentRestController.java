package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.dto.request.TblRecruitmentRequest;
import com.example.unikomwebresfulapi.dto.response.TblRecruitmentResponse;
import com.example.unikomwebresfulapi.helper.ResultResp;
import com.example.unikomwebresfulapi.service.tblRecruitment.ITblRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recruitments")
@CrossOrigin("*")
public class TblRecruitmentRestController {

    @Autowired
    private ITblRecruitmentService tblRecruitmentService;

    @GetMapping
    public ResultResp findAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Optional<String> q,
            @RequestParam Optional<String> s) {
        Pageable pageable = PageRequest.of(page, size);
        String name = q.orElse("");
        String salary = s.orElse("");
        return new ResultResp(tblRecruitmentService.findTblRecruitmentsExist(name, salary, pageable));
    }

    @GetMapping("{id}")
    public ResultResp findById(@PathVariable Long id) {
        return new ResultResp(tblRecruitmentService.findById(id));
    }

    @PostMapping
    public ResultResp save(@RequestBody TblRecruitmentRequest tblRecruitmentRequest) {
        return new ResultResp(tblRecruitmentService.save(tblRecruitmentRequest));
    }

    @PutMapping("{id}")
    public ResultResp edit(@PathVariable Long id, @RequestBody TblRecruitmentRequest tblRecruitmentRequest) {
        TblRecruitmentResponse tblRecruitmentResponse = tblRecruitmentService.edit(id, tblRecruitmentRequest);
        return new ResultResp(tblRecruitmentResponse);
    }

    @DeleteMapping("{id}")
    public ResultResp delete(@PathVariable Long id) {
        tblRecruitmentService.delete(id);
        return new ResultResp();
    }

}
