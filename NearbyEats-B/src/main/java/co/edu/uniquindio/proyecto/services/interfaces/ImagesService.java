package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.exceptions.image.UploadImageException;
import co.edu.uniquindio.proyecto.exceptions.image.deleteImageException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

public interface ImagesService {

    Map uploadImage(MultipartFile image) throws UploadImageException, IOException;

    Map deleteImage(String idImage) throws deleteImageException, IOException;
}
