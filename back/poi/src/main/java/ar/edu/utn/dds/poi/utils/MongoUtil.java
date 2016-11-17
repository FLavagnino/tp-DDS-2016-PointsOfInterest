package ar.edu.utn.dds.poi.utils;

import ar.edu.utn.dds.poi.domain.CGP;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.POI;
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
        POI poi = null;

        switch (document.getString("type")) {
            case "cgp":
                //poi = new CGP(
                        //document.getString("name"),
                        //new Coordenate(document.get("coordenate")))
                break;
            case "bank":
                break;
            case "bus_stop":
                break;
            case "shop":
                break;
        }

        return poi;
    }
}
