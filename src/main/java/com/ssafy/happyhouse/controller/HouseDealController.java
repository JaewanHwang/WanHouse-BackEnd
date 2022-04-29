package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.service.HouseDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HouseDealController {
    HouseDealService houseDealService;
    @Autowired
    public void setHouseDealService(HouseDealService houseDealService) {
        this.houseDealService = houseDealService;
    }
}
