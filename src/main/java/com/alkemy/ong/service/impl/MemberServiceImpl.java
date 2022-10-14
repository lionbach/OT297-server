package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.models.entity.MemberEntity;
import com.alkemy.ong.models.mapper.MemberMapper;
import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.MemberPaginatedResponse;
import com.alkemy.ong.models.response.MemberResponse;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import com.alkemy.ong.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MemberRepository memberRepository;

    @Override
    public MemberResponse save(MemberRequest memberRequest) {
        MemberEntity memberEntity = memberMapper.memberRequest2MemberEntity(memberRequest);
        memberEntity = memberRepository.save(memberEntity);
        MemberResponse memberResponse = memberMapper.memberEntity2MemberResponse(memberEntity);
        return memberResponse;
    }

    @Override
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) throw new GenericException(HttpStatus.NOT_FOUND, "id not exist", "The member to delete does not exist");
        memberRepository.deleteById(id);
    }

    @Override
    public List<MemberResponse> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberResponse> memberResponseList = memberMapper.memberEntityList2MemberResponseList(memberEntityList);
        return memberResponseList;
    }

    @Override
    public MemberPaginatedResponse findAllPaginated(Integer numberOfPage) {
        PaginationUtils pagination = new PaginationUtils(memberRepository, numberOfPage, 3, "/members/paginated?page=%d");
        MemberPaginatedResponse memberPaginatedResponse = memberMapper.paginationUtils2MemberPaginationResponse(pagination);
        return memberPaginatedResponse;
    }

    @Override
    public MemberResponse update(MemberRequest memberRequest, Long id) {
        if (!memberRepository.existsById(id)) throw new GenericException(HttpStatus.NOT_FOUND, "id not exist", "The member to update does not exist");
        MemberEntity memberEntity = memberMapper.memberRequest2MemberUpdateEntity(memberRequest, id);
        memberEntity = memberRepository.save(memberEntity);
        MemberResponse memberResponse = memberMapper.memberEntity2MemberResponse(memberEntity);
        return memberResponse;
    }

}
