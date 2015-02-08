<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Hidromec</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/index.css" rel="stylesheet"/>
        
    </head>
    <body>
        <div id="wrapper">
        </div>
        
        <div class="title_text">
            <h1>Hidromec</h1>
        </div>
        
        <div class="container-fluid panel-body formularioPrincipal center-block">
            
            <div class="command_div">
                <label class="big_text_box">Criar / Enviar Mensagem</label>
                <br/>
                <div>
                    <label class="big_text_box">Label</label>
                    <textarea class="input_text" id="label"></textarea> 
                    <div class="radio-inline">
                        <label class=""><input type="radio" name="encoding" checked="checked" id="ascii">ASCII</label>
                    </div>
                    <div class="radio-inline">
                      <label><input type="radio" name="encoding" id="hexa">HEXA</label>
                    </div>
                </div>
                <br>
                <div>
                    <label class="big_text_box">Message</label>
                    <textarea class="input_text" id="message"></textarea> 
                    <button type="button" class="btn btn-lg" id="save_message" onclick="saveMessage()">Save</button>
                    <button type="button" class="btn btn-lg" id="send_message" onclick="sendMessageDefault()">Send</button>
                </div>
                <br>
                
                
            </div>
            <div class="table-responsive response_div">
                <label class="big_text_box">Mensagens Salvas</label>
                <table class="table table-bordered table_of_messages" id="saved_messages_table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>LABEL</th>
                            <th>MESSAGES</th>
                            <th>DELETE</th>
                            <th>SEND</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
               
            <div class="response_div">
                <label class="big_text_box">Dado Enviado</label><br/>
                <textarea class="input-sm input_text input_text_resposta" id="dado_enviado"></textarea>
            </div>
            
            <div class="response_div">
                <label class="big_text_box">Dado Recebido</label><br/>
                <textarea class="input-sm input_text input_text_resposta" id="dado_recebido"></textarea>
                <br>
                <button class="btn btn-lg">Clear Buffer</button>
            </div>
            
            <div class="response_div other_functions">
                <div class="audio_div">
                    <label class="big_text_box">Audio</label><br>
                    <button class="btn btn-lg">Play</button>
                </div>
                <div class="gpio_div">
                    <label class="big_text_box">GPIO</label><br>
                    <c:forEach var="i" begin="1" end="8">
                        <label class="big_text_box">${i}</label>
                        <button type="button" class="btn btn-lg">ON</button>
                        <button type="button" class="btn btn-lg">OFF</button>
                        <br/>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <script type="text/javascript">
            
            function sendMessageFromTable(el){
                while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
                    el = el.parentNode;
                }
                var label = el.cells[1].innerHTML;
                var message = el.cells[2].innerHTML;
                var encoding = 'ascii';
                    if(document.getElementById("hexa").checked){
                        encoding = 'hexa';
                    }
                sendMessage(label,message,encoding)
            }
            
            function deleteMessage(el){
                while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
                    el = el.parentNode;
                }
                el.parentNode.removeChild(el);
                var table = document.getElementById('saved_messages_table').getElementsByTagName('tbody')[0];
                updateMessageNumber(table);
            }
            
            function saveMessage(){
                var table = document.getElementById('saved_messages_table').getElementsByTagName('tbody')[0];
                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var rowId = table.rows.length;
                updateMessageNumber(table)
                cell1.innerHTML = rowId;
                cell2.innerHTML = document.getElementById('label').value;
                cell3.innerHTML = document.getElementById('message').value;
                cell4.innerHTML = '<td><button class="btn btn-sm" onclick="deleteMessage(this)">DEL</button></td>';
                cell5.innerHTML = '<td><button class="btn btn-sm" onclick="sendMessageFromTable(this)" id="send_message_'+(rowId-1)+'">SEND</button></td>';
            }
            
            function updateMessageNumber(table){
                for(var i = 0; i<table.rows.length;i++){
                    var row = table.rows[i];
                    row.cells[0].innerHTML = i+1;
                }
            }
        </script>
        
        <script type="text/javascript">
            
                function sendMessageDefault(){
                    var labelText = document.getElementById("label").value;
                    var messageText = document.getElementById("message").value;
                    var encoding = 'ascii';
                    if(document.getElementById("hexa").checked){
                        encoding = 'hexa';
                    }
                    
                    sendMessage(labelText,messageText,encoding)
                }
                
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
                        if((xmlhttp.readyState == 4) && (xmlhttp.status == 200)){
                            innerText = xmlhttp.responseText;
                            
                        }
                        else if(xmlhttp.readyState == 3){
                            innerText = "Processando sua requisição.";
                            
                        }
                        else if(xmlhttp.status == 404){
                            innerText = "Erro ao processar sua requisição.";
                        }
                        
                        document.getElementById("dado_enviado").value = innerText;
                    };
                    
                    xmlhttp.open("GET","SendMessageController?label="+label+"&message="+message+"&encoding="+encoding,true);
                    xmlhttp.send();
                }
                
                
         </script>
         
         <script>
             
             function loadData(){
                    var xmlhttp;
                    if(window.XMLHttpRequest){
                        xmlhttp = new XMLHttpRequest();
                    }
                    else{
                        xmlhttp = new ActiveXObject("Microsoft.XMLHttp");
                    }


                    xmlhttp.onreadystatechange = function(){
                        var innerText;
                        if((xmlhttp.readyState == 4) && (xmlhttp.status == 200)){
                            innerText = xmlhttp.responseText;
                            
                        }
                        else if(xmlhttp.readyState == 3){
                            innerText = "Processando sua requisição.";
                            
                        }
                        else if(xmlhttp.status == 404){
                            innerText = "Erro ao processar sua requisição.";
                        }
                        
                        document.getElementById("dado_recebido").value = innerText;
                    };
                    
                    xmlhttp.open("GET","SendCommandController",true);
                    xmlhttp.send();
                }
                
                window.setInterval(loadData,100);
         </script>
        
    </body>
</html>
