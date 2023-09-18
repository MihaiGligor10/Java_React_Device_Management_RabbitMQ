import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import axiosInstance from "./axios";
import {Grid} from "@material-ui/core";
import history from './history';
import logstyle from "./CSS/Login.module.scss";


class Login extends React.Component {
    constructor() {
        super();
        this.state = {
            email: "",
            password: "",
        };
    }


    handleInput = event => {
        const {value, name} = event.target;
        this.setState({
            [name]: value
        });
       console.log(value);
    };






    onSubmitFun = event => {
      event.preventDefault();
      let credentilasl = {
          email: this.state.username,
          password: this.state.password
      }
      console.log(credentilasl);
      axiosInstance.post("/login", credentilasl)
          .then(
              res => {
                  const val = res.data;
                  console.log("Success");
                  console.log(res.data);
                  localStorage.setItem('USER_ID', res.data.id);
                  localStorage.setItem('user', val.email);
                  if(res.data)
                  {
                    if(val.email=="admin@gmail.com" && val.password =="admin000")
                    {
                      history.push("/admin");
                      window.location.reload();
                    }else
                    {
                      history.push("/home");
                      window.location.reload();
                    }
                    
                  }
                  else
                  {
                    console.log("client inexistent");
                  }
                 
              }
          )
          .catch(error => {
              console.log(error)
          })
  }




     

      onSubmitFunRegister = event => {
        event.preventDefault();
    

        let credentilas = {
            email: this.state.usernameR,
            password: this.state.passwordR
        }
        console.log(credentilas);

       
        const verificareMail = new RegExp("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        const verificarePass = new RegExp("(?=.*?[a-z])(?=.*?[0-9]){5,}");
        
        if(!verificareMail.test(credentilas.email) || !verificarePass.test(credentilas.password)){
          console.log("incorrect email/password");
        }
        else{
        axiosInstance.post("/register", credentilas)
            .then(
                res => {
                  const val = res.data;
                  console.log("Success");
                  localStorage.setItem("USER_ID", res.data.id);
                  console.log(res.data);
                }
            )
            .catch(error => {
                console.log(error)
            })
          }
     }


render() {
        const { match, location, history } = this.props;
        return (
         
        <div className={logstyle.loginbody}> 
        
          <Container maxWidth="lg">
                
               
                <h2 style={{fontSize: '80px'}}>Device manager</h2><br />  

                <div className={logstyle.login}>    


                  <form id="login" onSubmit={this.onSubmitFun}>    
                    <label><b>User Name     
                      </b>    
                    </label>    
                    <br /><br /> 
                    <input type="text" name="username" id="Uname" placeholder="Username" onChange={this.handleInput} />    
                    <br /><br />    
                    <label><b>Password     
                      </b>    
                    </label>  
                    <br /><br />   
                    <input type="Password" name="password" id="Pass" placeholder="Password" onChange={this.handleInput} />    
                    <br /><br /> 
                    <br /><br />    
                          <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >
                                Log In
                          </Button>        
                    <br /><br />        
                  </form>




                  <form id="register" onSubmit={this.onSubmitFunRegister}>    
                    <label><b>User Name     
                      </b>    
                    </label>    
                    <br /><br /> 
                    <input type="text" name="usernameR" id="Unamer" placeholder="Username" onChange={this.handleInput}/>    
                    <br /><br />    
                    <label><b>Password     
                      </b>    
                    </label>    
                    <br /><br /> 
                    <input type="Password" name="passwordR" id="Passr" placeholder="Password" onChange={this.handleInput}/>    
                    <br /><br />  
                    <label><b>Repeat Password     
                      </b>  
                    </label>  
                    <br /><br />   
                    <input type="Password" name="Passr2" id="Passr2" placeholder="Password" onChange={this.handleInput}/>    
                    <br /><br />    		
                          <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >
                                Register
                          </Button>     
                    <br /><br />        
                    
                  </form>


                </div>   


             
          </Container> 
          </div>
        );
    }

}

export default Login;