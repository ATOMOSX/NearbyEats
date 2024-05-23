package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.*;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.*;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import co.edu.uniquindio.nearby_eats.service.interfa.SearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
@SecurityRequirement(name = "bearerAuth")
public class PlaceController {

    private final PlaceService placeService;
    private final SearchService searchService;

    @PostMapping("/create-place")
    public ResponseEntity<MessageDTO<String>> createPlace(@Valid @RequestBody PlaceCreateDTO placeCreateDTO) throws CreatePlaceException {
        placeService.createPlace(placeCreateDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "create place is successful"));
    }

    @PatchMapping("/update-place")
    public ResponseEntity<MessageDTO<String>> updatePlace(@Valid @RequestBody UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException {
        placeService.updatePlace(updatePlaceDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "update place is successful"));
    }

    @DeleteMapping("/delete-place")
    public ResponseEntity<MessageDTO<String>> deletePlace(@Valid @RequestBody DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException {
        placeService.deletePlace(deletePlaceDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "delete place is successful"));
    }

    @GetMapping("/get-place/{id}")
    public ResponseEntity<MessageDTO<PlaceResponseDTO>> getPlace(@PathVariable String id) throws GetPlaceException {
        PlaceResponseDTO place = placeService.getPlace(id);
        return ResponseEntity.ok().body(new MessageDTO<>(false, place));
    }

    @GetMapping("/get-place/by-category/{category}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByCategory(@PathVariable String category,
                                                                                  @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByCategoryDTO getPlacesByCategoryDTO = new GetPlacesByCategoryDTO(category);
        List<PlaceResponseDTO> places = placeService.getPlacesByCategory(getPlacesByCategoryDTO, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false,places ));
    }

    @GetMapping("/get-place/by-name/{name}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByName(@PathVariable String name,
                                                                              @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByNameDTO getPlacesByNameDTO = new GetPlacesByNameDTO(name);
        List<PlaceResponseDTO> places = placeService.getPlacesByName(getPlacesByNameDTO, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-status")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByStatus(@RequestParam String status,
                                                                                @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByStatusByClientDTO getPlacesByStatusByClientDTO = new GetPlacesByStatusByClientDTO(status);
        List<PlaceResponseDTO> places = placeService.getPlacesByStatus(getPlacesByStatusByClientDTO, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-status-mod/{status}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesMod(@PathVariable String status) throws GetPlaceException {
        List<PlaceResponseDTO> places = placeService.getPlacesMod(status);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-status-by-mod/{status}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByModerator(@PathVariable String status, @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        List<PlaceResponseDTO> places = placeService.getPlacesByModerator(status, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-user-id/{clientId}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByClientId(@PathVariable String clientId) throws GetPlaceException {
        List<PlaceResponseDTO> places = placeService.getPlacesByClientId(clientId);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    @GetMapping("/get-place/by-location")
    private ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByLocation(@RequestParam String location,
                                                                                   @RequestHeader Map<String, String> headers) throws GetPlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        GetPlacesByLocation getPlacesByLocation = new GetPlacesByLocation(location);
        List<PlaceResponseDTO> places = placeService.getPlacesByLocation(getPlacesByLocation, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, places));
    }

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    @PostMapping("/review-place")
    public ResponseEntity<MessageDTO<String>> reviewPlace(@Valid @RequestBody PlaceReviewDTO placeReviewDTO, @RequestHeader Map<String, String> headers) throws ReviewPlaceException, MessagingException, EmailServiceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        placeService.reviewPlace(placeReviewDTO, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, ""));
    }

    @GetMapping("/recommend-places/{userId}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> recommendPlaces(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok().body(new MessageDTO<>(false, searchService.recommendPlaces(userId)));
    }

    @GetMapping("/save/favorite/place/{placeId}")
    public ResponseEntity<MessageDTO<Place>> saveFavoritePlace(@Valid @PathVariable String placeId, @RequestHeader Map<String, String> headers) throws FavoritePlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        placeService.saveFavoritePlace(placeId, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.saveFavoritePlace(placeId, token)));
    }

    @GetMapping("/delete/favorite/place/{placeId}")
    public ResponseEntity<MessageDTO<Place>> deleteFavoritePlace(@Valid @PathVariable String placeId, @RequestHeader Map<String, String> headers) throws FavoritePlaceException {
        String token = headers.get("authorization").replace("Bearer ", "");
        placeService.deleteFavoritePlace(placeId, token);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.deleteFavoritePlace(placeId, token)));
    }

    @GetMapping("/get-favorite-place")
    public ResponseEntity<MessageDTO<Place>> getfavoritePlace(@Valid @RequestBody String idPlace) throws Exception {
        placeService.getFavoritePlace(idPlace);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getFavoritePlace(idPlace)));
    }
}
