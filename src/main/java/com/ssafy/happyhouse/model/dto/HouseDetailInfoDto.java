package com.ssafy.happyhouse.model.dto;

import lombok.Data;

@Data
public class HouseDetailInfoDto {
    private String codeSec; // 경비 관리 방식
    private int kaptdScnt;
    private int kaptMgrCnt; // 일반관리 인원
    private String codeMgr; // 일반관리 방식
    private String codeClean;
    private int kaptdClcnt;
    private String codeGarbage;
    private int kaptdEcnt;
    private int kaptdPcnt;
    private int kaptdPcntu;
    private int kaptdCccnt;
    private String welfareFacility;
    private String kaptdWtimebus;
    private String subwayLine;
    private String subwayStation;
    private String kaptdWtimesub;
    private String convenientFacility;
    private String educationFacility;
}
