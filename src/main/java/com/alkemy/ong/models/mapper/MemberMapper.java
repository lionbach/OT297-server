package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.MemberEntity;
import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.MemberPaginatedResponse;
import com.alkemy.ong.models.response.MemberResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.AwsService;
import com.alkemy.ong.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {
    @Autowired
    AwsService awsService;
    @Autowired
    MemberRepository memberRepository;

    public MemberEntity memberRequest2MemberEntity(MemberRequest mapperRequest) {
        MemberEntity mapperResponse = new MemberEntity();
        mapperResponse.setName(mapperRequest.getName());
        mapperResponse.setFacebookUrl(mapperRequest.getFacebookUrl());
        mapperResponse.setInstagramUrl(mapperRequest.getInstagramUrl());
        mapperResponse.setLinkedinUrl(mapperRequest.getLinkedinUrl());
        mapperResponse.setImage(awsService.uploadFileFromBase64(mapperRequest.getImage()));
        mapperResponse.setDescription(mapperRequest.getDescription());
        return mapperResponse;
    }

    public MemberResponse memberEntity2MemberResponse(MemberEntity mapperRequest) {
        MemberResponse mapperResponse = new MemberResponse();
        mapperResponse.setId(mapperRequest.getId());
        mapperResponse.setName(mapperRequest.getName());
        mapperResponse.setFacebookUrl(mapperRequest.getFacebookUrl());
        mapperResponse.setInstagramUrl(mapperRequest.getInstagramUrl());
        mapperResponse.setLinkedinUrl(mapperRequest.getLinkedinUrl());
        mapperResponse.setImage(mapperRequest.getImage());
        mapperResponse.setDescription(mapperRequest.getDescription());
        mapperResponse.setTimestamps(mapperRequest.getTimestamps());
        return mapperResponse;
    }

    public List<MemberResponse> memberEntityList2MemberResponseList(List<MemberEntity> memberEntityList) {
        List<MemberResponse> mapperResponse = new ArrayList<>();
        for (MemberEntity ent: memberEntityList) {
            MemberResponse res = memberEntity2MemberResponse(ent);
            mapperResponse.add(res);
        }
        return mapperResponse;
    }

    public MemberEntity memberRequest2MemberUpdateEntity(MemberRequest mapperRequest, Long id) {
        MemberEntity mapperResponse = memberRequest2MemberEntity(mapperRequest);
        mapperResponse.setId(id);
        mapperResponse.setTimestamps(memberRepository.findById(id).get().getTimestamps());
        return mapperResponse;
    }

    public MemberPaginatedResponse paginationUtils2MemberPaginationResponse(PaginationUtils pagination) {
        Page page = pagination.getPage();
        List<MemberEntity> memberEntityList =  page.getContent();
        List<MemberResponse> memberResponseList = memberEntityList2MemberResponseList(memberEntityList);

        MemberPaginatedResponse memberPaginatedResponse = new MemberPaginatedResponse();
        memberPaginatedResponse.setMemberPageContent(memberResponseList);
        memberPaginatedResponse.setPreviousPage(pagination.getPrevious());
        memberPaginatedResponse.setNextPage(pagination.getNext());
        return memberPaginatedResponse;
    }
}
