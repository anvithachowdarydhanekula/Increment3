package com.myproject.myhub.giveawayhub;

public class ParseData
{
  private String id;
  private byte[] imageBytes;
  private String receiveItemLocation;
  private String receiveItemName;
  
  public String getId()
  {
    return this.id;
  }
  
  public byte[] getImageBytes()
  {
    return this.imageBytes;
  }
  
  public String getReceiveItemLocation()
  {
    return this.receiveItemLocation;
  }
  
  public String getReceiveItemName()
  {
    return this.receiveItemName;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setImageBytes(byte[] paramArrayOfByte)
  {
    this.imageBytes = paramArrayOfByte;
  }
  
  public void setReceiveItemLocation(String paramString)
  {
    this.receiveItemLocation = paramString;
  }
  
  public void setReceiveItemName(String paramString)
  {
    this.receiveItemName = paramString;
  }
}