package com.infinitePossibilities.utils;

import java.net.InetAddress;

public class GetInetAddress {

    public static void main(String[] args) {

        try {
            String currentIpAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(currentIpAddress);
            String[] str = currentIpAddress.split("\\.");
            StringBuilder hardware = new StringBuilder();
            for (int i = 0; i < str.length; i++) {
                hardware.append(str[i]);
        }
        System.out.println(Long.parseLong(hardware.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
