package com.divide.experience.article.controllers;

import com.divide.experience.article.objects.transport.StaticSource;
import com.divide.experience.article.services.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@RestController()
@RequestMapping("/static")
public class StaticController {

    private StaticService staticService;

    /**
     * Gets static file by articleID.
     *
     * @param articleId id.
     * @param imageName name.
     * @return Image.
     */
    @GetMapping(value = "/s/{articleId}/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable int articleId,
                                           @PathVariable String imageName) {
        ResponseEntity responseEntity;
        HttpHeaders headers = new HttpHeaders();
        try {
            byte[] media = staticService.getArticleStaticSource(imageName, articleId);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //TODO: добавить /p/

    /**
     * Add article to server.
     * https://www.devglan.com/react-js/file-upload-react-spring-rest
     *
     * @param articleId id.
     * @param file      image.
     * @return result.
     */
    @PostMapping(value = "/p/{articleId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity addImage(@PathVariable int articleId,
                                   @RequestParam MultipartFile file) {
        ResponseEntity responseEntity;
        try {
            staticService.addArticleStaticSource(file.getBytes(), file.getOriginalFilename(), articleId);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/p")
    public ResponseEntity deleteImage(@RequestHeader String staticSource) {
        ResponseEntity responseEntity;
        try {
            staticService.deleteStaticSource(java.net.URLDecoder.decode(staticSource, StandardCharsets.UTF_8.name()));
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }

    @GetMapping(value = "/s/get/sources/{articleId}")
    public List<StaticSource> getAllStaticSourceForArticle(@PathVariable int articleId) {
        return staticService.getListStaticsFromArticle(articleId);
    }

    @Autowired
    public void setStaticService(StaticService staticService) {
        this.staticService = staticService;
    }
}
