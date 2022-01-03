import React from 'react';
import './Administrator.css';

const Kratka = (values) =>{
    return(
        <div id="kratkaa" onClick={next}>
            <div className="obrazek" id={values.zdj}></div>
            {values.tekst}
        </div>
    )
}

const next = () =>{
    console.log("patataja")
}

class Pracownik extends React.Component {
    constructor(props) {
        super(props);
    }


    render() {
        return (
            <div id="glownya">
                <Kratka
                    zdj="employee"
                    tekst="Dodanie pracownika"
                    url="....."/>
                <Kratka
                    zdj="fired"
                    tekst="Usunięcie pracownika"/>
                <Kratka
                    zdj="remove"
                    tekst="Usunięcia pojazdu"/>
                <Kratka
                    zdj="target"
                    tekst="Usunięcie klienta"/>
                <Kratka
                    zdj="changes"
                    tekst="Modyfikacja danych użytkownika"/>
                <Kratka
                    zdj="order"
                    tekst="Modyfikacja danych kont"/>
                <Kratka
                    zdj="pencil"
                    tekst="Modyfikacja rodzaju usługi"/>
                <Kratka
                    zdj="money"
                    tekst="Modyfykacja pensji pracownika"/>
                <Kratka
                    zdj="customer-service"
                    tekst="Dodanie nowego rodzaju usługi"/>
                <Kratka
                    zdj="headphone"
                    tekst="Usunięcie danego rodzaju usługi"/>
            </div>
        );
    }
}
export default Pracownik;