// import { Link } from "react-router-dom"
import React from 'react';
import axios from 'axios';
import './Rejestracja2.css';
import Rejestracja from './Rejestracja';

const CAR_REST_API_URL = 'http://localhost:8080/api/cars';

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
        <input type="text" className="inputr" placeholder={values.dom} id="power" name="power" />
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

    constructor(props){
        super(props);
        this.state={
            value:this.props.location.state,
        }

    }

    render() {
        return (
            <form id="Rejestracja2" onSubmit={console.log("klik")}>
                <div id="glownyr">
                <Tytul
                tekst="Witaj w ostanim etapie rejestracji !"/>
                <Tekst
                tekst="aby ukończyć rejestracie musisz wypełnić jeszcze kilka informacji na swój temat. Pozwoli nam to na spersonalizowanie twojego konta"/>
                <div id = "inputy">
                <Wstaw
                tekst="Podaj swoje imię"
                dom = "Imię"/>
                <Wstaw
                tekst="Podaj swoje Nazwisko"
                dom = "Nazwisko"/>
                <Wstaw
                tekst="Podaj nr telefonu"
                dom = "Nr telefonu"/>
                <Wstaw
                tekst="Miejscowość"
                dom = "Miejscowość"/>
                <Wstaw
                tekst="Ulica"
                dom = "Ulica"/>
                <Wstaw
                tekst="Nr Domu"
                dom = "Nr Domu"/>
                </div>
                <Przycisk/>

                {console.log(this.props.location.state.id)}


                

                </div>
            </form>
        );
    }
}

export default Form;