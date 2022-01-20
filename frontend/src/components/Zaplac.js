// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Zaplac.css';
import { Link } from "react-router-dom"


const CAR_REST_API_URL = 'http://localhost:8080/api/bills';

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




class Zaplac extends React.Component {
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
        var id
        id = sessionStorage.getItem("taskid")
        console.log(id)
        // getting data from form and putting to json string to body array
        let rere = document.getElementById('rejestracja2');
        let formData = new FormData(rere);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });

        data.taskId = parseInt(id)
        data.amount = parseInt(data.amount)
        
        let body = JSON.stringify(data);
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

                <div id="glownyzaplac">
                <Tytul
                tekst="Wprowadź cene wykonanej usługi:"/>
                
                <form id="rejestracja2" onSubmit={this.handleSubmit}>
                <div id = "inputy">
                <Wstaw
                tekst="Podaj cene:"
                dom = "Cena"
                idk = "amount"/>
                
                </div>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wyślij"  />
                </div>
                </form>
                <Link to="/pracownik" id="pracowniklink"/>

            


                

                </div>
            
        );
    }
}

export default Zaplac;