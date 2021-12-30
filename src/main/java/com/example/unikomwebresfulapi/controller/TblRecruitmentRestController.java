package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.model.TblRecruitment;
import com.example.unikomwebresfulapi.service.tblRecruitment.ITblRecruitmentService;
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

    @GetMapping
    public ResponseEntity<Page<TblRecruitment>> findAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TblRecruitment> tblRecruitments = tblRecruitmentService.findTblRecruitmentsExist(pageable);
        return new ResponseEntity<>(tblRecruitments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TblRecruitment> findById(@PathVariable Long id) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentService.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tblRecruitmentOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TblRecruitment> save(@RequestBody TblRecruitment tblRecruitment) {
        return new ResponseEntity<>(tblRecruitmentService.save(tblRecruitment), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TblRecruitment> edit(@PathVariable Long id, @RequestBody TblRecruitment tblRecruitment) {
        Optional<TblRecruitment> tblRecruitmentOptional = tblRecruitmentService.findById(id);
        if (!tblRecruitmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TblRecruitment oldTblRecruitment = tblRecruitmentOptional.get();
        tblRecruitment.setId(oldTblRecruitment.getId());
        return new ResponseEntity<>(tblRecruitmentService.save(tblRecruitment), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TblRecruitment> delete(@PathVariable Long id) {
        boolean isDelete = tblRecruitmentService.delete(id);
        if (isDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
