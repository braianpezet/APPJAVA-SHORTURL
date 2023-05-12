package com.example.shorturlv3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ShortURL {
    @Id
    @GeneratedValue
    private long id; //id
    private String originalURL; //variable que almacena la urloriginal
    private String shortURL; //variable que almacena la shortUrl
    private String user;
    private String datatime;
    private UUID uuid;
    private Boolean enable;
    private String shortUrlId; //Identificador de la shortUrl que luego se va usar para buscar cuando se haga el get
    private int delayTime; //Variable de tiempo en segundos antes de ponerse lenta la url - podria estar en otra clase
    private int version;

    public ShortURL(){}


    // creo que podria haber usado @AllArgsConstructor
    public ShortURL(String originalURL, String shortUrl , String user, String datatime, UUID uuid,Boolean enable,String shortUrlId, int delayTime , int version){
        this.originalURL = originalURL;
        this.shortURL = shortUrl;
        this.user = user;
        this.datatime =datatime;
        this.uuid = uuid;
        this.enable = enable;
        this.shortUrlId = shortUrlId;
        this.version = version;
        this.delayTime = delayTime;
    }

}
