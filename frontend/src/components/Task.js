import React from 'react';
import axios from "axios";

const CUSTOMER_REST_API_URL = 'http://localhost:8080/api/customers';
const CARS_REST_API_URL = 'http://localhost:8080/api/cars';
const SERVICES_REST_API_URL = 'http://localhost:8080/api/services';
const TASKS_REST_API_URL = 'http://localhost:8080/api/tasks';



class Task extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            obj: [],
            cars: [],
            service: [],
            isGoing: true,
            numberOfGuests: 2
        };

        this.samochody = this.samochody.bind(this)
        this.serwis = this.serwis.bind(this)
        this.handleInputChange = this.handleInputChange.bind(this);

    }

    serwis() {
        console.log("atatatatata")

        const e = axios({
            method: "get",
            url: SERVICES_REST_API_URL,
            params: {
                pageSize: 100,
                pageNo: 0
              }
        })

            const f = e.then(response => {
                console.log(response.data)
                this.setState({service: response.data }) 
                console.log(this.state.service)
                
            })
            e.catch(function (response) {
                console.log(response)       
            })

    }

    samochody() {
        var id = document.getElementById("select").value
        console.log(this.state.cars)
        console.log("tututututu")

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
                console.log(response.data)
                this.setState({cars: response.data }) 
                console.log(this.state.cars)
                
            })
            c.catch(function (response) {
                console.log(response)       
            })

    }

    componentDidMount(){
        console.log(document.getElementById("select"))
        console.log(this.state.cars)
        
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
                console.log(response.data)
                this.setState({obj: response.data }) 
                console.log(this.state.obj)
                
            })
            a.catch(function (response) {
                console.log(response)       
            })

            console.log(this.state.obj)
        
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        // getting data from form and putting to json string to body array
        let usluga = document.getElementById('usluga');
        let formData = new FormData(usluga);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });

        let body = JSON.stringify(data);
        data.customerId = document.getElementById("select").value
        data.carId = document.getElementById("cars").value
        data.serviceId = document.getElementById("service").value
        var day = new Date();
        
        console.log(day.toISOString())
        console.log(data)
        
        data.acceptationDate = day.toISOString()
        // add car to database with post method
        axios({
            method: "post",
            url: TASKS_REST_API_URL,
            data: body,
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
            <div>
                
                <select id="select" onInput={this.samochody}>
                <option value="" disabled selected>Klenci</option>
                    {this.state.obj.map(el =>(
                        <option value={el.id}>{el.name}{" "}{el.surname}</option>
                    ))}
                </select>

                <select id="cars" onInput={this.serwis}>
                <option value="" disabled selected>Samochody</option>
                    {this.state.cars.map(el =>(
                        <option value={el.id}>{el.brand}{" "}{el.model}</option>
                    ))}
                </select>

                <select id="service">
                <option value="" disabled selected>Usługi</option>
                    {this.state.service.map(el =>(
                        <option value={el.id}>{el.name}</option>
                    ))}
                </select>
                <form id="usluga" onSubmit={this.handleSubmit}>
                <textarea id="story" name="serviceDescription" rows="5" cols="33">
                    Opis usługi...
                </textarea>

                <textarea id="story" name="problemDescription" rows="5" cols="33">
                    Opis problemu...
                </textarea>

                <input id="przycisk2" type="submit" value="Wyślij" />
                </form>

            </div>
        ); 
    }
}

export default Task;