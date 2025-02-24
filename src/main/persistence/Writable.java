package persistence;

import org.json.JSONObject;

// SOURCE: FROM JSON LIBRAIRIES SAMPLE APPLICATION

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
