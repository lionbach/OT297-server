package com.alkemy.ong.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsUpdateRequest {

    private String name;
    private String content;
    private String image;
    private Long  categoryId;
}
