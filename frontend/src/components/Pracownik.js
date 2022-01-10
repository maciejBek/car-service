import React from 'react';
import './Pracownik.css';
import { Link } from "react-router-dom"

const Kratka = (values) =>{
    return(
        <div id="kratka" onClick={()=>{ document.getElementById(values.id).click()}}>
            <div className="obrazek" id={values.zdj}></div>
            {values.tekst}
            <Link id={values.id} to={values.uuu}></Link>
        </div>
    )
}


class Pracownik extends React.Component {
    constructor(props) {
        super(props);
    }


    render() {
        return (
            <div id="glowny">
                <Kratka
                    id="opcja1"
                    zdj="wrench"
                    tekst="Nowe zlecenie"
                    uuu="task"/>
                <Kratka
                    id="opcja2"
                    zdj="kalendarz"
                    tekst="Harmonogram"
                    uuu="harmonogram"/>
                <Kratka
                    id="opcja3"
                    zdj="add"
                    tekst="Dodawanie klienta"
                    uuu="addcustomer"/>
                <Kratka
                    id="opcja4"
                    zdj="add1"
                    tekst="Dodawanie samochodu"
                    uuu="addcar"/>
                <Kratka
                    id="opcja5"
                    zdj="done"
                    tekst="Oznacz jako wykonane"
                    uuu="wykonane"/>
                <Kratka
                    id="opcja6"
                    zdj="urlop"
                    tekst="Urlop"
                    uuu="task"/>
                <Kratka
                    id="opcja7"
                    zdj="add2"
                    tekst="Dodanie przedmiotu"
                    uuu="task"/>
                <Kratka
                    id="opcja8"
                    zdj="remove"
                    tekst="Usunięcie przedmiotu"
                    uuu="task"/>
            </div>
        );
    }
}
export default Pracownik;