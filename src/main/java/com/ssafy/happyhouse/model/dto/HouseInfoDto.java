package com.ssafy.happyhouse.model.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class HouseInfoDto {

	private BigInteger aptCode;
	private String kaptCode; // 단지 코드
	private String aptName;
	private String area;
	private String dongCode;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;

}
