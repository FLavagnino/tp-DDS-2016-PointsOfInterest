package ar.edu.utn.dds.poi.utils;

import ar.edu.utn.dds.poi.domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoUtil {

    public boolean existPoi(String name) {
        return MongoDB.getInstance().getMongoDatabase()
                .getCollection("external_pois").find(new Document("name", name))
                .first() != null;
    }

    public void savePoi(POI poi) {
        JsonFactory jsonFactory = new JsonFactory();
        MongoDB.getInstance().getMongoDatabase().getCollection("external_pois").insertOne(
                Document.parse(jsonFactory.toJson(poi)));
    }

    public void savePois(List<POI> pois) {
        JsonFactory jsonFactory = new JsonFactory();

        List<Document> documents = new ArrayList<>();
        for(POI poi : pois) {
            documents.add(Document.parse(jsonFactory.toJson(poi)));
        }

        MongoDB.getInstance().getMongoDatabase().getCollection("external_pois").insertMany(documents);
    }

    public List<POI> getPois(String filter) {
        List<POI> pois = new ArrayList<>();

        for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("external_pois")
                .find(Document.parse("{name: /" + filter + "/}"))) {
            pois.add(documentToPOI(document));
        }

        return pois;
    }

    private POI documentToPOI(Document document) {
        JsonFactory jsonFactory = new JsonFactory();

        switch (document.getString("type")) {
            case "cgp":
                return jsonFactory.fromJson(document.toJson(), new TypeReference<CGP>() {});
            case "bank":
                return jsonFactory.fromJson(document.toJson(), new TypeReference<Bank>() {});
            case "bus_stop":
                return jsonFactory.fromJson(document.toJson(), new TypeReference<BusStop>() {});
            case "shop":
                return jsonFactory.fromJson(document.toJson(), new TypeReference<Shop>() {});
            default:
                return null;
        }
    }
}
