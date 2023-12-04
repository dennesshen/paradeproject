package com.parade.paradeproject.slide.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.parade.paradeproject.util.UserUtil;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class DtoOfSlide {
    
    @NotBlank
    private String webUrl;

    @Size(max = 255)
    private String title;

    private String pictureUrl;

    private Long category_id;

    private Long userId = UserUtil.getUserId();

}
