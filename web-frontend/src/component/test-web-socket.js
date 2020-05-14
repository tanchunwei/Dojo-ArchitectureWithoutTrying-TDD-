import React from 'react';
import './../App.css';
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

function TestWebSocket(){

    const sock = new SockJS('/websocket')
    const stompClient = Stomp.over(sock)

    stompClient.connect({}, function () {
        console.log('connected')

        stompClient.subscribe('/topic/testsocket', function(response){
            var data = JSON.parse(response.body);
            console.log(data)
        })
    })
    //sock.onopen = () => {
    //    console.log('connected')
    //};
    //sock.onmessage = () => {
    //    console.log('message received')
    //};
    //sock.onclose = () => {
    //    console.log('closed')
    //};

    const sendMsg = () => {
        console.log('send')
        stompClient.send('/app/testsocket', {}, JSON.stringify({'messageBy' : 'test'}));
        return;
    };

    return (
        <div>
            <button onClick={sendMsg}>send</button>
        </div>
    );
}

export default TestWebSocket;