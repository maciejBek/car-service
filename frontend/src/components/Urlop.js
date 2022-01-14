import React from 'react';
import axios from "axios";
import './Urlop.css';

const VACATIONS_REST_API_URL = 'http://localhost:8080/api/accounts/vacations';


class Urlop extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isGoing: true,
            numberOfGuests: 2
        };
        this.urlop = this.urlop.bind(this);
    }

    urlop(){
        var id = sessionStorage.getItem("id")

        let datarozp = document.getElementById('daterozppole').value;
        let datazak = document.getElementById('datezakpole').value;

        var date={}
        date.startDate = datarozp
        date.endDate = datazak
        date.accountId = id
        date.accountId = parseInt(date.accountId)
        console.log(date)

        let body = JSON.stringify(date);
        console.log(body);

        axios({
            method: "post",
            url: VACATIONS_REST_API_URL,
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



    }

    
    render() {
        return (
            <div id="contenerremovecar">
                <div id="teksttask">
                Wybierz date rozpoczęcia uropu oraz jego końca:
                </div>

                <div id="urlopcont">
                    <div id="daterozp">
                        <div id="daterozptekst">
                            Podaj date rozpoczęcia urlopu:
                        </div>
                    <input id="daterozppole" type="date" />
                    </div>

                    <div id="datezak">
                        <div id="datezaktekst">
                            Podaj date zakończenia urlopu:
                        </div>
                    <input id="datezakpole" type="date" />
                    </div>

                </div>
               
                <div id="taskbutton">
                <input id="przycisk2" onClick={this.urlop} type="submit" value="Wyślij"  />
                </div>

            </div>
        ); 
    }
}

export default Urlop;