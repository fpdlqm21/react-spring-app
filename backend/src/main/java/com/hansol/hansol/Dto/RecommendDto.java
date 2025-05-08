package com.hansol.hansol.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecommendDto {
    private String clothes;

    public RecommendDto(String clothes){
        this.clothes = clothes;
    }
}
