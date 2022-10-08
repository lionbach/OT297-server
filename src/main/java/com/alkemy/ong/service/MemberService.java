package com.alkemy.ong.service;

import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse save(MemberRequest memberRequest);
    void delete(Long id);
    List<MemberResponse> findAll();
    MemberResponse update(MemberRequest memberRequest, Long id);
}
