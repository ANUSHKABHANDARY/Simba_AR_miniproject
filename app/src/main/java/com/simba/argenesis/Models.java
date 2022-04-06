package com.simba.argenesis;

public class Models {
    String Model_Name, Model_Image, Model_File, Model_Description, Model_Uid;

    public Models(String model_Name, String model_Image, String model_File, String model_Description, String model_Uid) {
        Model_Name = model_Name;
        Model_Image = model_Image;
        Model_File = model_File;
        Model_Description = model_Description;
        Model_Uid = model_Uid;

    }

    public Models(String model_Name, String model_Image) {
        Model_Name = model_Name;
        Model_Image = model_Image;
    }

    public Models(String model_Name, String model_Image, String model_File, String model_Description) {
        Model_Name = model_Name;
        Model_Image = model_Image;
        Model_File = model_File;
        Model_Description = model_Description;
    }

    public Models() {
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String model_Name) {
        Model_Name = model_Name;
    }

    public String getModel_Image() {
        return Model_Image;
    }

    public void setModel_Image(String model_Image) {
        Model_Image = model_Image;
    }

    public String getModel_File() {
        return Model_File;
    }

    public void setModel_File(String model_File) {
        Model_File = model_File;
    }

    public String getModel_Description() {
        return Model_Description;
    }

    public void setModel_Description(String model_Description) {
        Model_Description = model_Description;
    }

    public String getModel_Uid() {
        return Model_Uid;
    }

    public void setModel_Uid(String model_Uid) {
        Model_Uid = model_Uid;
    }
}
