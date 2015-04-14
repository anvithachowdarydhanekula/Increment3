package com.myproject.myhub.giveawayhub;

import android.app.Application;
import com.parse.Parse;

public class ParseApplication
  extends Application
{
  public void onCreate()
  {
    super.onCreate();
    Parse.initialize(this, "vwUqDiOPfxm4MlOKPfpdKPqs3HteDLEbcIwe9IHE", "I34owieSSThtO5sT7PDNZjSuOr1Gb5qGm7PhSi6r");
  }
}