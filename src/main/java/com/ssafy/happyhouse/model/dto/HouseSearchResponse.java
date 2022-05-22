package com.ssafy.happyhouse.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HouseSearchResponse {
    private List<DongDto> dongList;
    private List<HouseInfoDto> houseList;
}
