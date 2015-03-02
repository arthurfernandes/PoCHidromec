/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Send message via button*/
function sendMessageDefault(){
    var labelText = document.getElementById("label").value;
    var messageText = document.getElementById("message").value;
    var encoding = 'ascii';
    if(document.getElementById("hexa").checked){
        encoding = 'hexa';
    }

    sendMessage(labelText,messageText,encoding);
}

/*Send message from table that contains save/delete mechanism*/
function sendMessageFromTable(el){
    while (el.parentNode && el.tagName.toLowerCase() !== 'tr') {
        el = el.parentNode;
    }
    var label = el.cells[1].innerHTML;
    var message = el.cells[2].innerHTML;
    var encoding = 'ascii';
        if(document.getElementById("hexa").checked){
            encoding = 'hexa';
        }
    sendMessage(label,message,encoding);
}

/*Send a message with the specified label, message and encoding*/
function sendMessage(label,message,encoding){
    
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

        document.getElementById("dado_enviado").value = innerText;
    };

    xmlhttp.open("GET","Poc2/ReceivedMessageController?label="+label+"&message="+message+"&encoding="+encoding,true);
    xmlhttp.send();
}
