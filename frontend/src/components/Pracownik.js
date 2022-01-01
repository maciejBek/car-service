import React from 'react';
import './Pracownik.css';

const Kratka = (values) =>{
    return(
        <div id="kratka">
            <div className="obrazek" id={values.zdj}></div>
            {values.tekst}
        </div>
    )
}

class Pracownik extends React.Component {


    render() {
        return (
            <div id="glowny">
                <Kratka
                    zdj="wrench"
                    tekst="Usługi"/>
                <Kratka
                    zdj="kalendarz"
                    tekst="Harmonogram"/>
                <Kratka
                    zdj="add"
                    tekst="Dodawanie klienta"/>
                <Kratka
                    tekst="Harmonogram"/>
            </div>
        );
    }
}
export default Pracownik;