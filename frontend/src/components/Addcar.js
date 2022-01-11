import React from 'react';
import axios from "axios";
import './Addcar.css';

const CUSTOMER_REST_API_URL = 'http://localhost:8080/api/customers';


const Wstaw = (values) =>{
    return(
        <div id="inputaddcar">
        {values.tekst}
        <input type="text" className="inputr" placeholder={values.dom} id="power" name={values.idk} />
        </div>
    )
}

class Addcar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            obj: [],
            cars: [],
            service: [],
            isGoing: true,
            numberOfGuests: 2
        };

    }

   
    componentDidMount(){
        
        const a = axios({
            method: "get",
            url: CUSTOMER_REST_API_URL,
            params: {
                pageSize: 100,
                pageNo: 0,
                sortBy: "acceptanceDate"
              }
        })

            const b = a.then(response => {
                this.setState({obj: response.data }) 
                
            })
            a.catch(function (response) {
                console.log(response)       
            })

        
    }

  


   

    render() {
        return (
            <div id="contener">
                <div id="Tytul">
                Aby dodać Samochód musisz najpierw wybrać klienta do którego będzie należał samochód:
                </div>

                <div id="wyboryaddcar">
                <div id="customercont">
                <div id="teksttask1">
                Wybierz klienta:
                </div>
                
                <select id="select" name="customerId" onInput={this.samochody}>
                <option value="" disabled selected>Klenci</option>
                    {this.state.obj.map(el =>(
                        <option value={el.id}>{el.name}{" "}{el.surname}</option>
                    ))}
                </select>
                </div>
                </div>
                <div id="tekstaddcar">
                    Następnie proszę wypełnić pola:
                </div>
                <Wstaw
                tekst="Podaj Marke"
                dom = "Marka"
                idk = "Imie"/>
                <Wstaw
                tekst="Podaj Model"
                dom = "Model"
                idk = "Imie"/>
                 <Wstaw
                tekst="Rocznik"
                dom = "Rocznik"
                idk = "Imie"/>
                <Wstaw
                tekst="Podaj Nr Vin"
                dom = "Nr Vin"
                idk = "Imie"/>
                 <Wstaw
                tekst="Podaj Nr Rejestracyjny"
                dom = "Nr Rejestracyjny"
                idk = "Imie"/>
                <Wstaw
                tekst="Podaj Pojemność"
                dom = "Pojemność"
                idk = "Imie"/>
                 <Wstaw
                tekst="Podaj Rodzaj Paliwa"
                dom = "Rodzaj Paliwa"
                idk = "Imie"/>
                <Wstaw
                tekst="Podaj Moc"
                dom = "Moc"
                idk = "Imie"/>
                 <Wstaw
                tekst="Podaj Moment Obrotowy"
                dom = "Moment obrotowy"
                idk = "Imie"/>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wyślij"  />
                </div>

            </div>
        ); 
    }
}

export default Addcar;