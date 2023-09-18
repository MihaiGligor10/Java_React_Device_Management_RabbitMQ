import React from "react";
import axiosInstance from "./axios";
import side from './CSS/Sidebar.module.scss'
import SideNav, { Toggle, Nav, NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import {Grid} from "@material-ui/core";

import history from './history';
//import styles from 'Login.css';
import '@trendmicro/react-sidenav/dist/react-sidenav.css';
import adminstyle from "./CSS/Admin.module.scss";






class Admin extends React.Component {

    handleInput = event => {
        const {value, name} = event.target;
        this.setState({
            [name]: value
        });
       
    };

    onSubmitAddDevice = event => {
      event.preventDefault();
      let device= {
          name: this.state.name,
          location: this.state.location,
          description : this.state.description,
          maxConsumption : this.state.maxConsumption
      }

      console.log(device);
      
     
      axiosInstance.post("/addDevice", device)
          .then(
              res => {
                  const val = res.data;
                  console.log("Success");
                  console.log(val);
                  localStorage.setItem("device "+val.name , val.id);
                }
          )
          .catch(error => {
              console.log(error)
          })
    }

    onSubmitDeleteDevice = event => {
        event.preventDefault();
        let delmov = {
            name: this.state.numeMov,
            id: this.state.locationMov,
            idc: this.state.location2Mov
        }
        console.log(delmov);
        axiosInstance.post("/deleteDevice", delmov)
            .then(
                res => {
                    const val = res.data;
                    console.log("Success");           
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    onSubmitDeleteClient = event => {
        event.preventDefault();
           
            console.log(this.state.email);
            axiosInstance.post("/deleteClient", this.state.email)
                .then(
                    res => {
                        const val = res.data;
                        console.log("Success");           
                }
                )
                .catch(error => {
                    console.log(error)
                })
    }

    onSubmitLinkClient = event => {
        event.preventDefault();
        let linkinfo = {
            client: this.state.client,
            device: this.state.device,
        }
            
            axiosInstance.post("/linkClient", linkinfo)
                .then(
                    res => {
                        const val = res.data;
                        console.log("Success");           
                }
                )
                .catch(error => {
                    console.log(error)
                })
    }

    onSubmitGenerate= event => {
        event.preventDefault();
        
            
            axiosInstance.post("/generateMeasures", this.state.deviceG)
                .then(
                    res => {
                        const val = res.data;
                        console.log("Success");           
                }
                )
                .catch(error => {
                    console.log(error)
                })
    }

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
                <div className={adminstyle.adminbody}>
                    
                     
                       <SideNav
                             onSelect={this.handle}
                             
                            >
                            <SideNav.Toggle />
                            <SideNav.Nav defaultSelected="admin">
                                <NavItem eventKey="admin">
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
                                <h2 className="text-center">The best place for monitoring devices</h2>
                            </div>
                            </div>
                            <div className="section" id="about">
                            <h1>Admin</h1>
                            <hr />
                            </div>
                            <div className="container">
                            <div className="row">
                               
                            </div>
                            {/*/.row*/}
                            </div>
                           
                       
                            
                        </div>





                    <Container maxWidth="sm">
                        <div>
                            <Grid>
                                <form onSubmit={this.onSubmitAddDevice}>
                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="devicename"
                                        label="Name"
                                        name="name"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />
                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        name="location"
                                        label="Location"
                                        id="devicelocation"
                                        onChange={this.handleInput}
                                        
                                    /> 
                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="devicedescription"
                                        label="Description"
                                        name="description"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />

                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        name="maxConsumption"
                                        label="Max Consumption"
                                        id="deviceimagepath"
                                        onChange={this.handleInput}
                                        
                                    />
                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        color="primary"
                                    >
                                        Add Device
                                    </Button>
                                </form>


                                <form onSubmit={this.onSubmitDeleteDevice}>
                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="deletedevice"
                                        label="Insert the name of the device you want to delete"
                                        name="numeMov"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />

                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="deletedevicelocation"
                                        label="Insert the id of the device you want to delete"
                                        name="locationMov"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />

                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="deletedevicelocation"
                                        label="Insert the id of the client that owns the device , or 0"
                                        name="location2Mov"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />
                                   
                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        color="primary"
                                    >
                                        Delete Device
                                    </Button>
                                </form>

                                <form onSubmit={this.onSubmitDeleteClient}>
                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="deleteclient"
                                        label="Insert the name of the client to delete it"
                                        name="email"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />
                                   
                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        color="primary"
                                    >
                                        Delete Client
                                    </Button>
                                </form>


                                <form onSubmit={this.onSubmitLinkClient}>
                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="linkclientdev"
                                        label="Insert the id of the client you wish to associate with the device "
                                        name="client"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />

                                    <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="linkclidevice"
                                        label="Insert the id of the device you wish to assocoiate with the client"
                                        name="device"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />
                                   
                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        color="primary"
                                    >
                                        Link client and device
                                    </Button>
                                </form>

                                <form onSubmit={this.onSubmitGenerate}>

                                    
                                <TextField
                                        variant="outlined"
                                        margin="normal"
                                        required
                                        fullWidth
                                        id="linkclidevice"
                                        label="Insert the id of the device to generate measurements"
                                        name="deviceG"
                                        
                                        onChange={this.handleInput}
                                        autoFocus
                                    />
                                
                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        color="primary"
                                    >
                                        Import measurements from sensor for this device
                                    </Button>
                                </form>


                            </Grid>
                        </div>
                    </Container>

                </div>

        )
    }

}
 
export default Admin;