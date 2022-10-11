package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.MemberRequest;

import com.alkemy.ong.models.response.MemberResponse;
import com.alkemy.ong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> save(@Valid @RequestBody MemberRequest memberRequest) {
        MemberResponse memberSavedResponse  = memberService.save(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberSavedResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<List<MemberResponse>> findAll(){
        List<MemberResponse> membersResponse  = memberService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(membersResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> update(@Valid @RequestBody MemberRequest memberRequest, @PathVariable Long id ) {
        MemberResponse memberResponse  = memberService.update(memberRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

}
