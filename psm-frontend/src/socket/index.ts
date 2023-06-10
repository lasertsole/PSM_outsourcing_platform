//引入socket.io
import io from 'socket.io-client';
export function createConnect(user_id:string) {//创建socket链接
    let WSConnect = new WebSocket(import.meta.env.VITE_WS_URL+"/ws/socketio/?user_id="+user_id);
    WSConnect.onopen = function() {
        console.log('Client Socket Open');
    };
    //连接关闭触发的回调方法
    WSConnect.onclose = function(event) {
        console.log('Client Socket Closed');
    };
    //连接通讯发生异常触发的回调方法
    WSConnect.onerror = function(event) {
        console.log('Client Socket Error');
    };
    //通讯过程中受到信息触发的回调方法
    WSConnect.onmessage = function(event) {
        console.log('** Client Receive:' + event.data);
    };
    return WSConnect;
}

export function disconnect() {
    
}