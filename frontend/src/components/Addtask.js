import React from 'react';
import axios from "axios";
import './Addtask.css';
import { Link } from "react-router-dom"


const SERVICES_REST_API_URL = 'http://localhost:8080/api/services';
const TASKS_REST_API_URL = 'http://localhost:8080/api/tasks';



class Addtask extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            obj: [],
            cars: [],
            service: [],
            isGoing: true,
            numberOfGuests: 2
        };

        this.serwis = this.serwis.bind(this)
        this.handleInputChange = this.handleInputChange.bind(this);

    }

    serwis() {

        const e = axios({
            method: "get",
            url: SERVICES_REST_API_URL,
            params: {
                pageSize: 100,
                pageNo: 0
              }
        })

            const f = e.then(response => {
                this.setState({service: response.data }) 
                
            })
            e.catch(function (response) {
                console.log(response)       
            })

    }

    

    componentDidMount(){
        var id
        id = sessionStorage.getItem("id")
        console.log(id)
        var ulrr 
        ulrr= "http://localhost:8080/api/cars/account/"+id
        console.log(ulrr)

        const a = axios({
            method: "get",
            url: ulrr,
            
        })

            const b = a.then(response => {
                console.log(response.data)
                this.setState({cars: response.data }) 
                
            })
            a.catch(function (response) {
                console.log(response)       
            })

        
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
        var id
        id = sessionStorage.getItem("id")
        console.log(id)
        // getting data from form and putting to json string to body array
        let usluga = document.getElementById('usluga');
        let formData = new FormData(usluga);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });
        var i = document.getElementById("cars").value
        var f = document.getElementById("service").value


        data.carId = parseInt(i)
        data.serviceId = parseInt(f)
        data.accountId = parseInt(id)

        var day = new Date();
        
       
        
        data.acceptanceDate = day.toISOString()
        // add car to database with post method
        let body = JSON.stringify(data);
        console.log(data)
        console.log(body)
        axios({
            method: "post",
            url: TASKS_REST_API_URL,
            data: body,
            headers: { "Content-Type": "application/JSON" },
        })
            .then(function (response) {
                //handle success
                console.log(response);
                document.getElementById('klientlink').click();
            })
            .catch(function (response) {
                //handle error
                console.log(response);
            });

        event.preventDefault();
    }

   

    render() {
        return (
            <div id="contener">
                <div id="teksttask">
                Aby doda?? nowe zlecenie nale??y wype??ni?? poni??sze pola:
                </div>

                <div id="wyborytask">
                <div id="customertask">
                <div id="teksttask1">
                Wybierz samoch??d:
                </div>
                <select id="cars" name="carId" onInput={this.serwis}>
                <option value="" disabled selected>Samochody</option>
                    {this.state.cars.map(el =>(
                        <option value={el.id}>{el.brand}{" "}{el.model}</option>
                    ))}
                </select>
                </div>
                </div>
                <div id="taskcont">
                    <div id="teksttask2">
                Wybierz Us??ug?? kt??ra ma zosta?? wykonana:
                    </div>
                <select id="service" name="serviceId">
                <option value="" disabled selected>Us??ugi</option>
                    {this.state.service.map(el =>(
                        <option value={el.id}>{el.name}</option>
                    ))}
                </select>
                </div>
                
                <form id="usluga" onSubmit={this.handleSubmit}>
                <div id="uslugacont1">
                    <div id="teksttask1">
                    Opis us??ugi:
                    </div>
                <textarea id="tekstarea1" name="serviceDescription" rows="5" cols="33" placeholder="Opis us??ugi..." >
                    
                </textarea>
                </div>
                <div id="uslugacont2">
                    <div id="teksttask1">
                        Opis problemu:
                    </div>
                <textarea id="tekstarea2" name="problemDescription" rows="5" cols="33" placeholder="Opis problemu...">
                    
                </textarea>
                </div>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wy??lij"  />
                </div>
                </form>
                <Link to="/klient" id="klientlink"/>
            </div>
        ); 
    }
}

export default Addtask;