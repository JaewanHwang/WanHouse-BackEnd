package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.HouseInfoResponse;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.OpenApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/house")
public class HouseController {

	private HouseService houseService;
	private OpenApiService openApiService;

	@Autowired
	public void setHouseService(HouseService houseService) {
		this.houseService = houseService;
	}
	@Autowired
	public void setOpenApiService(OpenApiService openApiService) {
		this.openApiService = openApiService;
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

	@GetMapping("/apt")
	public  ResponseEntity<List<HouseInfoDto>> apt(@RequestParam Map<String, Integer> filters
//																@RequestParam(value = "area", required = false) Integer area,
//																@RequestParam(value = "dealAmount", required = false) Integer dealAmount,
//													|			@RequestParam(value = "buildYear", required = false) Integer buildYear
	) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseService.getAptInDong(filters), HttpStatus.OK);
	}

	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<?> getAptDetail(@PathVariable BigInteger aptCode) throws Exception {
		HouseInfoResponse houseInfoResponse = new HouseInfoResponse();
		houseInfoResponse.setHouseInfo(houseService.getHouseInfo(aptCode));
		if (houseInfoResponse.getHouseInfo().getKaptCode() != null) {
			houseInfoResponse.setHouseDetailInfo(openApiService.fetchHouseInfo(houseInfoResponse.getHouseInfo().getKaptCode() ));
		}
		houseInfoResponse.setHouseDealList(houseService.getHouseDealsByAptCode(aptCode));
		return ResponseEntity.ok().body(houseInfoResponse);
	}

}

