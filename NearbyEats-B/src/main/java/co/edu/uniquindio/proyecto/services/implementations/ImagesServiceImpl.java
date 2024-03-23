package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.exceptions.image.UploadImageException;
import co.edu.uniquindio.proyecto.exceptions.image.deleteImageException;
import co.edu.uniquindio.proyecto.services.interfaces.ImagesService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ImagesServiceImpl implements ImagesService {

    private final Cloudinary cloudinary;

    public ImagesServiceImpl() {

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dzdswg3j5");
        config.put("api_key", "512362591958621");
        config.put("api_secret", "kwc-ePHWaBdJNXpcORZFxOHRHWQ");

        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map uploadImage(MultipartFile image) throws UploadImageException, IOException {
        File file = convert(image);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "NearbyEats"));
    }

    @Override
    public Map deleteImage(String idImage) throws deleteImageException, IOException {
        return cloudinary.uploader().destroy(idImage, ObjectUtils.emptyMap());
    }

    private File convert(MultipartFile image) throws IOException {

        File file = File.createTempFile(image.getOriginalFilename(), null);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();

        return file;
    }
}
