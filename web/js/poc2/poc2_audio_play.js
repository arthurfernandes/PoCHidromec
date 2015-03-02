/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function audio_play(){
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
            innerText = "Processando sua requisição.";

        }
        else if(xmlhttp.status === 404){
            innerText = "Erro ao processar sua requisição.";
        }

        document.getElementById("dado_recebido").value = innerText;
    };

    xmlhttp.open("GET","Poc2/AudioPlayController",true);
    xmlhttp.send();
}
