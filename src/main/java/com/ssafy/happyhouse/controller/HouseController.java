package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	private HouseService houseService;
	
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
	public String getAptDetail(@PathVariable int aptCode, Model model) throws Exception {
		model.addAttribute("houseInfo", houseService.getHouseInfo(aptCode));
		model.addAttribute("houseDeals", houseService.getHouseDealsByAptCode(aptCode));
		return "apt_detail";
	}

	@GetMapping("/apt_list")
	public String aptList() {
		return "apt_list";
	}
	
}
