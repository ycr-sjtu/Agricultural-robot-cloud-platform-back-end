package cn.edu.sjtu;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;

public class JSONTest {
    public static void main(String[] args) {
        String msg = "{\"lng\": 200, \"lat\": 30}";
        System.out.println(JSON.isValidObject(msg));
        try {
            JSONObject.parseObject(msg);
        } catch (JSONException e) {
            System.out.println("无法解析");
        }
        if (JSON.isValidObject(msg)) {
            System.out.println("valid");
            System.out.println(JSONObject.parseObject(msg).toJSONString());
        }
    }
}
