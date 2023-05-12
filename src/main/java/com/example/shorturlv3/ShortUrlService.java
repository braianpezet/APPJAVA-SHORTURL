package com.example.shorturlv3;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;


@Service
public class ShortUrlService {
    private final CurrentTimeService currentTimeService = new CurrentTimeService(); //variable para usar el servicio de worltime
    private final ShortUrlRepository repository; //repositorio

    @Autowired
    ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.repository = shortUrlRepository;
    } //autowire del repositorio



    //createShortUrl es el servicio que responde al post
    //Crea un urlCorta con el formato input que indica el ejercicio
    public ResponseEntity<String> createShortUrl(String originalUrl, String user, String contextpath){
        String dataTime =  currentTimeService.getCurrentTime(); //tomo la fecha actual
        UUID uuid = UUID.randomUUID(); //genero un uuid random
        //Este codigo es para generar el identificador del link corto va a tener una forma similar a esta f7b58250
        String shortUrlid = uuid.toString().replaceAll("-", "").substring(0, 8);
        //Esto es para el output tenga el formato deseado toma el contextpath que esta definido en el controlador que seria
        //Raiz + puerto y le agrega el identificador de la shorturl
        String shortUrl = contextpath + "/" + shortUrlid;
        //Creo una ShortUrl seteo manualmente el delaytime a 60 segundos
        ShortURL shortURL1 = new ShortURL(originalUrl,shortUrl,user,dataTime,uuid,true,shortUrlid,60,1);
        //La guardo en la base de datos
        repository.save(shortURL1);
        //envio el formato de salida como se ve en el ejercicio
        return ResponseEntity.ok().body(shortUrl);
    }

    //Este codigo corresponde al GET de la URL corta
    public ResponseEntity<ShortUrlResponse> findByShortUrlId(String shorUrlId) throws InterruptedException{
        //Busca el shortUrl a traves de su identificador ejemplo  http://localhost:7070/f7b58250
        ShortURL url= repository.findByShortUrlId(shorUrlId);
        //Usa ShortUrlResponse que es solamente una clase para modelar los datos de salida y enviar solo lo que interesa
        ShortUrlResponse shortUrlResponse= new ShortUrlResponse(url.getUser(),url.getUuid(),url.getDatatime(),url.getVersion(),url.getEnable());
        //Tomo el tiempo actual
        String dataTimeActual = currentTimeService.getCurrentTime();
        //System.out.println("Data time url" + url.getDatatime());
        //System.out.println("Data time actual" + dataTimeActual);

        //Pareseo el tiempo actual y el de la url para usar Duration
        LocalDateTime actual = LocalDateTime.parse(dataTimeActual);
        LocalDateTime viejo = LocalDateTime.parse(url.getDatatime());
        //compara las duraciones
        Duration duration  = Duration.between(viejo,actual);
        long seconds = duration.getSeconds(); //lo pongo en segundos
        //System.out.println("La diferencia es:" + seconds);
        //System.out.println(url.getDelayTime());
        //Si la diferencia es mayor a lo que se definio en el delay añado un retraso
        if(seconds >= url.getDelayTime()){
            Thread.sleep(2000);
        }
        return ResponseEntity.ok().body(shortUrlResponse);
    }
}
