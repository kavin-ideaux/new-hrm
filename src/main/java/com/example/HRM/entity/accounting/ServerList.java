package com.example.HRM.entity.accounting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "serverList")
public class ServerList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long serverListId;
  
  private String serverType;
  
  private int amount;
  
  public Long getServerListId() {
    return this.serverListId;
  }
  
  public void setServerListId(Long serverListId) {
    this.serverListId = serverListId;
  }
  
  public String getServerType() {
    return this.serverType;
  }
  
  public void setServerType(String serverType) {
    this.serverType = serverType;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
}
