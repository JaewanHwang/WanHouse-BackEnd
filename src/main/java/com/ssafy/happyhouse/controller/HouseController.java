package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.*;
import com.ssafy.happyhouse.model.service.interfaces.HouseService;
import com.ssafy.happyhouse.model.service.interfaces.JwtService;
import com.ssafy.happyhouse.model.service.interfaces.OpenApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/house")
public class HouseController {

    private HouseService houseService;
    private OpenApiService openApiService;

    private JwtService jwtService;

    @Autowired
    public void setHouseService(HouseService houseService) {
        this.houseService = houseService;
    }

    @Autowired
    public void setOpenApiService(OpenApiService openApiService) {
        this.openApiService = openApiService;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/sido")
    public @ResponseBody ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
        log.debug("sido : {}", houseService.getSido());
        return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getSido(), HttpStatus.OK);
    }

    @GetMapping("/gugun")
    public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
        return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getGugunInSido(sido), HttpStatus.OK);
    }

    @GetMapping("/dong")
    public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
        return new ResponseEntity<List<HouseInfoDto>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
    }

    @GetMapping("/apts")
    public ResponseEntity<HouseSearchResponse> getApts(@RequestParam Map<String, Integer> filters, @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        System.out.println(filters);
        HouseSearchResponse houseSearchResponse = new HouseSearchResponse();
        if (keyword != null) {
            houseSearchResponse.setDongList(houseService.getDongs(keyword));
        }
        houseSearchResponse.setHouseList(houseService.getApts(filters));
        return new ResponseEntity<>(houseSearchResponse, HttpStatus.OK);
    }

    @GetMapping("/apts/{aptCode}")
    public ResponseEntity<?> getAptDetail(@PathVariable BigInteger aptCode) throws Exception {
        HouseInfoResponse houseInfoResponse = new HouseInfoResponse();
        houseInfoResponse.setHouseInfo(houseService.getHouseInfo(aptCode));
        if (houseInfoResponse.getHouseInfo().getKaptCode() != null) {
            houseInfoResponse.setHouseDetailInfo(openApiService.fetchHouseInfo(houseInfoResponse.getHouseInfo().getKaptCode()));
        }
        houseInfoResponse.setHouseDealList(houseService.getHouseDealsByAptCode(aptCode));
        return ResponseEntity.ok().body(houseInfoResponse);
    }

    @PostMapping("/like/{aptCode}")
    public ResponseEntity<?> likeThisApt(@PathVariable BigInteger aptCode, HttpServletRequest request) {
        if (jwtService.isValidToken(request.getHeader("access-token"))) {
            houseService.likeThisApt(aptCode, jwtService.parseJWT(request.getHeader("access-token")));
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/unlike/{aptCode}")
    public ResponseEntity<?> unlikeThisApt(@PathVariable BigInteger aptCode, HttpServletRequest request) {
        if (jwtService.isValidToken(request.getHeader("access-token"))) {
            houseService.unlikeThisApt(aptCode, jwtService.parseJWT(request.getHeader("access-token")));
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/likes")
    public ResponseEntity<List<LikedHouseDto>> getLikedApts(HttpServletRequest request) {
        return ResponseEntity.ok(houseService.getLikedApts(jwtService.parseJWT(request.getHeader("access-token"))));
    }

}

