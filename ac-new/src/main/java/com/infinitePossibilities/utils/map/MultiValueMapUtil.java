package com.infinitePossibilities.utils.map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class MultiValueMapUtil {

    public static void main(String[] args) {

        MultiValueMap <String ,String > map = new LinkedMultiValueMap<>();
        List list = new ArrayList();
        list.add("li");
        list.add("lis");
        map.put("user",list);
        map.add("user","zhang");
        System.out.println(map.get("user"));

    }



}
