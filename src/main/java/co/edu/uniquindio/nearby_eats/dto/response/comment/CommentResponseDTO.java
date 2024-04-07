package co.edu.uniquindio.nearby_eats.dto.response.comment;

import java.util.List;

public record CommentResponseDTO(
        String id,
        String date,
        String clientId,
        String text,
        Integer score,
        ReplyResponseDTO reply
) {
}
