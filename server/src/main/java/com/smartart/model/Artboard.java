package com.smartart.model;


import javax.persistence.*;

/**
 * @author rwendell
 *
 * This is the model for our Artboard,  All methods are self explanatory.
 */
@Entity
public class Artboard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "ArtboardID")
    private Long artboardId;
    @Column(name = "ArtboardName")
    private String artboardName;
    @Column(name = "UserID")
    private Long userId;

    /*
    @Column(name = "IsPrivate")
    private long isPrivate;
    */


    public Long getArtboardId() {
        return artboardId;
    }

    public void setArtboardId(Long artboardId) {
        this.artboardId = artboardId;
    }


    public String getArtboardName() {
        return artboardName;
    }

    public void setArtboardName(String artboardName) {
        this.artboardName = artboardName;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    /*
    public long getIsPrivate(){
        return isPrivate;
    }

    public void setIsPrivate(Long isPrivate){
        this.isPrivate = isPrivate;
    }
    */

}