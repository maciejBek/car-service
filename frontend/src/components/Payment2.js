 import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Payment2.css';


const CAR_REST_API_URL = 'http://localhost:8080/api/bills';

const Tytul = (values) =>{
    return(
        <div id="Tytul" >
            {values.tekst} {values.cena} zł
        </div>
    )
}

const Tranzakcja1 = (values) =>{
    return(
                <div id="pierwszy">
                    wybierz typ transakcji
                    <select id="transakcja" name="carId">
                        <option value="Blik" >BLIK</option>
                        <option value="Przelew" >Przelew</option>
                        <option value="Gotówka" >Przelewy 24</option>
                    </select>
                </div>
    )
}

const Tranzakcja2 = (values) =>{
    return(
                <div id="pierwszy">
                    wybierz typ transakcji
                    <select id="transakcja" name="carId">
                        <option value="Gotówka" >Gotówka</option>
                    </select>
                </div>
    )
}






class Payment2 extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cena: 0,
            pokaz: true,
            isGoing: true,
            numberOfGuests: 2
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.wybor = this.wybor.bind(this);
        this.dalej = this.dalej.bind(this);

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
            })
            .catch(function (response) {
                //handle error
                console.log(response);
                sessionStorage.clear();
            });

        event.preventDefault();
    }

    wybor(){
        var wartosc
        wartosc = document.getElementById("platnosc").value
        console.log(wartosc)


        if(document.getElementById("platnosc").value == "Przelew"){
            this.setState({pokaz: true })
        }else{
            this.setState({pokaz: false})
        }
        console.log(this.state.pokaz)

        
    }

    componentDidMount(){
        
       console.log("działa")
       var taskId
       taskId = sessionStorage.getItem("id")
       console.log(taskId)

       var data = {}
       data.taskId = parseInt(taskId)

       let body = JSON.stringify(data);

       console.log(body)

       var urll 
       urll = 'http://localhost:8080/api/bills/price'

       const a = axios({
        method: "post",
        url: urll,
        data: body,
        headers: {"Content-Type": "application/JSON"},
        
        })
        const b = a.then(response => {
            console.log(response.data)
            this.setState({cena: response.data.amount })
        })
        a.catch(function (response) {
            console.log(response)       
        })
        
    }

    dalej(){
        document.getElementById('klientlink').click();
    }



    render() {
        return (

                <div id="glownypayment2">
                <Tytul
                tekst="Do zapłaty:"
                cena = {this.state.cena} /> 
                
                <div id="pierwszy">
                    wybierz metode płatności
                    <select id="platnosc" name="carId" onInput={this.wybor}>
                        <option value="Przelew" >Przelew</option>
                        <option value="Gotówka" >Gotówka</option>
                    </select>
                </div>

                { this.state.pokaz == true ? <Tranzakcja1/> : <Tranzakcja2/> }

                
                <div id="przyciskpayment2">
                    <input id="przycisk2" type="submit" value="Dalej" onClick={this.dalej}/>
                </div>

                <Link to="/klient" id="klientlink"/>

            


                

                </div>
            
        );
    }
}

export default Payment2;