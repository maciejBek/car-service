import React from 'react';
import axios from "axios";

const HARMONOGRAM_REST_API_URL = 'http://localhost:8080/api/tasks/customer/';


const Poleharmonogram = (values) =>{
    return(
        <div id={values.id}>
            {values.tekst}
        </div>
    )
}



class Customerhistory extends React.Component {

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
        
        var id = sessionStorage.getItem("id")
        console.log(id)

        var urll = 'http://localhost:8080/api/tasks/customer/'+id
        console.log(urll)

        const a = axios({
            method: "get",
            url: urll
            
        })

            const b = a.then(response => {
                console.log(response.data)
                
                var obj = response.data
                // for(var i=0; i<=(obj.length-1); i++){
                //     obj[i].completionDate = obj[i].completionDate.slice(1,10)
                //     obj[i].acceptanceDate = obj[i].acceptanceDate.slice(1,10)
                // }
            
                this.setState({harmonogram: obj }) 
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
               Zlecenia które zostały wykonane:
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
                    tekst="Data wykonania"/>

               </div>
               {this.state.harmonogram.map(el =>(
                        <div id={"contenerharmonogram"+el.taskId}>
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

export default Customerhistory;