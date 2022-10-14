package com.alkemy.ong.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
public class MemberPaginatedResponse {
    List<MemberResponse> memberPageContent;
    String nextPage;
    String previousPage;
}
