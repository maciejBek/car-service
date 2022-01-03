import React from 'react';
import './Pracownik.css';

const Kratka = (values) =>{
    return(
        <div id="kratka" onClick={next}>
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
            <div id="glowny">
                <Kratka
                    zdj="wrench"
                    tekst="Nowe zlecenie"
                    url="....."/>
                <Kratka
                    zdj="kalendarz"
                    tekst="Harmonogram"/>
                <Kratka
                    zdj="add"
                    tekst="Dodawanie klienta"/>
                <Kratka
                    zdj="add1"
                    tekst="Dodawanie samochodu"/>
                <Kratka
                    zdj="done"
                    tekst="Oznacz jako wykonane"/>
                <Kratka
                    zdj="urlop"
                    tekst="Urlop"/>
                <Kratka
                    zdj="add2"
                    tekst="Dodanie przedmiotu"/>
                <Kratka
                    zdj="remove"
                    tekst="Usunięcie przedmiotu"/>
            </div>
        );
    }
}
export default Pracownik;