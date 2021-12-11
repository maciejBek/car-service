// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';

const CAR_REST_API_URL = 'http://localhost:8080/api/cars';

class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          isGoing: true,
          numberOfGuests: 2
        };
    
        this.handleInputChange = this.handleInputChange.bind(this);

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
        let carForm = document.getElementById('carForm');
        let formData = new FormData(carForm);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });

        let body = JSON.stringify(data);
        
       // add car to database with post method
        axios({
            method: "post",
            url: CAR_REST_API_URL,
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
        <form id="carForm" onSubmit={this.handleSubmit}>
            <input type="text" placeholder="Marka" id="brand" name="brand" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Model" id="model" name="model" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Rok" id="year" name="year" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Numer VIN" id="vinNumber" name="vinNumber" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Numer rejestracyjny" id="registrationNumber" name="registrationNumber" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Pojemność" id="capacity" name="capacity" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Typ paliwa" id="fuelType" name="fuelType" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Moc" id="power" name="power" value={this.state.value} onChange={this.handleChange} />
            <input type="text" placeholder="Moment obrotowy" id="torque" name="tourqe" value={this.state.value} onChange={this.handleChange} />
            
            <input type="submit" value="Wyślij" />
        </form>
      );
    }
  }

export default Form;