import React from "react";
import side from './CSS/Sidebar.module.scss'
import axiosInstance from "./axios";
import homestyle from "./CSS/Home.module.scss";
import SideNav, { Toggle, Nav, NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import '@trendmicro/react-sidenav/dist/react-sidenav.css';
import history from './history';

import "react-multi-carousel/lib/styles.css";


// import SockJsClient from 'react-stomp';

class Home extends React.Component {


    constructor() {
        super()
        this.state = {
            actor: [],
            devices:[]
        }
    };



    handle = (selected) => {
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

    



    render() {
        return (
                <div className={homestyle.homebody}>
                    
                       
                    <SideNav
                             onSelect={this.handle}
                             className={side.sidebar}
                            >
                            <SideNav.Toggle />
                            <SideNav.Nav defaultSelected="home">
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

            




                        <div className="page-wrap">
                            <div className="jumbotron">
                            <div style={{marginTop: '130px'}} className="container">
                             
                                <a href="#" className="h5 close-menu"><i style={{color: '#fff', position: 'relative', bottom: '120px', cursor: 'pointer'}} className="fa fa-times fa-2x fa-fw" /></a>
                                <h1>Device manager</h1>
                                <div className="spacer" />
                                <hr />
                                <hr />
                                <h2 className="text-center">The best place for devices</h2>
                            </div>
                            </div>
                            <div className="section" id="about">
                            <h1>About</h1>
                            <hr />
                            </div>
                            <div className="container">
                            <div className="row">
                                <div className="col-md-8">
                                <div className="well">
                                    <h3>Device manager</h3>
                                    <p>A place for cinematografy and cinephiles, where you can share your opinions about devices , rate them , get all kind of information about them , and recieve great recomandations and news</p>
                                </div>
                                </div>
                            </div>
                
                            <br />
                        
                          
                            <div className="section" id="contact">
                            <h1>Enjoy !!!</h1>
                            <hr />
                            </div>
                        </div>    
                    </div>

                   {/* <div>{this.connect()}</div> */}
                   {/* <SockJsClient
                        url={SOCKET_URL}
                        topics={['/topic/message']}
                        onConnect={console.log("Connected!")}
                        onDisconnect={console.log("Disconnected!")}
                        onMessage={msg => alert(msg)}
                        debug={false}
                    /> */}
                </div>

        )
    }

}

export default Home;