/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Delete a message in the table*/
function deleteMessage(el){
    while (el.parentNode && el.tagName.toLowerCase() !== 'tr') {
        el = el.parentNode;
    }
    el.parentNode.removeChild(el);
    var table = document.getElementById('saved_messages_table').getElementsByTagName('tbody')[0];
    updateMessageNumber(table);
}

/*Save a message into the specified table*/
function saveMessage(){
    var label = document.getElementById('label').value;
    var message = document.getElementById('message').value;
    if(label !== "" && message != ""){
        var table = document.getElementById('saved_messages_table').getElementsByTagName('tbody')[0];
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var rowId = table.rows.length;
        updateMessageNumber(table);
        cell1.innerHTML = rowId;
        cell2.innerHTML = label;
        cell3.innerHTML = message;
        cell4.innerHTML = '<td><button class="btn btn-sm" onclick="deleteMessage(this)">DEL</button></td>';
        cell5.innerHTML = '<td><button class="btn btn-sm" onclick="sendMessageFromTable(this)" id="send_message_'+(rowId-1)+'">ENV</button></td>';
    }
}

/*Update the numbers of the indexes of the table when some change happens */
function updateMessageNumber(table){
    for(var i = 0; i<table.rows.length;i++){
        var row = table.rows[i];
        row.cells[0].innerHTML = i+1;
    }
}
