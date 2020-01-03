package com.lushihao.sharewe.util;

import net.sf.json.JSONObject;

import java.util.Map;

public class LSHResponseUtils {
    /**
     * 拼接返回信息
     *
     * @param flag
     * @param map
     * @return
     */
    public static String responseParam(boolean flag, Map<String, Object> map) {
        JSONObject back = new JSONObject();
        back.put("ifSuccess", flag);
        if (map != null) {
            for (String key : map.keySet()) {
                back.put(key, map.get(key));
            }
        }
        return back.toString();
    }

}
