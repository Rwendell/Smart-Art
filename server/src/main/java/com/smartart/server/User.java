package com.smartart.server;

import javax.persistence.*;
/**
 * @author rwendell
 */
@Entity
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "UserID")
  private Long userId;


  private String username;
  private String hash;
  private String salt;
  private long admin;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }


  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }


  public long getAdmin() {
    return admin;
  }

  public void setAdmin(long admin) {
    this.admin = admin;
  }

}
