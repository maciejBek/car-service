// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Zaplac.css';


const CAR_REST_API_URL = 'http://localhost:8080/api/register';

const Tytul = (values) =>{
    return(
        <div id="Tytul" >
            {values.tekst}
        </div>
    )
}

const Tekst = (values) =>{
    return(
        <div id="tekst" >
            {values.tekst}
        </div>
    )
}

const Wstaw = (values) =>{
    return(
        <div id="inputrr">
        {values.tekst}
        <input type="text" className="inputr" placeholder={values.dom} id="power" name={values.idk} />
        </div>
    )
}

const Przycisk = (values) =>{
    return(
        <div id="przycisk">
            <input id="przycisk2" type="submit" value="Dalej" />
        </div>
    )
}




class Payment extends React.Component {
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
        let rere = document.getElementById('rejestracja2');
        let formData = new FormData(rere);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });

        console.log(data);
        
        var pojemnik = {};
        console.log(pojemnik);
        pojemnik.account = {};
        pojemnik.account.username = data.username
        pojemnik.account.password = data.password
        pojemnik.account.emailAddress = data.emailAddress
        pojemnik.customer = {};
        pojemnik.customer.name = data.name;
        pojemnik.customer.surname = data.surname;
        pojemnik.customer.phoneNumber = data.phoneNumber;
        pojemnik.address = {};
        pojemnik.address.street = data.street;
        pojemnik.address.number = data.number;
        pojemnik.address.town = data.town;
        console.log(pojemnik);
        

        let body = JSON.stringify(pojemnik);
        console.log(body);

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
                sessionStorage.clear();
            })
            .catch(function (response) {
                //handle error
                console.log(response);
                sessionStorage.clear();
            });

        event.preventDefault();
    }



    render() {
        return (

                <div id="glownyzaplac">
                <Tytul
                tekst="Wprowadź cene wykonanej usługi:"/>
                
                <form id="rejestracja2" onSubmit={this.handleSubmit}>
                <div id = "inputy">
                <Wstaw
                tekst="Podaj cene:"
                dom = "Cena"
                idk = "username"/>
                
                </div>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wyślij"  />
                </div>
                </form>

            


                

                </div>
            
        );
    }
}

export default Payment;