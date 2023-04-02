package com.wikiFilm.services.Storage;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
    void init() throws IOException; // Metodo auxiliar para preparar todo lo necesario
    String store(MultipartFile file); // Metodo para almacenar archivo
    Resource loadAsResource(String filename); // Metodo para cargar archivo
}
