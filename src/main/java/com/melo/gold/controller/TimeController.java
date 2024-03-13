package com.melo.gold.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RequestMapping("/test")
public class TimeController {


    public void test (){

    }

    public static void main(String[] args) throws InterruptedException {

        HttpURLConnection connection = null;

      //  String url = "http://beijingrtj.com/admin/get_price5.php?&t=1709528169119";
        try {
            while (true) {

                long timestamp = System.currentTimeMillis();
                System.out.println("当前时间戳: " + timestamp);
                String url = "http://beijingrtj.com/admin/get_price5.php?&t="+timestamp;
                // 创建URL对象
                URL apiUrl = new URL(url);

                // 打开连接
                connection = (HttpURLConnection) apiUrl.openConnection();

                // 设置请求方法
                connection.setRequestMethod("POST");

                // 获取响应码
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 读取响应内容
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    // 输出响应内容
                    System.out.println("响应内容：");
                    System.out.println(response.toString());
                } else {
                    System.out.println("请求失败，响应码：" + responseCode);
                }

                // 等待1秒
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect(); // 关闭连接
            }
        }


    }

}
