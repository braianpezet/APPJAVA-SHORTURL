package com.example.shorturlv3;

import lombok.Data;

import java.util.UUID;


//Esta clase seria como un DTO
@Data
public class ShortUrlResponse {
    private String user;
    private UUID uuid;
    private String datetime;
    private int version;
    private boolean enable;

    public ShortUrlResponse(String user, UUID uuid, String datetime, int version, boolean enable){
        this.user = user;
        this.uuid = uuid;
        this.datetime = datetime;
        this.version = version;
        this.enable = enable;
    }


}