package co.edu.uniquindio.proyecto.modelo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Categorias")
public enum Category {

    RESTAURANT,
    CAFE,
    FASTFOOD,
    MUSEUM,
    HOTEL
}
