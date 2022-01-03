import React from 'react';
import './Klient.css';

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
            <div id="glownyk">
                <Kratka
                    zdj="history"
                    tekst="Historia usług"
                    url="....."/>
                <Kratka
                    zdj="kalendarz"
                    tekst="Harmonogram"/>
                <Kratka
                    zdj="opinia"
                    tekst="Reklamacja"/>
                <Kratka
                    zdj="visit"
                    tekst="Umówienie wizyty"/>
            </div>
        );
    }
}
export default Pracownik;