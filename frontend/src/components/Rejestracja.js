import React from 'react';
import './Rejestracja.css';
import axios from "axios";

const CAR_REST_API_URL = 'http://localhost:8080/api/accounts';

class Rejestracja extends React.Component {

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

        let body = JSON.stringify(data);

        //walidacja
        let haslo1 =document.getElementById('haslo1').value
        let haslo2 =document.getElementById('haslo2').value
        if(haslo1 != haslo2){
            console.log("hasla sie roznia")
            document.getElementById('pasek1').style.border="3px solid red";
            document.getElementById('pasek2').style.border="3px solid red";
            document.getElementById('blad').style.display="inline";

        }else{
            console.log("hasla zgodne")
            document.getElementById('pasek1').style.border="0px solid red";
            document.getElementById('pasek2').style.border="0px solid red";
            document.getElementById('blad').style.display="none";

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
        }

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
                    Rejestracja
                </div>
                <form id="account" onSubmit={this.handleSubmit}>
                <div id="poz">
                    <div className="pasek">
                        <div id="zdj3"></div>
                        <input className="input" type="text" name="emailAddress" placeholder="E-mail" value={this.state.value} onChange={this.handleChange}/>
                    </div>
                    <div className="pasek">
                        <div id="zdj1"></div>
                        <input className="input" type="text" name="username" placeholder="Login" value={this.state.value} onChange={this.handleChange}/>
                    </div>
                    <div className="pasek" id="pasek1">
                        <div id="zdj2"></div>
                        <input className="input" id="haslo1" type="password" name="password" placeholder="Hasło" value={this.state.value} onChange={this.handleChange}/>
                    </div>
                    <div className="pasek" id="pasek2">
                        <div id="zdj4"></div>
                        <input className="input" id="haslo2" type="password" name="password" placeholder="Hasło" value={this.state.value} onChange={this.handleChange}/>
                    </div>
                </div>
                    <div id="blad">
                        podane hasła się różnią!
                    </div>
                <div id="przycisk">
                    <input id="przycisk1" type="submit" value="Zarejestruj się" />
                </div>
                </form>
            </div>
            //</div>
        );
    }
}
export default Rejestracja;