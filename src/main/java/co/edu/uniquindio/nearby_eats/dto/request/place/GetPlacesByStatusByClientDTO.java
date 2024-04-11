package co.edu.uniquindio.nearby_eats.dto.request.place;

public record GetPlacesByStatusByClientDTO(
        String status,
        String clientId
) {
}
