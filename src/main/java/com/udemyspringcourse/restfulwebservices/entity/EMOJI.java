package com.udemyspringcourse.restfulwebservices.entity;

import java.util.Map;

public enum EMOJI {

    LIKE("LIKE"),
    CELEBRATE("CELEBRATE"),
    LOVE("LOVE"),
    FUNNY("FUNNY");

    private static Map<Integer,EMOJI> emojiMap;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private EMOJI(String code){
        this.code = code;
    }

    public static EMOJI getEmoji(String code){
        for(EMOJI emoji: EMOJI.values()){
            if(emoji.getCode().equalsIgnoreCase(code)){
                return emoji;
            }
        }
        return null;
    }
}
