package com.campuspedia.campuspedia.response;

import com.campuspedia.campuspedia.model.Campus;
import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/17/2017.
 */

public class CampusResponse {
    @SerializedName("data")
    private Campus campus;

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
