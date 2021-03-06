package ru.redguy.redguyapi.event;

import ru.redguy.redguyapi.ApiError;
import ru.redguy.redguyapi.ApiRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Event {

    private final Map<String, String> options;

    public Event(Map<String, String> options) {
        this.options = options;
    }

    public PlayerStats getStats(String nick) throws IOException, ApiError {
        return new PlayerStats(ApiRequest.mainGet("event/stats/get",this.options, new HashMap<String, Object>() {{
            put("nick",nick);
        }}));
    }

    public Coins coins() {
        return new Coins(this.options);
    }

    public Tokens tokens() {
        return new Tokens(this.options);
    }

    public Wins wins() {
        return new Wins(this.options);
    }

    public Kills kills() {
        return new Kills(this.options);
    }
}
