package com.alkemy.ong.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsUpdateResponse {
    private String name;
    private String content;
    private String image;
    private Long  categoryId;
}
