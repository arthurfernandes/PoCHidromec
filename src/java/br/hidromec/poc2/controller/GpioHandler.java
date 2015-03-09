package br.hidromec.poc2.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class GpioHandler{
    boolean onOff(Integer gpio_index, boolean setUnset){
        try {
            // gpio <gpio number> <1:set 0:unset>            
            if(setUnset){
                Runtime.getRuntime().exec("/bin/gpio " + gpio_index + " 1");
                System.out.println("gpio " + gpio_index + " set");
            } else{
                Runtime.getRuntime().exec("/bin/gpio " + gpio_index + " 0");
                System.out.println("gpio " + gpio_index + " unset");
            }
        } catch (IOException ex) {
            Logger.getLogger(GpioHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
}
