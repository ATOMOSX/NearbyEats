package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.GetPlacesByCategoryDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.GetPlacesByLocation;
import co.edu.uniquindio.nearby_eats.dto.request.place.GetPlacesByNameDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.GetPlaceException;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final PlaceService placeService;

    @GetMapping("/get-place/by-location")
    private ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByLocation(@RequestParam String location,
                                                                                   @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByLocation getPlacesByLocation = new GetPlacesByLocation(location);
        List<PlaceResponseDTO> places = placeService.getPlacesByLocation(getPlacesByLocation, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-category")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByCategory(@RequestParam String category,
                                                                                  @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByCategoryDTO getPlacesByCategoryDTO = new GetPlacesByCategoryDTO(category);
        List<PlaceResponseDTO> places = placeService.getPlacesByCategory(getPlacesByCategoryDTO, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-name")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByName(@RequestParam String name,
                                                                              @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByNameDTO getPlacesByNameDTO = new GetPlacesByNameDTO(name);
        List<PlaceResponseDTO> places = placeService.getPlacesByName(getPlacesByNameDTO, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place-status")
    public ResponseEntity<MessageDTO<List<String>>> getPlacesStatus() {
        List<String> placeStatus = placeService.getPlaceStatus();
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeStatus));
    }
}
