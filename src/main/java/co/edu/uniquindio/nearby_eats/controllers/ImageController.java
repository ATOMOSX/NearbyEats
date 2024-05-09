package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.image.ImageDTO;
import co.edu.uniquindio.nearby_eats.service.interfa.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<MessageDTO<Map>> uploadImage(@RequestParam("file") MultipartFile image) throws Exception{
        Map response = imageService.uploadImage(image);
        return ResponseEntity.ok().body(new MessageDTO<>(false, response));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageDTO<Map>> deleteImage(ImageDTO imageDTO) throws Exception{
        Map response = imageService.deleteImage(imageDTO.idImage());
        return ResponseEntity.ok().body(new MessageDTO<>(false, response));
    }

}

