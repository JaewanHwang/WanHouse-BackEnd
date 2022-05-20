package com.ssafy.happyhouse.model.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssafy.happyhouse.model.dto.HouseDetailInfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class OpenApiServiceImpl implements OpenApiService {

    @Value("${open-api-key}")
    private String openApiKey;

    @Override
    public HouseDetailInfoDto fetchHouseInfo(String kaptCode) throws IOException {

        System.out.println(openApiKey);
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusDtlInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + openApiKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("kaptCode", "UTF-8") + "=" + URLEncoder.encode(kaptCode, "UTF-8")); /*단지코드*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        Gson gson = new Gson();
        return gson.fromJson(gson.fromJson(sb.toString(), JsonObject.class).get("response").getAsJsonObject().get("body").getAsJsonObject().get("item").getAsJsonObject(), HouseDetailInfoDto.class);
    }

}
