import React from 'react';
import axios from "axios";
import './Harmonogram.css';

const HARMONOGRAM_REST_API_URL = 'http://localhost:8080/api/tasks';


const Poleharmonogram = (values) =>{
    return(
        <div id={values.id}>
            {values.tekst}
        </div>
    )
}



class Harmonogram extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            harmonogram: [],
            cars: [],
            service: [],
            isGoing: true,
            numberOfGuests: 2
        };


    }

    
    componentDidMount(){
        
        const a = axios({
            method: "get",
            url: HARMONOGRAM_REST_API_URL,
            params: {
                pageSize: 20,
                pageNo: 0,
                sortBy: "acceptanceDate"
              }
        })

            const b = a.then(response => {
                this.setState({harmonogram: response.data }) 
                console.log(this.state.harmonogram)
            })
            a.catch(function (response) {
                console.log(response)       
            })

        
    }

   
   

    render() {
        return (
            <div id="contenerharmonogram">
                <div id="harmonogramtekst1">
               Harmonogram pozwoli na zobrazownianie usług które są wykonywane:
               </div>
               <div id="contenerharmonogramid1">
                    <Poleharmonogram
                    id="imieharmonogram"
                    tekst="Imię"/>
                    <Poleharmonogram
                    id="nazwiskoharmonogram"
                    tekst="Nazwisko"/>
                    <Poleharmonogram
                    id="markaharmonogram"
                    tekst="Marka"/>
                    <Poleharmonogram
                    id="modelharmonogram"
                    tekst="Model"/>
                    <Poleharmonogram
                    id="uslugaharmonogram"
                    tekst="Usługa"/>
                    <Poleharmonogram
                    id="opisharmonogram"
                    tekst="Opis Problemu"/>
                    <Poleharmonogram
                    id="ouslugaharmonogram"
                    tekst="Opis Usługi"/>
                    <Poleharmonogram
                    id="dataprzharmonogram"
                    tekst="Data przyjęcia"/>
                    <Poleharmonogram
                    id="dataukharmonogram"
                    tekst="Data ukończenia"/>

               </div>
               {this.state.harmonogram.map(el =>(
                        <div id={"contenerharmonogram"+el.id}>
                        <Poleharmonogram
                        id="imieharmonogram"
                        tekst={el.customerName}/>
                        <Poleharmonogram
                        id="nazwiskoharmonogram"
                        tekst={el.customerSurname}/>
                        <Poleharmonogram
                        id="markaharmonogram"
                        tekst={el.brand}/>
                        <Poleharmonogram
                        id="modelharmonogram"
                        tekst={el.model}/>
                        <Poleharmonogram
                        id="uslugaharmonogram"
                        tekst={el.serviceName}/>
                        <Poleharmonogram
                        id="opisharmonogram"
                        tekst={el.problemDescription}/>
                        <Poleharmonogram
                        id="ouslugaharmonogram"
                        tekst={el.serviceDescription}/>
                        <Poleharmonogram
                        id="dataprzharmonogram"
                        tekst={el.acceptanceDate}/>
                        <Poleharmonogram
                        id="dataukharmonogram"
                        tekst={el.completionDate}/>
    
                   </div>
                ))}
               


            </div>
        ); 
    }
}

export default Harmonogram;