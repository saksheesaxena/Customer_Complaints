package com.example.admin.customer_complaints.library;

/**
 * Created by Sakshee on 12-Oct-15.
 */
public class CustomerDetails {

    //private variables
    int ID;
    String NAME;
    String MOBILENUMBER;
    String ADDRESS;
    String ULB;
    String GENDER;
    String EMAIL;

    public CustomerDetails(){}

    public CustomerDetails(int ID,String NAME, String MOBILENUMBER,String ADDRESS, String ULB, String GENDER, String EMAIL)
    {
        this.ID = ID;
        this.NAME = NAME;
        this.MOBILENUMBER = MOBILENUMBER;
        this.ADDRESS = ADDRESS;
        this.ULB = ULB;
        this.GENDER = GENDER;
        this.EMAIL = EMAIL;
    }

    public CustomerDetails(String NAME, String MOBILENUMBER,String ADDRESS, String ULB, String GENDER, String EMAIL)
    {

        this.NAME = NAME;
        this.MOBILENUMBER = MOBILENUMBER;
        this.ADDRESS = ADDRESS;
        this.ULB = ULB;
        this.GENDER = GENDER;
        this.EMAIL = EMAIL;
    }
    public int getID() {
        return ID;
    }
    public void setID(int id){
        this.ID = id;
    }

    public String getNAME() {
        return NAME;
    }
    public void setNAME(String name){
      this.NAME = name;
    }

    public String getMOBILENUMBER() {
        return MOBILENUMBER;
    }
    public void setMOBILENUMBER(String mob){
        this.MOBILENUMBER = mob;
    }
    public String getADDRESS() {
        return ADDRESS;
    }
    public void setADDRESS(String add){
        this.ADDRESS = add;
    }
    public String getULB() {
        return ULB;
    }
    public void setULB(String ulb){
        this.ULB = ulb;
    }
    public String getGENDER() {
        return GENDER;
    }
    public void setGENDER(String gender){
        this.GENDER = gender;
    }
    public String getEMAIL() {
        return EMAIL;
    }
    public void setEMAIL(String email){
        this.EMAIL = email;
    }

}
