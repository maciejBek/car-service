 import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Rejestracja2.css';
import Rejestracja from './Rejestracja';


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
        let rere = document.getElementById('rejestracja2');
        let formData = new FormData(rere);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });
        data.emailAddress = sessionStorage.getItem("emailAddress")
        data.username = sessionStorage.getItem("username")
        data.password = sessionStorage.getItem("password")

        console.log(data);
        
        var pojemnik = {};
        pojemnik.account = {};
        pojemnik.account.username = data.username
        pojemnik.account.password = data.password
        pojemnik.account.emailAddress = data.emailAddress
        pojemnik.customer = {};
        pojemnik.customer.name = data.Imie;
        pojemnik.customer.surname = data.Nazwisko;
        pojemnik.customer.phoneNumber = data.nrTel;
        pojemnik.address = {};
        pojemnik.address.street = data.Ulica;
        pojemnik.address.number = data.Nrdomu;
        pojemnik.address.town = data.Miejscowosc;
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
                document.getElementById('logowanielink').click();
            })
            .catch(function (response) {
                //handle error
                console.log(response);
                document.getElementById('logowanielink').click();
                
            });

        event.preventDefault();
    }



    render() {
        return (

                <div id="glownyr">
                <Tytul
                tekst="Witaj w ostanim etapie rejestracji !"/>
                <Tekst
                tekst="aby uko??czy?? rejestracie musisz wype??ni?? jeszcze kilka informacji na sw??j temat. Pozwoli nam to na spersonalizowanie twojego konta"/>
                <form id="rejestracja2" onSubmit={this.handleSubmit}>
                <div id = "inputy">
                <Wstaw
                tekst="Podaj swoje imi??"
                dom = "Imi??"
                idk = "Imie"/>
                <Wstaw
                tekst="Podaj swoje Nazwisko"
                dom = "Nazwisko"
                idk = "Nazwisko"/>
                <Wstaw
                tekst="Podaj nr telefonu"
                dom = "Nr telefonu"
                idk = "nrTel"/>
                <Wstaw
                tekst="Miejscowo????"
                dom = "Miejscowo????"
                idk = "Miejscowosc"/>
                <Wstaw
                tekst="Ulica"
                dom = "Ulica"
                idk = "Ulica"/>
                <Wstaw
                tekst="Nr Domu"
                dom = "Nr Domu"
                idk = "Nrdomu"/>
                </div>
                <div id="guzikrejestracja">
                <input id="przycisk2" type="submit" value="Zarejestruj" />
                </div>
                </form>
                <Link to="/" id="logowanielink"/>
            


                

                </div>
            
        );
    }
}

export default Form;