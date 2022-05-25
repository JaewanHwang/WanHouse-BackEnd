package com.ssafy.happyhouse.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HouseInfoResponse {
    private HouseInfoDto houseInfo;
    private HouseDetailInfoDto houseDetailInfo;
    private List<HouseDealDto> houseDealList;
    private List<AvgPricePerYear> avgPriceList;
    private Boolean likeThisApt;
}
