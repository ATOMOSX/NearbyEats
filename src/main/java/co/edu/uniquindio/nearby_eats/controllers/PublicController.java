package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.GetPlacesByCategoryDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.GetPlacesByLocation;
import co.edu.uniquindio.nearby_eats.dto.request.place.GetPlacesByNameDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.GetPlaceException;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final PlaceService placeService;

    @GetMapping("/get-place/by-location")
    private ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByLocation(@RequestParam String location,
                                                                                   @RequestParam String token) throws GetPlaceException {
        GetPlacesByLocation getPlacesByLocation = new GetPlacesByLocation(token, location);
        List<PlaceResponseDTO> places = placeService.getPlacesByLocation(getPlacesByLocation);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-category")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByCategory(@RequestParam String category,
                                                                                  @RequestParam String token) throws GetPlaceException {
        GetPlacesByCategoryDTO getPlacesByCategoryDTO = new GetPlacesByCategoryDTO(token, category);
        List<PlaceResponseDTO> places = placeService.getPlacesByCategory(getPlacesByCategoryDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-name")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByName(@RequestParam String name,
                                                                              @RequestParam String token) throws GetPlaceException {
        GetPlacesByNameDTO getPlacesByNameDTO = new GetPlacesByNameDTO(token, name);
        List<PlaceResponseDTO> places = placeService.getPlacesByName(getPlacesByNameDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }
}
