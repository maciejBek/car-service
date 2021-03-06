// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Addcustomer.css';
import { Link } from "react-router-dom"


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
                document.getElementById('pracowniklink').click();
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

                <div id="glownyaddcustomer">
                <Tytul
                tekst="Opcja dodanie Klienta pozwoli utworzy?? nowe konto klienta oraz przypisa?? mu dane:"/>
                <Tekst
                tekst="aby doda?? nowego klienta prosz?? uzupe??ni?? poni??sze pola:"/>
                <form id="rejestracja2" onSubmit={this.handleSubmit}>
                <div id = "inputy">
                <Wstaw
                tekst="Podaj login"
                dom = "Login"
                idk = "username"/>
                <Wstaw
                tekst="Podaj E-mail"
                dom = "E-mail"
                idk = "emailAddress"/>
                <Wstaw
                tekst="Podaj Has??o"
                dom = "Has??o"
                idk = "password"/>
                <Wstaw
                tekst="Podaj swoje imi??"
                dom = "Imi??"
                idk = "name"/>
                <Wstaw
                tekst="Podaj swoje Nazwisko"
                dom = "Nazwisko"
                idk = "surname"/>
                <Wstaw
                tekst="Podaj nr telefonu"
                dom = "Nr telefonu"
                idk = "phoneNumber"/>
                <Wstaw
                tekst="Miejscowo????"
                dom = "Miejscowo????"
                idk = "town"/>
                <Wstaw
                tekst="Ulica"
                dom = "Ulica"
                idk = "street"/>
                <Wstaw
                tekst="Nr Domu"
                dom = "Nr Domu"
                idk = "number"/>
                </div>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wy??lij"  />
                </div>
                </form>
                <Link to="/pracownik" id="pracowniklink"/>
            


                

                </div>
            
        );
    }
}

export default Form;