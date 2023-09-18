import logo from './logo.svg';
import './App.css';
import {
    BrowserRouter as Router,
    Route,
    Routes as Switch,
    Navigate as Redirect
} from "react-router-dom";

//import Administration from './Administration';
import Login from './Login';
import Home from "./Home";
import MyList from './MyList';
import Device from './Device';
import Admin from './Admin';
import ChatRoom from './Chatroom';

import toast, { Toaster } from 'react-hot-toast';


function App() {
    const defaultRoute = window.location.pathname === "/" ? <Redirect to="/log-in"/> : undefined;

    return (
     
        <Router>
            <div><Toaster/></div>
            <Switch>
                <Route exact path="/log-in" element={<Login/>}/>
              
                <Route exact path="/home" element={<Home/>}/>

                <Route exact path="/mylist" element={<MyList/>}/>

                <Route exact path="/device" element={<Device/>}/>

                <Route exact path="/admin" element={<Admin/>}/>

                <Route exact path="/chatroom" element={<ChatRoom/>}/>


            </Switch>
            {defaultRoute}
        </Router>
    );
}

export default App;