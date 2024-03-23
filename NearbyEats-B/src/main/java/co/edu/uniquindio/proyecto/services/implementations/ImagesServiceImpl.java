package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.exceptions.image.UploadImageException;
import co.edu.uniquindio.proyecto.exceptions.image.deleteImageException;
import co.edu.uniquindio.proyecto.services.interfaces.ImagesService;
import com.cloudinary.Cloudinary;
import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ImagesServiceImpl implements ImagesService {

    private final Cloudinary cloudinary;

    public ImagesServiceImpl() {

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "SU_CLOUD_NAME");
        config.put("api_key", "SU_API_KEY");
        config.put("api_secret", "SU_API_SECRET");

        cloudinary = new Cloudinary(config);
    }
    
    @Override
    public Map uploadImage(Multipart image) throws UploadImageException {
        return null;
    }

    @Override
    public Map deleteImage(String idImage) throws deleteImageException {
        return null;
    }
}
