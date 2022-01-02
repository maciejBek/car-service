import React from 'react';
import './Logowanie.css';
import axios from "axios";
import { Link } from 'react-router-dom';

const CAR_REST_API_URL = 'http://localhost:8080/api/accounts/login';

const Linkk = () =>{
    return(
        <Link to="/123">Dołącz do nas</Link>
    )
}

class Logowanie extends React.Component {

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
        let account = document.getElementById('account');
        let formData = new FormData(account);


        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });
            console.log(data)
        document.getElementById('pasek0').style.border = "0px solid red";
        document.getElementById('pasek1').style.border = "0px solid red";
        document.getElementById('blad').style.display = "none";
        document.getElementById('dobrze').style.display = "none";

        let body = JSON.stringify(data);

                // add car to database with post method
                axios({
                    method: "post",
                    url: CAR_REST_API_URL,
                    data: body,
                    headers: {"Content-Type": "application/JSON"},
                })

                    .then(function (response) {
                        document.getElementById('dobrze').style.display = "inline";
                    })
                    .catch(function (response) {
                        const error =(response.response.data);
                        console.log(error.error)
                        document.getElementById('pasek0').style.border="3px solid red";
                        document.getElementById('pasek1').style.border="3px solid red";
                        document.getElementById('blad').style.display = "inline";
                    }).catch(function (response) {
                    //handle error
                    console.log(response);
                    });

        event.preventDefault();
    }


    render() {
        return (
            //<div id="strona">
              //  <div id="pole" >
                //    <div id="logo"></div>
                //</div>
            <div id="logowanie">
                <div className="text">
                Logowanie
                </div>
                <form id="account" onSubmit={this.handleSubmit}>
                <div id="poz">
                <div className="pasek" id="pasek0">
                    <div id="zdj1"></div>
                    <input className="input" type="text" name="username" placeholder="Login"/>
                </div>
                <div className="pasek" id="pasek1">
                    <div id="zdj2"></div>
                    <input className="input" type="password" name="password" placeholder="Hasło"/>
                </div>
                </div>
                    <div id="dobrze">
                        zalogowano!
                    </div>
                    <div id="blad">
                        Wprowadzono zle dane!
                    </div>
                <div id="przycisk">
                    <input id="przycisk1" type="submit" value="Zaloguj" />
                </div>
                <div id="rej">Nie masz jeszcze konta?

                    
                    <a id="reje" href="123">Dołącz do nas </a>
                    
                
                </div>
                </form>
            </div>
            //</div>
        );
    }
}
export default Logowanie;