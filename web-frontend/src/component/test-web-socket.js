import React from 'react';
import './../App.css';
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

function TestWebSocket(){

    const sock = new SockJS('/websocket');
    const stompClient = Stomp.over(sock);
    let testidsocket = null;

    stompClient.connect({}, function () {
        console.log('connected');

        stompClient.subscribe('/topic/testsocket', function(response){
            var data = JSON.parse(response.body);
            console.log(data);
        })
    })

    const subscribeIdMsg = () => {
        var id = document.getElementById("id").value;
        if(id === "")
            return;

        if(testidsocket){
            testidsocket.unsubscribe();
            testidsocket = null;
        }

        if(!testidsocket){
            testidsocket = stompClient.subscribe('/topic/testidsocket.' + id, function(response){
                var data = JSON.parse(response.body);
                console.log(data);
            })
        }
    }

    const sendIdMsg = () => {
        var id = document.getElementById("id").value;
        console.log('send id: ' + id);
        stompClient.send('/app/testidsocket/' + id, {}, JSON.stringify({'messageBy' : 'test'}));
    }

    const sendMsg = () => {
        console.log('send');
        stompClient.send('/app/testsocket', {}, JSON.stringify({'messageBy' : 'test'}));
        return;
    };

    return (
        <div>
            <button onClick={sendMsg}>send</button><br/>
            <input type="text" id ="id"/>
            <button onClick={subscribeIdMsg}>subscribe</button>
            <button onClick={sendIdMsg}>send</button>
        </div>
    );
}

export default TestWebSocket;