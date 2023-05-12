package com.example.shorturlv3;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ShortUrlController {
    private final ShortUrlService shortUrlService; //servicio


    //Autowire del servicio
    @Autowired
    ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    //Post
    @PostMapping("/access/{user}")
    public ResponseEntity<String> newShortUrl(@PathVariable String user, HttpServletRequest contextPath) {
            String originalUrl = "/access/" + user; //guardo la url original
            //Esta es la variable que se "envia" al servicio para modelar la salida es el nombre del servidor + el puerto
            String contextPathstr = "https://" + contextPath.getServerName() +":"+ contextPath.getServerPort();
            ResponseEntity<String> shortURL = shortUrlService.createShortUrl(originalUrl, user ,contextPathstr);
            // Otras opciones aca se podria enviar en el @body una url larga tipo https://localhost:8080/algo/algo/algo,
            // incluso también el nombre de usuario..., pero quise mantener lo que entendi del ejercicio original
            return shortURL;
    }
    //Get usa ShortUrlResponse para modelar la salida
    @GetMapping("/{shortUrlId}")
    public ResponseEntity<ShortUrlResponse> getShortUrl(@PathVariable String shortUrlId) throws InterruptedException{
        return shortUrlService.findByShortUrlId(shortUrlId);
    }
}
