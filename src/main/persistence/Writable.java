package persistence;

import org.json.JSONObject;

// SOURCE: FROM JSON LIBRAIRIES SAMPLE APPLICATION DEMO

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
