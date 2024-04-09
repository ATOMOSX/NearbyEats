package co.edu.uniquindio.nearby_eats.service.interfa;

import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

public interface ImageService {
    Map uploadImage(MultipartFile image) throws Exception;
    Map deleteImage(String imageId) throws Exception;
}
