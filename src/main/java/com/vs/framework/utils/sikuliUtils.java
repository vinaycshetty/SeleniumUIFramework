package com.vs.framework.utils;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 * Created by : Vinay Shetty
 * on 26-03-2025 at 00:19
 **/
public class sikuliUtils {
  public static Screen screen;
  public static Pattern pattern;

  public static Screen getScreenObject(){
    return screen = new Screen();
  }

  public static Pattern getPatternImagePath(String imagePath){
    return pattern = new Pattern(imagePath);
  }
}
