package com.example.catabase;

import java.util.ArrayList;

public class SearchResultBy_Name {
    public class Weight{

        public String metric;


        public String getMetric() {
            return metric;
        }
    }


    public void setId(String id) {
        this.id = id;
    }

    public String id;
    public String name;
    public String description;
    public String temperament;
    public String origin;
    public Weight weight;
    public String life_span;
    public String wikipedia_url;
    public int dog_friendly;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }
}
