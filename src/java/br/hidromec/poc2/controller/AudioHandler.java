package br.hidromec.poc2.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class AudioHandler{
  boolean play(){
      try {
          Runtime.getRuntime().exec("aplay /root/alarm.wav");
          return true;
      } catch (IOException ex) {
          Logger.getLogger(AudioHandler.class.getName()).log(Level.SEVERE, null, ex);
          return false;
      }
  }
}
