package co.edu.uniquindio.nearby_eats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<String> findCities() {
        Query query = new Query();
        query.fields().include("nombre");

        List<Document> documents = mongoTemplate.find(query, Document.class, "cities");
        return documents.stream()
                .map(doc -> doc.getString("nombre"))
                .collect(Collectors.toList());
    }
}
