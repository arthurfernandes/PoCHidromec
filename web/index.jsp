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
                    <textarea class="input_text" id="label" rows="1"></textarea> 
                    <div class="radio-inline">
                        <label class=""><input type="radio" name="encoding" checked="checked" id="ascii">ASCII</label>
                    </div>
                    <div class="radio-inline">
                      <label><input type="radio" name="encoding" id="hexa">HEXA</label>
                    </div>
                </div>
                <br>
                <div>
                    <label class="big_text_box">Mensagem</label>
                    <textarea class="input_text" id="message" rows="1"></textarea> 
                    <button type="button" class="btn btn-lg" id="save_message" onclick="saveMessage()">Salvar</button>
                    <button type="button" class="btn btn-lg" id="send_message" onclick="sendMessageDefault()">Enviar</button>
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
                            <th>MENSAGENS</th>
                            <th>DELETAR</th>
                            <th>ENVIAR</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
               
            <div class="response_div">
                <label class="big_text_box">Dado Enviado</label><br/>
                <textarea class="input-sm input_text input_text_resposta" id="dado_enviado" rows="3"></textarea>
            </div>
            
            <div class="response_div">
                <label class="big_text_box">Dado Recebido</label><br/>
                <textarea class="input-sm input_text input_text_resposta" id="dado_recebido" rows="1"></textarea>
                <br>
                <button class="btn btn-lg" onclick="clear_buffer()">Limbar Buffer</button>
            </div>
            
            <div class="response_div other_functions">
                <div class="audio_div">
                    <label class="big_text_box">Audio</label><br>
                    <button class="btn btn-lg" onclick="audio_play()">Play</button>
                </div>
                <div class="gpio_div">
                    <label class="big_text_box">GPIO</label><br>
                    <c:forEach var="i" begin="1" end="8">
                        <label class="big_text_box">${i}</label>
                        <button type="button" class="btn btn-lg" onclick="gpio(${i},true)">ON</button>
                        <button type="button" class="btn btn-lg" onclick="gpio(${i},false)">OFF</button>
                        <br/>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <script src="js/poc2/poc2_received_data.js" type="text/javascript"></script>
        <script src="js/poc2/poc2_save_delete_message.js" type="text/javascript"></script>
        <script src="js/poc2/poc2_send_message.js" type="text/javascript"></script>
        <script src="js/poc2/poc2_clear_buffer.js" type="text/javascript"></script>
        <script src="js/poc2/poc2_gpio.js" type="text/javascript"></script>
        <script src="js/poc2/poc2_audio_play.js" type="text/javascript"></script>
    </body>
</html>
