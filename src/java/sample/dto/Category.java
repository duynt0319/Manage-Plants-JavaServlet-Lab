/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author Triệu
 */
public class Category {
    private int CateID;
    private String CateName;

    public Category() {
    }

    public Category(int CateID, String CateName) {
        this.CateID = CateID;
        this.CateName = CateName;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }
    
}
