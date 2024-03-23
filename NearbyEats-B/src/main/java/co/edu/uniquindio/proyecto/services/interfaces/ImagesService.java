package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.exceptions.image.UploadImageException;
import co.edu.uniquindio.proyecto.exceptions.image.deleteImageException;
import jakarta.mail.Multipart;

import java.util.Map;

public interface ImagesService {

    Map uploadImage(Multipart image) throws UploadImageException;

    Map deleteImage(String idImage) throws deleteImageException;
}
