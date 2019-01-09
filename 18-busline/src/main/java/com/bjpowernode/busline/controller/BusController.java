package com.bjpowernode.busline.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.busline.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * ClassName:BusController
 * Package:com.bjpowernode.busline.controller
 * Description:
 *
 * @Date:2018/11/14 15:58
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class BusController {
    @Value("${key}")
    private String key;

    @GetMapping("/")
    public String toIndex(){
        return "index";
    }


    @PostMapping("/inputtips")
    @ResponseBody
    public Object inputTips(@RequestParam("keywords") String keywords,
                            @RequestParam(value = "city",required = false) String city){
        String param = "?output=json&key=" + key +"&keywords=" + keywords + "&city=" + city;
        String jsonResult = HttpClientUtils.doGet("http://restapi.amap.com/v3/assistant/inputtips" + param);
        System.out.println(jsonResult);
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        Integer status = jsonObject.getInteger("status");
        List<String> resultList = new ArrayList<>();
        if (status == 1){
            JSONArray tips = jsonObject.getJSONArray("tips");
            for (int i = 0; i < tips.size() ; i++) {
                JSONObject tipObject = tips.getJSONObject(i);
                String district = tipObject.getString("district");
                String name = tipObject.getString("name");
                resultList.add(district + name);
            }
        } else {
            System.out.println("调用失败");
        }
        return resultList;
    }

    @GetMapping("/query")
    @ResponseBody
    public Object query(@RequestParam(value = "city",required = false) String city,
                        @RequestParam("origin") String origin,
                        @RequestParam("destination") String destination){
        String originLocParam = "?output=json&key=" + key +"&address=" + origin + "&city=" + city;
        String destinationLocParam = "?output=json&key=" + key +"&address=" + destination + "&city=" + city;


        String originJson = HttpClientUtils.doGet("http://restapi.amap.com/v3/geocode/geo" + originLocParam);
        String destinationJson = HttpClientUtils.doGet("http://restapi.amap.com/v3/geocode/geo" + destinationLocParam);
        JSONObject jsonObject1 = JSONObject.parseObject(originJson);
        Integer status = jsonObject1.getInteger("status");
        if (status == 1){
            JSONArray jsonArray = jsonObject1.getJSONArray("geocodes");
            for (int i = 0; i < jsonArray.size() ; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                origin = jsonObject.getString("location");
            }
        } else {
            System.out.println("调用失败");
            return "error1";
        }
        JSONObject jsonObject2 = JSONObject.parseObject(destinationJson);
        Integer status1 = jsonObject2.getInteger("status");
        if (status1 == 1){
            JSONArray jsonArray = jsonObject2.getJSONArray("geocodes");
            for (int i = 0; i < jsonArray.size() ; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                destination = jsonObject.getString("location");
            }
        } else {
            System.out.println("调用失败");
            return "error2";
        }


        Map<String,Object> resultMap = new LinkedHashMap<>();
        String param = "?output=json&key=" + key +"&origin=" + origin + "&destination=" + destination +  "&city=" + city;
        String resultJson = HttpClientUtils.doGet("https://restapi.amap.com/v3/direction/transit/integrated" + param);
        JSONObject parseObject = JSONObject.parseObject(resultJson);
        Integer status2 = parseObject.getInteger("status");
        if (status2 == 1){
            JSONObject routeObject = parseObject.getJSONObject("route");
            String distance = routeObject.getString("distance");
            String taxiCost = routeObject.getString("taxi_cost");
            resultMap.put("distance", distance + "m");
            resultMap.put("taxiCost", taxiCost + "rmb");

            //换乘列表
            JSONArray transits = routeObject.getJSONArray("transits");
            for (int i = 0; i < transits.size() ; i++) {
                JSONObject transitsJSONObject = transits.getJSONObject(i);
                List<Map<String, Object>> way = new ArrayList<>();
                //具体换乘方案
                JSONArray segments = transitsJSONObject.getJSONArray("segments");
                for (int j = 0; j < segments.size(); j++) {
                    //换乘路线列表
                    JSONObject segmentsJSONObject = segments.getJSONObject(j);
                    JSONObject busObject = segmentsJSONObject.getJSONObject("bus");
                    JSONArray buslines = busObject.getJSONArray("buslines");
                    Map<String, Object> wayMap = new LinkedHashMap<>();
                    for (int k = 0; k < buslines.size() ; k++) {
                        JSONObject jsonObject = buslines.getJSONObject(k);
                        JSONObject departure_stop = jsonObject.getJSONObject("departure_stop");
                        JSONObject arrival_stop = jsonObject.getJSONObject("arrival_stop");
                        wayMap.put("departure_stop" + (k + 1), departure_stop.getString("name"));
                        wayMap.put("arrival_stop"+ (k + 1), arrival_stop.getString("name"));
                        wayMap.put("name" + (k + 1),jsonObject.getString("name"));
                        way.add(wayMap);
                    }
                }
                resultMap.put("way"+ (i+1), way);

            }
        }


        return resultMap;
    }
}
