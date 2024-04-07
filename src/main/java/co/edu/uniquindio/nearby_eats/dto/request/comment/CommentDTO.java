package co.edu.uniquindio.nearby_eats.dto.request.comment;

public record CommentDTO(
        String placeId,
        String clientId,
        String comment,
        Integer score
) {
}
