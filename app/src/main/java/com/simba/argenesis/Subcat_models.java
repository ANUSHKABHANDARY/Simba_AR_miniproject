package com.simba.argenesis;

import android.widget.Button;

public class Subcat_models {
    String model_name;
    Integer model_img;
    Button bt;

    public Subcat_models(String model_name, Integer model_img) {
        this.model_name = model_name;
        this.model_img = model_img;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public Integer getModel_img() {
        return model_img;
    }

    public void setModel_img(Integer model_img) {
        this.model_img = model_img;
    }
}
