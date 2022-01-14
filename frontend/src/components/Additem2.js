// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Additem2.css';


const NEEDED_REST_API_URL = 'http://localhost:8080/api/needed-task-parts';

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




class Additem2 extends React.Component {
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


        data.taskId = sessionStorage.getItem("idtask")
        console.log(data);


        data.taskId = parseInt(data.taskId)
        data.partsAmount = parseInt(data.partsAmount)
        let body = JSON.stringify(data);
        console.log(body);

        // add car to database with post method
        axios({
            method: "post",
            url: NEEDED_REST_API_URL,
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

                <div id="glownyadditem2">
                <Tytul
                tekst="Wpisz przedmiot oraz ilość który jest niezbędny do zakończenia zlecenia"/>
                <Tekst
                tekst="aby dodać potrzebną część proszę uzupełnić poniższe pola:"/>
                <form id="rejestracja2" onSubmit={this.handleSubmit}>
                <div id = "inputy">
                <Wstaw
                tekst="Podaj nazwę cześci"
                dom = "Nazwa"
                idk = "partName"/>
                <Wstaw
                tekst="Podaj Oznaczenie"
                dom = "oznaczenie"
                idk = "partNumbering"/>
                <Wstaw
                tekst="Podaj nr seryjny"
                dom = "Nr seryjny"
                idk = "partSerialNumber"/>
                <Wstaw
                tekst="Podaj Model"
                dom = "Model"
                idk = "carBrand"/>
                <Wstaw
                tekst="Podaj Marke"
                dom = "Marka"
                idk = "carModel"/>
                <Wstaw
                tekst="Podaj Ilość"
                dom = "Ilość"
                idk = "partsAmount"/>
                </div>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wyślij"  />
                </div>
                </form>

            


                

                </div>
            
        );
    }
}

export default Additem2;