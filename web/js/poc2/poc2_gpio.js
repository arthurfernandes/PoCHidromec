/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Sends a gpio request to set or reset a bit*/
function gpio(id,set){
    var xmlhttp;
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHttp");
    }

    xmlhttp.onreadystatechange = function(){
        var innerText;
        if((xmlhttp.readyState === 4) && (xmlhttp.status === 200)){
            innerText = xmlhttp.responseText;
        }
        else if(xmlhttp.readyState === 3){
            
        }
        else if(xmlhttp.status === 404){
            innerText = "failed";
        }

        if(innerText.replace("\n","") == "failed"){
            window.alert("Um erro ocorreu ao processar sua requisição.");
        }
        else if(innerText.replace("\n","") == "success"){
            window.alert("Sucesso.");
        }
    };
    
    xmlhttp.open("GET","Poc2/GPIOController?gpio="+id+"&set="+set,true);
    
    xmlhttp.send();
}
