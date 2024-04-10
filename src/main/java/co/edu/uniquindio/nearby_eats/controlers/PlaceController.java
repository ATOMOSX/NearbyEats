package co.edu.uniquindio.nearby_eats.controlers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.DeletePlaceDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.PlaceCreateDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.UpdatePlaceDTO;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.CreatePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.DeletePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.GetPlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.UpdatePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/create-place")
    public ResponseEntity<MessageDTO<String>> createPlace(@Valid @RequestBody PlaceCreateDTO placeCreateDTO) throws CreatePlaceException {
        placeService.createPlace(placeCreateDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "create place is successful"));
    }

    @PostMapping("/update-place")
    public ResponseEntity<MessageDTO<String>> updatePlace(@Valid @RequestBody UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException {
        placeService.updatePlace(updatePlaceDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "update place is successful"));
    }

    @PostMapping("/delete-place")
    public ResponseEntity<MessageDTO<String>> deletePlace(@Valid @RequestBody DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException {
        placeService.deletePlace(deletePlaceDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "delete place is successful"));
    }

    @PostMapping("/get-place/{id}")
    public ResponseEntity<MessageDTO<PlaceResponseDTO>> getPlace(@PathVariable String placeId) throws GetPlaceException {
        placeService.getPlace(placeId);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlace(placeId)));
    }

    @PostMapping("/get-place/by-category/category}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByCategory(@PathVariable String category) {
        placeService.getPlacesByCategory(category);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByCategory(category)));
    }

    @PostMapping("/get-place/by-status/{status}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByStatus(@PathVariable String status) {
        placeService.getPlacesByStatus(status);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByStatus(status)));
    }

    @PostMapping("/get-place/by-user-id/{clientId}")
    public ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByClientId(@PathVariable String clientId) throws GetPlaceException {
        placeService.getPlacesByClientId(clientId);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByClientId(clientId)));
    }

    @PostMapping("/get-place/by-location/{location}")
    private ResponseEntity<MessageDTO<List<PlaceResponseDTO>>> getPlacesByLocation(@PathVariable Location location) {
        placeService.getPlacesByLocation(location);
        return ResponseEntity.ok().body(new MessageDTO<>(false, placeService.getPlacesByLocation(location)));
    }

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    @PostMapping("/review-place")
    public ResponseEntity<MessageDTO<String>> reviewPlace(@Valid @RequestBody PlaceReviewDTO placeReviewDTO) throws ReviewPlaceException {
        placeService.reviewPlace(placeReviewDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, ""));
    }

}
