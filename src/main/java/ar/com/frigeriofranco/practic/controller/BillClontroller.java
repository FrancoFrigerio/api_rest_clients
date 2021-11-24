package ar.com.frigeriofranco.practic.controller;


import ar.com.frigeriofranco.practic.dto.BillRequestDto;
import ar.com.frigeriofranco.practic.dto.PageRequestDto;
import ar.com.frigeriofranco.practic.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/bill")
@Slf4j
public class BillClontroller {

    @Autowired
    BillService billService;


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?>getAllBills(){
        return ResponseEntity.ok(billService.findAllWithClient());
    }

    @PostMapping("/dto")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?>getDtos(@RequestBody PageRequestDto pageRequestDto, HttpServletRequest request){
        return ResponseEntity.ok(billService.findAllPage(pageRequestDto,request));
    }


    @PostMapping("/save/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> saveBill(@Valid @RequestBody  BillRequestDto billRequestDto,@PathVariable("id")Long id){
        return new ResponseEntity<>(billService.saveBill(billRequestDto,id), HttpStatus.CREATED);
    }


}
