package br.hidromec.poc2.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class GpioHandler{
    /*
        System port mapping:
        - GPIO1 --> NAND_DATA04  --> GPIO2_IO04 ==> (2-1)*32 + 4   = 36
        - GPIO2 --> NAND_DATA05  --> GPIO2_IO05 ==> (2-1)*32 + 5   = 37
        - GPIO3 --> NAND_DATA06  --> GPIO2_IO06 ==> (2-1)*32 + 6   = 38
        - GPIO4 --> NAND_DATA07  --> GPIO2_IO07 ==> (2-1)*32 + 7   = 39
        - GPIO5 --> NAND_READY   --> GPIO6_IO10 ==> (6-1)*32 + 10 = 170
        - GPIO6 --> NAND_WP_B    --> GPIO6_IO09 ==> (6-1)*32 + 9   = 169
        - GPIO7 --> GPIO02       --> GPIO1_IO02 ==> (1-1)*32 + 2   = 2
        - GPIO8 --> GPIO06       --> GPIO1_IO06 ==> (1-1)*32 + 6   = 6
    
        Board port mapping:
        - GPIO1 --> P13
        - GPIO2 --> P14
        - GPIO3 --> P15
        - GPIO4 --> P16
        - GPIO5 --> P17
        - GPIO6 --> P18
        - GPIO7 --> P19
        - GPIO8 --> P20
    */
    static Integer GPIO1 = 36;
    static Integer GPIO2 = 37;
    static Integer GPIO3 = 38;
    static Integer GPIO4 = 39;
    static Integer GPIO5 = 170;
    static Integer GPIO6 = 169;
    static Integer GPIO7 = 2;
    static Integer GPIO8 = 6;
    
    boolean onOff(Integer gpio_index, boolean setUnset){
        String port = "";
        
        switch(gpio_index){
            case 1:
                port = Integer.toString(GPIO1);
                break;
            case 2:
                port = Integer.toString(GPIO2);
                break;
            case 3:
                port = Integer.toString(GPIO3);
                break;
            case 4:
                port = Integer.toString(GPIO4);
                break;
            case 5:
                port = Integer.toString(GPIO5);
                break;
            case 6:
                port = Integer.toString(GPIO6);
                break;
            case 7:
                port = Integer.toString(GPIO7);
                break;
            case 8:
                port = Integer.toString(GPIO8);
                break;
            default:
                return false;
        }
        
        try {
            Runtime.getRuntime().exec("echo" + port + " > /sys/class/gpio/export");
            Runtime.getRuntime().exec("echo out > /sys/class/gpio/gpio" + port +"/direction");
            
            if(setUnset){
                Runtime.getRuntime().exec("echo 1 > /sys/class/gpio/gpio" + port + "/value");
            } else{
                Runtime.getRuntime().exec("echo 0 > /sys/class/gpio/gpio" + port + "/value");
            }
            
            Runtime.getRuntime().exec("echo" + port + " > /sys/class/gpio/unexport");
        } catch (IOException ex) {
            Logger.getLogger(GpioHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
}
