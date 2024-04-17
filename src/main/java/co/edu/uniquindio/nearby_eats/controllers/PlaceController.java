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
    public ResponseEntity<MessageDTO<PlaceResponseDTO>> getPlace(@PathVariable String placeId) throws GetPlaceException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlace(placeId)));
    }

    @GetMapping("/get-place/by-category/{category}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByCategory(@PathVariable PlaceCategory category) throws GetPlaceException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByCategory(category)));
    }

    @GetMapping("/get-place/by-status")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByStatus(@Valid @RequestBody GetPlacesByStatusByClientDTO getPlacesByStatusByClientDTO) throws GetPlaceException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByStatus(getPlacesByStatusByClientDTO)));
    }

    @GetMapping("/get-place/by-user-id/{clientId}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByClientId(@PathVariable String clientId) throws GetPlaceException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByClientId(clientId)));
    }

    @GetMapping("/get-place/by-location/{location}")
    private ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByLocation(@PathVariable Location location) throws GetPlaceException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByLocation(location)));
    }

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    @PostMapping("/review-place")
    public ResponseEntity<MessageDTO<String>> reviewPlace(@Valid @RequestBody PlaceReviewDTO placeReviewDTO) throws ReviewPlaceException, MessagingException, EmailServiceException {
        placeService.reviewPlace(placeReviewDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, ""));
    }

    @GetMapping("/recommend-places/{userId}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> recommendPlaces(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok().body(new MessageDTO<>(false, searchService.recommendPlaces(userId)));
    }

    @GetMapping("/save/favorite/place")
    public ResponseEntity<MessageDTO<Place>> saveFavoritePlace(@Valid @RequestBody FavoritePlaceDTO favoritePlaceDTO) throws FavoritePlaceException {
        placeService.saveFavoritePlace(favoritePlaceDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.saveFavoritePlace(favoritePlaceDTO)))
    }

    @GetMapping("/delete/favorite/place")
    public ResponseEntity<MessageDTO<Place>> deleteFavoritePlace(@Valid @RequestBody FavoritePlaceDTO deleteFavoritePlaceDTO) throws FavoritePlaceException {
        placeService.deleteFavoritePlace(deleteFavoritePlaceDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.deleteFavoritePlace(deleteFavoritePlaceDTO)));
    }
}
