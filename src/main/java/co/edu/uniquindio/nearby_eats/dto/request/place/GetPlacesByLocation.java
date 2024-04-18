package co.edu.uniquindio.nearby_eats.dto.request.place;

public record GetPlacesByLocation(
        String token,
        String location
) {
}
