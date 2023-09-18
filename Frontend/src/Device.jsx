import { Box, Button, CardMedia, TextField, Typography } from '@mui/material'
import React from 'react'
import classes from './CSS/Movie.module.scss'
import axiosInstance from "./axios"
import history from './history'
import SimpleReactCalendar from 'simple-react-calendar'
import ReactApexChart from "react-apexcharts"
//import SockJsClient from 'react-stomp'
const SOCKET_URL = 'http://localhost:8080/ws';

class Device extends React.Component {

    constructor(){
        super();
        this.state = {
         device:[],
         data:[ ], 
        };
    }
    
    handleHome = () => {
        console.log("handleHome");
        localStorage.removeItem('deviceId');
        history.push("/mylist");
        window.location.reload();
    };

    

   
    componentDidMount(){
        console.log(localStorage.getItem('deviceId'));
        console.log(this.state.data)


        axiosInstance.post("/getByIdDevice",localStorage.getItem('deviceId'))
        .then(
            res => {
                const val = res.data;
                
                this.setState(
                    {
                       device:val
                    }       
                );
               // console.log(val);
               // console.log(this.state);
            }
        )
        .catch(error => {
            console.log(error)
        })

       




    }

    handleInput = event => {
       console.log(event);
      var date = new Date();
      date=event;
      console.log(date);
      localStorage.setItem('date',date);
      console.log( "date :");
      //location.reload();
    
      let idata = {
        t:date,
        device: localStorage.getItem('deviceId')
    }
    console.log( "date :");
    console.log(date);


      axiosInstance.post("/generateDataForChart",idata)
      .then(
          res => {
              const val = res.data;
              console.log(val);
                
              this.setState(
                  {
                     data:val
                  }       
              );
                  
                  //location.reload();
             
              console.log(val);
          }
      )
      .catch(error => {
          console.log(error)
      })



    };
     

    render() {
        var c = this.state.data.map(x=> x.consumption)
        var h = this.state.data.map(x=> x.hour)

        let state = {
            series: [
              {
                name: "Consumption",
                data: c,
              },
            ],
            options: {
              chart: {
                height: 350,
                type: "line",
                zoom: {
                  enabled: false,
                },
              },
              dataLabels: {
                enabled: false,
              },
              stroke: {
                curve: "straight",
              },
              title: {
                text: "Hourly consumption of the device",
                align: "left",
              },
              grid: {
                row: {
                  colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
                  opacity: 0.5,
                },
              },
              xaxis: {
                type: "hourly",
                categories: h
              },
            },
          };
          
          // <ReactApexChart options={state.options} series={state.series} type="line" height={350} />
          

        
        const rev= localStorage.getItem('revs')
       // const { data: chartData } = this.state;

       /*const data = [
            {hour: 18, consumption: 71},
            {hour: 17, consumption: 2},
            {hour: 16, consumption: 61},
            {hour: 15, consumption: 74},
            {hour: 14, consumption: 71},
            {hour: 13, consumption: 41},
            {hour: 12, consumption: 9},
            {hour: 11, consumption: 71}
        ];
        */

        console.log(this.state);
            return (

              

                <React.Fragment>

           
                
                
                    <Box className={classes.right} >   
                

                        <Typography component="div" sx={{ flexGrow: 1 }}>
                            Device manager
                        </Typography>
                    

                    
                            <div className={classes.right}>
                                    <Box>
                                        <Typography className={classes.gen_value}>{this.state.device.location}</Typography>
                                        <Button className={classes.back_btn} onClick={this.handleHome}> Back to My List</Button>
                                        <br />
                                        <Typography className={classes.name}><strong>{this.state.device.name}</strong></Typography>
                                        <br />  
                                        <Typography className={classes.description}>Description:</Typography>
                                        <br />
                                        <Typography className={classes.description_value}>{this.state.device.description}</Typography>
                                        <br />
                                    
                                    </Box>
                            </div>

                            <div className={classes.left}>
                                    
                             <SimpleReactCalendar  activeMonth={new Date()}  onSelect={(event)=>this.handleInput(event)}/>
                             <br />
                            
                             <ReactApexChart options={state.options} series={state.series} type="line" height={350} />
                            
                            </div>
                        
                    </Box>
                </React.Fragment>
            )
                       
    }
    
}
    
export default Device;

