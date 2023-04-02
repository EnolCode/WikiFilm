package com.wikiFilm.services.Storage;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class FyleSistemStorageService implements StorageService {

    @Value("${media.location}") // Mapeamos la ruta del app.properties
    private String mediaLocation;

    private Path rootLocation; // Representar la ruta raiz de almacenamiento

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to storage");
            }
            String filename = file.getOriginalFilename(); // Obtenemos nombre original
            Path destinationFile = rootLocation.resolve(Paths.get(filename)) // Obtenemos su ruta de almacenamiento
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING); // Lo almacenamos usando
                                                                                               // la clase utilfiles a
                                                                                               // partir de su
                                                                                               // inputStream.
            }
            return filename; // el metodo retornara el nombre del archivo
        } catch (IOException e) {
            throw new RuntimeException("failed to store file", e);
        }

    }

    @Override
    public Resource loadAsResource(String filename) { // Con este metodo recuperamos un archivo a partir de su nombre
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename);
        }
    }

}
