// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Reklamacja2.css';


const COMPLAINTS_REST_API_URL = 'http://localhost:8080/api/complaints';

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




class Reklamacja2 extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isGoing: true,
            numberOfGuests: 2
        };

        this.wyslij = this.wyslij.bind(this);

    }


    wyslij(event) {
        // getting data from form and putting to json string to body array
        var id = sessionStorage.getItem("id")
        var opis = document.getElementById("tekstreklamacja2").value

        

        var pojemnik = {}
        pojemnik.taskId = parseInt(id)
        pojemnik.description = opis


        let body = JSON.stringify(pojemnik);
        console.log(body);

        // add car to database with post method
        axios({
            method: "post",
            url: COMPLAINTS_REST_API_URL,
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

                <div id="glownyreklamacja2">
                    <div>
                    Wprowadź treść reklamacji:
                    </div>
                    <div>
                    <textarea id="tekstreklamacja2" name="problemDescription" rows="5" cols="33" placeholder="Opis reklamacji...">
                    
                    </textarea>

                    </div>
                
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wyślij" onClick={this.wyslij} />
                </div>
                

            


                

                </div>
            
        );
    }
}

export default Reklamacja2;