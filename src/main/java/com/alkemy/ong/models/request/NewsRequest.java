package com.alkemy.ong.models.request;

import com.alkemy.ong.models.response.CategoryResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class NewsRequest {
    @ApiModelProperty(name = "Name",
            value = "Title of the News",
            dataType = "String",
            notes = "It can't be null, It can´t be empty",
            example = "Relatos de esfuerzo y recuperación: " +
                    "conoce nuestro hospital de cirugía reconstructiva en Amman, Jordania",
            required = true)
    @NotNull(message = "the name can't be null")
    @NotEmpty(message = "the name can't be empty")
    private String name;

    @ApiModelProperty(name = "Content",
            value = "Content of the news",
            dataType = "String",
            notes = "It can't be null, It can´t be empty",
            example = "Desde 2006, nuestro hospital especializado en servicios de cirugía reconstructiva " +
                    "en Amman, Jordania, atiende a personas de todo el Medio Oriente que no tienen acceso a la " +
                    "atención quirúrgica especializada y rehabilitación necesaria en sus países de origen.",
            required = true)
    @NotNull(message = "the name can't be null")
    @NotEmpty(message = "the name can't be empty")
    private String content;

    @ApiModelProperty(name = "Image",
            value = "Image of the news",
            dataType = "String",
            notes = "It can't be null, It can´t be empty",
            example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAQCAIAAABV4/KnAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAENSURBVChTbclPT4JgHMDx3zs2Ix4w68QDgiyzrSYif5oiSRBYq+Es3aJQnGydOnSoDnkxolWvIFrpwbV9Tt8vFNh6hsLSQo1ma0DhLNURIy1l76+uAIpprED/VhIrP5VmVYSVXySjrmMdNks6YnUktJFobfCdNdxEgglFXu9eP55P30/jL2/6KTTHZNkGqT3oTWYVIyzu97zozbh6zmETSKxuVyxRu6waQff2xRo+IdGGvdbQj18bZ/ecGnhh4o4SotwBa/Dgxwm94xXEk4vx3L2Z5XkTJCfy7z7cKD0K5vYodcI0zztAYE3U+rutYKvqYck/OJ7kWAMQp5CcSnIyWiBKh0CzcobipCXEyd/X/Mf17tf+6AAAAABJRU5ErkJggg==",
            required = true)
    @NotNull(message = "the name can't be null")
    @NotEmpty(message = "the name can't be empty")
    private String image;

    private CategoryResponse category;

    @ApiModelProperty(name = "Category Id",
            value = "Id of category of the news",
            dataType = "String",
            notes = "id of the category corresponding to the news to publish",
            example = "1",
            required = true)
    private Long categoryId;
}
