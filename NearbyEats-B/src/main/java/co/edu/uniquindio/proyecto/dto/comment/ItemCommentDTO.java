package co.edu.uniquindio.proyecto.dto.comment;

import co.edu.uniquindio.proyecto.model.documents.Comentary;

public record ItemCommentDTO(
        String id,
        String commentary,
        Comentary answer,
        String firstName,
        String lastName,
        String profilePhoto,
        String date,
        String score
) {
}
