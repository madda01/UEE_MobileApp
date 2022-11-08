package com.example.panthera.specialist;

public class TestMethods {

    //test method to validate the length of the animal name and the scientific name
    public boolean validateName(String Name, String ScieName) {
        boolean isTrue = false;
        if (Name.length() <= 3 || ScieName.length() <= 3) {
            return true;
        }
        return true;
    }

    //method to validate null inputs
    public boolean nullDetails(String Name, String ScieName, String Desc) {
        boolean isNull = false;

        if (Name.equals("") || ScieName.equals("") || Desc.equals("")) {
            return isNull;
        }
        return true;
    }
}
