package com.smartart.server;


import javax.persistence.*;
/**
 * @author rwendell
 */
@Entity
public class Artboard {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "ArtboardID")
  private String artboardId;
  @Column(name = "ArtboardName")
  private String artboardName;
  @Column(name = "UserID")
  private String userId;


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


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}