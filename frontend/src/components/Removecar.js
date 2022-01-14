import React from 'react';
import axios from "axios";
import './Removecar.css';

const CUSTOMER_REST_API_URL = 'http://localhost:8080/api/customers';
const CARS_REST_API_URL = 'http://localhost:8080/api/cars';
const SERVICES_REST_API_URL = 'http://localhost:8080/api/services';
const TASKS_REST_API_URL = 'http://localhost:8080/api/tasks';



class Removecar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            obj: [],
            cars: [],
            isGoing: true,
            numberOfGuests: 2
        };

        this.samochody = this.samochody.bind(this)
        this.usun = this.usun.bind(this);

    }

    samochody() {
        var id = document.getElementById("select").value

        const c = axios({
            method: "get",
            url: CARS_REST_API_URL,
            params: {
                pageSize: 100,
                pageNo: 0,
                sortBy: "brand",
                customerId: id
              }
        })

            const d = c.then(response => {
                this.setState({cars: response.data }) 
                
            })
            c.catch(function (response) {
                console.log(response)       
            })

    }

    componentDidMount(){
        
        const a = axios({
            method: "get",
            url: CUSTOMER_REST_API_URL,
            params: {
                pageSize: 100,
                pageNo: 0,
                sortBy: "acceptanceDate"
              }
        })

            const b = a.then(response => {
                this.setState({obj: response.data }) 
                
            })
            a.catch(function (response) {
                console.log(response)       
            })

        
    }


    usun(event) {
        // getting data from form and putting to json string to body array


        var carId = document.getElementById("cars").value

        var urll = 'http://localhost:8080/api/cars/'+carId
        console.log(urll)

        axios({
            method: "delete",
            url: urll,
            headers: { "Content-Type": "application/JSON" },
        })
            .then(function (response) {
                //handle success
                console.log(response);
            })
            .catch(function (response) {
                //handle error
                console.log(response);
            });

        event.preventDefault();
    }

   

    render() {
        return (
            <div id="contenerremovecar">
                <div id="teksttask">
                Wybierz pojazd który ma zostać usunięty:
                </div>

                <div id="wyborytask">
                <div id="customercont">
                <div id="teksttask1">
                Wybierz klienta:
                </div>
                
                <select id="select" name="customerId" onInput={this.samochody}>
                <option value="" disabled selected>Klenci</option>
                    {this.state.obj.map(el =>(
                        <option value={el.id}>{el.name}{" "}{el.surname}</option>
                    ))}
                </select>
                </div>
                <div id="customercont">
                <div id="teksttask1">
                Wybierz samochód:
                </div>
                <select id="cars" name="carId" onInput={this.serwis}>
                <option value="" disabled selected>Samochody</option>
                    {this.state.cars.map(el =>(
                        <option value={el.id}>{el.brand}{" "}{el.model}</option>
                    ))}
                </select>
                </div>
                </div>
                
                
                
                
                <div id="taskbutton">
                <input id="przycisk2" onClick={this.usun} type="submit" value="Usuń"  />
                </div>

            </div>
        ); 
    }
}

export default Removecar;