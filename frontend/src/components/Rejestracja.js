import React from 'react';
import './Rejestracja.css';
import axios from "axios";

const CAR_REST_API_URL = 'http://localhost:8080/api/accounts/register';

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
        let password1 = document.getElementById('haslo1').innerHTML

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });

        console.log(password1)


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

                .then(function (response) {
                    //handle success
                })
                .catch(function (response) {
                    console.log(response.response.data);
                }) .catch(function (response) {
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
                    <div className="pasek">
                        <div id="zdj2"></div>
                        <input className="input" id="haslo1" type="password" name="password" placeholder="Hasło" value={this.state.value} onChange={this.handleChange}/>
                    </div>
                    <div className="pasek">
                        <div id="zdj4"></div>
                        <input className="input" id="haslo2" type="password" name="password" placeholder="Hasło" value={this.state.value} onChange={this.handleChange}/>
                    </div>
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