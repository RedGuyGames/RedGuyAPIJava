package ru.redguy.redguyapi.math;

import org.json.JSONObject;
import ru.redguy.redguyapi.ApiError;
import ru.redguy.rednetworker.clients.http.ApacheFluentAPI;
import ru.redguy.rednetworker.clients.http.exceptions.OpenConnectionException;
import ru.redguy.rednetworker.clients.http.exceptions.URLException;

import java.util.HashMap;
import java.util.Map;

public class Math {

    private String token;
    private Map<String, String> options;
    private ApacheFluentAPI apacheFluentAPI;

    public Math(String token, Map<String, String> options) {
        this.options = options;
        this.token = token;
        apacheFluentAPI = new ApacheFluentAPI();
    }

    public int get(NumberLevels level, int number) throws OpenConnectionException, URLException, ApiError {
        String data = apacheFluentAPI.getString("https://api.redguy.ru/math/get/", new HashMap<String, Object>() {{
            put("token", token);
            put("v", options.get("v"));
            put("level",level.getLevel());
            put("number",String.valueOf(number));
        }});
        JSONObject json = new JSONObject(data);
        if (json.getInt("code") != 1) {
            throw new ApiError(json.getInt("code"),json.getString("comment"));
        } else {
            return json.getJSONObject("response").getInt("result");
        }
    }
}
