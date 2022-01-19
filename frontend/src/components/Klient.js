import React from 'react';
import './Klient.css';
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
            <div id="glownyk">
                <Kratka
                    id="opcja1"
                    zdj="history"
                    tekst="Historia usług"
                    uuu="customerhistory"/>
                <Kratka
                    id="opcja2"
                    zdj="kalendarz"
                    tekst="Harmonogram"
                    uuu="harmonogram"/>
                <Kratka
                    id="opcja3"
                    zdj="opinia"
                    tekst="Reklamacja"
                    uuu="reklamacja"/>
                <Kratka
                    id="opcja4"
                    zdj="visit"
                    tekst="Umówienie wizyty"
                    uuu="addtask"/>
            </div>
        );
    }
}
export default Pracownik;