package com.smartart.server;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ArtBoard {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private String userId;
  private String artboardId;
  private String artboardName;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getArtboardId() {
    return artboardId;
  }

  public void setArtboardId(String artboardId) {
    this.artboardId = artboardId;
  }


  public String getArtboardName() {
    return artboardName;
  }

  public void setArtboardName(String artboardName) {
    this.artboardName = artboardName;
  }

}
