package com.divide.experience.article.controllers;

import com.divide.experience.article.objects.transport.StaticSource;
import com.divide.experience.article.services.StaticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RestController
@Api(value = "Contains operations with static sources.", tags = "StaticController")
@RequestMapping("/article/static")
public class StaticController {

    private StaticService staticService;

    @ApiOperation(value = "Gets an image.",
            notes = "Returns bytes array.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Gets the image"),
            @ApiResponse(code = 404, message = "The image not found")})
    @GetMapping(value = "/s/{articleId}/{imageName}")
    public ResponseEntity<byte[]> getImage(@ApiParam(value = "The id of the article.",
                                                     allowableValues = "range[1, infinity]", required = true)
                                           @PathVariable int articleId,
                                           @ApiParam(value = "The name of the image.", example = "Cats.jpg", required = true)
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

    //https://www.devglan.com/react-js/file-upload-react-spring-rest
    @ApiOperation(value = "Adds a new image to the server.",
            notes = "You can add only 5 images. Images are saved on different services (online or not online).",
            tags = "protect_resource")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Adding is successful"),
            @ApiResponse(code = 503, message = "Can't add the image. Some errors are happened. SERVICE_UNAVAILABLE.")})
    @PostMapping(value = "/p/{articleId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity addImage(@ApiParam(value = "The id of the article.", allowableValues = "range[1, infinity]", required = true)
                                   @PathVariable int articleId,
                                   @ApiParam(value = "The container of the image (or other media files).", required = true)
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

    @ApiOperation(value = "Deletes an image.",
            notes = "Deletes the static resource only from not published article. "
                    + "Only the author of article can delete the image by this endpoint.",
            tags = "protect_resource")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deleting is successful"),
            @ApiResponse(code = 503, message = "Can't delete the image. Some errors are happened. SERVICE_UNAVAILABLE.")})
    @DeleteMapping(value = "/p")
    public ResponseEntity deleteImage(@ApiParam(value = "The container of the image (or other media files).", required = true)
                                      @RequestHeader String staticSource) {
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

    @ApiOperation(value = "Gets a list of the image DTOs.",
            response = StaticSource.class,
            responseContainer = "List")
    @GetMapping(value = "/s/get/sources/{articleId}")
    public List<StaticSource> getAllStaticSourceForArticle(@ApiParam(value = "The id of the article.",
                                                                        allowableValues = "range[1, infinity]", required = true)
                                                           @PathVariable int articleId) {
        return staticService.getListStaticsFromArticle(articleId);
    }

    @Autowired
    public void setStaticService(StaticService staticService) {
        this.staticService = staticService;
    }
}
