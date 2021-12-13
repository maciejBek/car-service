import React from 'react';
import './Rejestracja.css';

class Rejestracja extends React.Component {
    render() {
        return (
            //<div id="strona">
            //  <div id="pole" >
            //    <div id="logo"></div>
            //</div>
            <div id="logowanie">
                <div className="text">
                    Rejestracja
                </div>
                <div id="poz">
                    <div className="pasek">
                        <div id="zdj3"></div>
                        <input className="input" type="text" name="nazwa" placeholder="E-mail"/>
                    </div>
                    <div className="pasek">
                        <div id="zdj1"></div>
                        <input className="input" type="text" name="nazwa" placeholder="Login"/>
                    </div>
                    <div className="pasek">
                        <div id="zdj2"></div>
                        <input className="input" type="text" name="nazwa" placeholder="Hasło"/>
                    </div>
                    <div className="pasek">
                        <div id="zdj4"></div>
                        <input className="input" type="text" name="nazwa" placeholder="Powtórz Hasło"/>
                    </div>
                </div>
                <div id="przycisk">
                    <input id="przycisk1" type="submit" value="Zarejestruj się" />
                </div>
            </div>
            //</div>
        );
    }
}
export default Rejestracja;