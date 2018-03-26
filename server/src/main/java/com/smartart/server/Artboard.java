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
  private Long artboardId;
  @Column(name = "ArtboardName")
  private String artboardName;
  @Column(name = "UserID")
  private Long userId;


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

}