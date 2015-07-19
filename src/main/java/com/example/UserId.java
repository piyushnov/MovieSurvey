package com.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by piagarwa on 7/17/2015.
 */
public class UserId {
    String uid;

    public String getId() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public JSONObject to() throws JSONException {
        String S= "token :"+String.valueOf(uid);
        JSONObject r = new JSONObject(S);
        return r;
    }
}
