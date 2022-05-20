package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.HouseDetailResponseDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.OpenApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	private HouseService houseService;

	@Autowired
	private OpenApiService openApiService;
	
	@GetMapping("/sido")
	public @ResponseBody ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", houseService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public @ResponseBody ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public @ResponseBody ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public @ResponseBody ResponseEntity<List<HouseInfoDto>> apt(@RequestParam Map<String, Integer> filters
//																@RequestParam(value = "area", required = false) Integer area,
//																@RequestParam(value = "dealAmount", required = false) Integer dealAmount,
//													|			@RequestParam(value = "buildYear", required = false) Integer buildYear
	) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseService.getAptInDong(filters), HttpStatus.OK);
	}

	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<?> getAptDetail(@PathVariable BigInteger aptCode, @RequestParam(required = false) String kaptCode) throws Exception {
		HouseDetailResponseDto houseDetailResponseDto = new HouseDetailResponseDto();
		houseDetailResponseDto.setHouseInfo(houseService.getHouseInfo(aptCode));
		if (kaptCode != null) {
			houseDetailResponseDto.setHouseDetailInfo(openApiService.fetchHouseInfo(kaptCode));
		}
		houseDetailResponseDto.setHouseDealList(houseService.getHouseDealsByAptCode(aptCode));
		return ResponseEntity.ok().body(houseDetailResponseDto);
	}

	@GetMapping("/apt_list")
	public String aptList() {
		return "apt_list";
	}
	
}
