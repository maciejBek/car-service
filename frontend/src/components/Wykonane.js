import React from 'react';
import axios from "axios";
import { Link } from "react-router-dom"
import './Wykonane.css';

const HARMONOGRAM_REST_API_URL = 'http://localhost:8080/api/tasks';


const Poleharmonogram = (values) =>{
    return(
        <div id={values.id}>
            {values.tekst}
        </div>
    )
}



class Wykonane extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            harmonogram: [],
            cars: [],
            service: [],
            isGoing: true,
            numberOfGuests: 2
        };

        this.wyslij = this.wyslij.bind(this);
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
                
                var obj2 = []
                var obj = response.data
                for(var i=0; i<=(obj.length-1); i++){
                    if(obj[i].completionDate == null){
                        obj2[i]=obj[i]
                    }
                }

                console.log(obj2)
                

                this.setState({harmonogram: obj2 }) 
            })
            a.catch(function (response) {
                console.log(response)       
            })

        
    }

    wyslij(idtask){
        console.log(idtask)

        var urll = 'http://localhost:8080/api/tasks/'+idtask
        console.log(urll)
        sessionStorage.setItem("taskid", idtask)
        var data = {};
        var day = new Date(); 
        data.completionDate = day.toISOString()
        let body = JSON.stringify(data);
        console.log(body)

        const a = axios({
            headers: { "Content-Type": "application/JSON" },
            method: "patch",
            url: urll,
            data: body
        })

            const b = a.then(response => {
                console.log(response)
                this.componentDidMount()
                document.getElementById('zaplac').click();
                ;
            })
            a.catch(function (response) {
                console.log(response)       
            })

    }

   
   

    render() {
        return (
            <div id="contenerharmonogram">
                <div id="harmonogramtekst1">
               Oznacz zadanie które zostało wykonane:
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
                    tekst="Oznacz jako wykonane"/>

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
                        tekst={el.acceptanceDate.slice(0,10)}/>
                        
                        <div id="wykonanepole">
                        <input id="przycisk2" type="submit" onClick={()=> {this.wyslij(el.taskId)}} value="Wykonane"  />
                        <Link to="zaplac" id="zaplac"/>
                        </div>
    
                   </div>
                ))}
               


            </div>
        ); 
    }
}

export default Wykonane;