package com.divide.experience.article.services.impl;

import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.transport.StaticSource;
import com.divide.experience.article.objects.transport.TypeOfStaticSource;
import com.divide.experience.article.services.StaticResourceStrategy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Service
public class ServerStaticResourceStrategy implements StaticResourceStrategy {

    // 6 MByte
    private int MAX_FILE_SIZE = 6291456;
    private int MAX_FILES_FOR_ARTICLE = 7;

    private static final String base_uri = "article/static/s/%s/%s";
    private static final String revert_line = "article/static/s/%s/";
    private static final String base_path = "C:/Users/user/Desktop/divide_exp/%s/%s/%s";
    private static final String pattern = "MM_yyyy";

    @Override
    public String generateUri(String nameSource, ArticleModel articleModel) {
        return String.format(base_uri, articleModel.getId().toString(), nameSource);
    }

    @Override
    public void addArticleStaticSource(byte[] source, String nameSource, ArticleModel articleModel) throws IOException {
        if (source.length < MAX_FILE_SIZE) {
            String pathDir = generatePathDir(articleModel);
            File dir = new File(pathDir);
            dir.mkdirs();
            if (dir.list().length < MAX_FILES_FOR_ARTICLE && dir.exists()) {
                Files.write(new File(pathDir + "/" + nameSource).toPath(), source);
            }
        }
    }

    @Override
    public void deleteStaticSource(String nameSource, ArticleModel articleModel) {
        String path = generatePathFromLink(articleModel, nameSource);
        File file = new File(path);
        file.delete();
    }

    @Override
    public byte[] getArticleStaticSource(String nameSource, ArticleModel articleModel) throws IOException {
        String path = generatePathDir(articleModel) + "/" + nameSource;
        File file = new File(path);
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public List<StaticSource> getListStaticsFromArticle(ArticleModel articleModel) {
        ArrayList<StaticSource> statics = new ArrayList<>();
        File dir = new File(generatePathDir(articleModel));
        if (dir.exists()) {
            String[] files = dir.list();
            for (String f : files) {
                StaticSource st = new StaticSource();
                st.setName(f);
                st.setType(TypeOfStaticSource.IMAGE);
                st.setUri(generateUri(f, articleModel));
                statics.add(st);
            }
        }
        return statics;
    }

    //  Автор/месяц с годом/номер статьи
    private String generatePathDir(ArticleModel articleModel) {
        LocalDate localDate = articleModel.getDate().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String date = localDate.format(formatter);
        return String.format(base_path,
                articleModel.getAuthorModel().getId(),
                date,
                articleModel.getId().toString());
    }


    private String generatePathFromLink(ArticleModel articleModel, String link) {
        String path = generatePathDir(articleModel);
        String[] s = link.split(String.format(revert_line, articleModel.getId()));
        return path + "/" + s[1];
    }
}
