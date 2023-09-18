import React, { useEffect, useState } from 'react'
import {over} from 'stompjs';
//import SockJS from 'sockjs-client';
import SockJS from "sockjs-client/dist/sockjs"
import SideNav, { Toggle, Nav, NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import '@trendmicro/react-sidenav/dist/react-sidenav.css';
import history from './history';
import side from './CSS/Sidebar.module.scss'
import axiosInstance from "./axios";
import css from "./CSS/Chat.module.scss";
import "./CSS/Chat.css";
import toast, { Toaster } from 'react-hot-toast';


var stompClient =null;

const ChatRoom = () => {
    const [privateChats, setPrivateChats] = useState(new Map());     
    const [publicChats, setPublicChats] = useState([]); 
    const [tab,setTab] =useState("CHATROOM");
    const [userData, setUserData] = useState({
        username: '',
        password:'',
        receiverName: '',
        connected: false,
        message: ''
      });
    useEffect(() => {
      console.log(userData);
    }, [userData]);

    const connect =()=>{
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({},onConnected, onError);
    }

    const onConnected = () => {
        setUserData({...userData,"connected": true});
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('/user/'+userData.username+'/private', onPrivateMessage);
        userJoin();
    }

    const userJoin=()=>{
          var chatMessage = {
            senderName: userData.username,
            status:"JOIN"
          };
          stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    }

    const onMessageReceived = (payload)=>{
        var payloadData = JSON.parse(payload.body);
        switch(payloadData.status){
            case "JOIN":
                if(!privateChats.get(payloadData.senderName)){
                    privateChats.set(payloadData.senderName,[]);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
            case "NO":
                console.log("it reached notify");
                if(userData.username=="admin@gmail.com")
                {
                    notify();
                }
                
                break;
        }
    }
    
    const onPrivateMessage = (payload)=>{
        console.log(payload);
        var payloadData = JSON.parse(payload.body);
      
        if(privateChats.get(payloadData.senderName)){
            privateChats.get(payloadData.senderName).push(payloadData);
            setPrivateChats(new Map(privateChats));
        }else{
            let list =[];
            list.push(payloadData);
            privateChats.set(payloadData.senderName,list);
            setPrivateChats(new Map(privateChats));
        }
    }

    const onError = (err) => {
        console.log(err);
        
    }

    

    const notify = () => 
    {
        toast.success('User is typing shit',"duration:5000")
        console.log("toast?");
    }
    const sendValue=()=>{
            if (stompClient) {
              var chatMessage = {
                senderName: userData.username,
                message: userData.message,
                status:"MESSAGE"
              };
              console.log(chatMessage);
              stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
              setUserData({...userData,"message": ""});
            }
    }

    const sendPrivateValue=()=>{
        if (stompClient) {
          var chatMessage = {
            senderName: userData.username,
            receiverName:tab,
            message: userData.message,
            status:"MESSAGE"
          };
          
          if(userData.username !== tab){
            privateChats.get(tab).push(chatMessage);
            setPrivateChats(new Map(privateChats));
          }
          stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
          setUserData({...userData,"message": ""});
        }
    }

    const handleUsername=(event)=>{
        const {value}=event.target;
        setUserData({...userData,"username": value});
    }
    const handlePass=(event)=>{
        const {value}=event.target;
        setUserData({...userData,"password": value});
    }

    const handleMessage =(event)=>{
        const {value}=event.target;
        setUserData({...userData,"message": value});
        if(stompClient)
        {
            var chatMessage = {
                senderName: userData.username,
                message: userData.message,
                status:"NO"
              };
              console.log(chatMessage.senderName);
              if(chatMessage.senderName!="admin@gmail.com")
              {
                stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
              }
              
        }
        else{
            console.log("no bueno")
        }
        
       
    }

    const registerUser=()=>{

       
            let credentilasl = {
                email: userData.username,
                password: userData.password
            }
          
            axiosInstance.post("/chatConnect", credentilasl)
                .then(
                    res => {
                        const val = res.data;
                        console.log("Success");
                        console.log(res.data);
                       
                      
                         connect();
                        
                       
                    }
                )
                .catch(error => {
                    console.log(error)
                    alert("client inexistent , email sau parola gresite !");
                })
        
        
    }


    const handle = (selected) => {
        const to = '/' + selected;
        if (location.pathname !== to) {
            if(to == '/log-in')
            {
                axiosInstance.post("/logout", localStorage.getItem('USER_ID'))
                .then(
                    res => {
                        
                    }
                )
                .catch(error => {
                    console.log(error)
                })
                localStorage.removeItem('USER_ID');
                console.log(to);
                history.push(to);
                window.location.reload();
            }else
            {
                if(to=="/home" && localStorage.getItem("user")=="admin@gmail.com")
                {
                    console.log(to);
                    history.push("/admin");
                    window.location.reload();
                }else
                {
                console.log(to);
                history.push(to);
                window.location.reload();
                }
               
            }
           
        }
    }


    return (




        
    <div className={css.container}>


     
                            <SideNav
                             onSelect={handle}
                             className={side.sidebar}
                            >
                            <SideNav.Toggle />
                            <SideNav.Nav defaultSelected="chatroom">
                                <NavItem eventKey="home">
                                    <NavIcon>
                                        <i className="fa fa-fw fa-home" style={{ fontSize: '1.75em' }} />
                                    </NavIcon>
                                    <NavText>
                                        Home
                                    </NavText>
                                </NavItem>
                                <NavItem eventKey="mylist">
                                    <NavIcon>
                                        <i className="fa fa-fw fa-home" style={{ fontSize: '1.75em' }} />
                                    </NavIcon>
                                    <NavText>
                                        My List
                                    </NavText>
                                </NavItem>

                                <NavItem eventKey="chatroom">
                                    <NavIcon>
                                        <i className="fa fa-fw fa-home" style={{ fontSize: '1.75em' }} />
                                    </NavIcon>
                                    <NavText>
                                        Chatroom
                                    </NavText>
                                </NavItem>
                               
                                <NavItem eventKey="log-in">
                                    <NavIcon>
                                        <i className="fa fa-fw fa-home" style={{ fontSize: '1.75em' }} />
                                    </NavIcon>
                                    <NavText>
                                        Log Out
                                    </NavText>
                                </NavItem>
                               
                            </SideNav.Nav>
                        </SideNav>




        {userData.connected?
        <div className={css.chatbox}>


            <div className={css.memberlist}>
                <ul>
                <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                    {[...privateChats.keys()].map((name,index)=>(
                        <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
                    ))}
                </ul>
            </div>



            {tab==="CHATROOM" && <div className={css.chatcontent}>
                <ul className={css.chatmessages}>
                    {publicChats.map((chat,index)=>(
                       <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                            {chat.senderName !== userData.username && <div className={css.avatar}>{chat.senderName}</div>}
                            <div className={css.messagedata}>{chat.message}</div>
                            {chat.senderName === userData.username && <div className={css.avatarself}>{chat.senderName}</div>}
                        </li>
                    ))}
                </ul>

                <div className={css.sendmessage}>
                    <input type="text" className={css.inputmessage} placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
                    <button type="button" className={css.sendbutton} onClick={sendValue}>send</button>
                </div>
            </div>}



            {tab!=="CHATROOM" && <div className="chat-content">
                <ul className="chat-messages">
                    {[...privateChats.get(tab)].map((chat,index)=>(
                        <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                            {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                            <div className="message-data">{chat.message}</div>
                            {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                        </li>
                    ))}
                </ul>

                <div className="send-message">
                    <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
                    <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                </div>
            </div>}
        </div>
        :
        <div className={css.register}>
            <input
                id="user-name"
                placeholder="Enter your name"
                name="userName"
                value={userData.username}
                onChange={handleUsername}
                margin="normal"
            />
              
            <input
                id="password"
                placeholder="Enter your password"
                name="userName"
                value={userData.password}
                onChange={handlePass}
                margin="normal"
            />
              <button type="button" onClick={registerUser}>
                    connect
              </button> 
        </div>}
    </div>
    )
}

export default ChatRoom