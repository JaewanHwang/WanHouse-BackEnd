package com.ssafy.happyhouse.model.dto;

import lombok.Data;

@Data
public class HouseDealDto {
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
}
