import React from 'react';
import axios from "axios";
import './Addcar.css';

const CUSTOMER_REST_API_URL = 'http://localhost:8080/api/customers';
const CARS_REST_API_URL = 'http://localhost:8080/api/cars';


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
        let rere = document.getElementById('rejestracja2');
        let idcustomer = document.getElementById('select').value;
        let formData = new FormData(rere);

        var data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });
        console.log(data);
        data.year = parseInt(data.year)
        data.capacity = parseInt(data.capacity)
        data.power = parseInt(data.power)
        data.tourqe = parseInt(data.tourqe)

        data.customerId = parseInt(idcustomer)
        

        let body = JSON.stringify(data);
        console.log(body);

        // add car to database with post method
        axios({
            method: "post",
            url: CARS_REST_API_URL,
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
                
            });

        event.preventDefault();
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
                <form id="rejestracja2" onSubmit={this.handleSubmit}>
                <Wstaw
                tekst="Podaj Marke"
                dom = "Marka"
                idk = "model"/>
                <Wstaw
                tekst="Podaj Model"
                dom = "Model"
                idk = "brand"/>
                 <Wstaw
                tekst="Rocznik"
                dom = "Rocznik"
                idk = "year"/>
                <Wstaw
                tekst="Podaj Nr Vin"
                dom = "Nr Vin"
                idk = "vinNumber"/>
                 <Wstaw
                tekst="Podaj Nr Rejestracyjny"
                dom = "Nr Rejestracyjny"
                idk = "registrationNumber"/>
                <Wstaw
                tekst="Podaj Pojemność"
                dom = "Pojemność"
                idk = "capacity"/>
                 <Wstaw
                tekst="Podaj Rodzaj Paliwa"
                dom = "Rodzaj Paliwa"
                idk = "fuelType"/>
                <Wstaw
                tekst="Podaj Moc"
                dom = "Moc"
                idk = "power"/>
                 <Wstaw
                tekst="Podaj Moment Obrotowy"
                dom = "Moment obrotowy"
                idk = "tourqe"/>
                <div id="taskbutton">
                <input id="przycisk2" type="submit" value="Wyślij"  />
                </div>
                </form>
            </div>
        ); 
    }
}

export default Addcar;