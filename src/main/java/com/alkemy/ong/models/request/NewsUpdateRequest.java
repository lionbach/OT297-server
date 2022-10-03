package com.alkemy.ong.models.request;

import com.alkemy.ong.models.entity.CategoryEntity;
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
  //  private CategoryEntity category;
    private Long  categoryId;
}
