import React from 'react';
import './Logowanie.css';

class Logowanie extends React.Component {
    render() {
        return (
            //<div id="strona">
              //  <div id="pole" >
                //    <div id="logo"></div>
                //</div>
            <div id="logowanie">
                <div className="text">
                Logowanie
                </div>
                <div id="poz">
                <div className="pasek">
                    <div id="zdj1"></div>
                    <input className="input" type="text" name="nazwa" placeholder="Login"/>
                </div>
                <div className="pasek">
                    <div id="zdj2"></div>
                    <input className="input" type="text" name="nazwa" placeholder="Hasło"/>
                </div>
                </div>
                <div id="przycisk">
                    <input id="przycisk1" type="submit" value="Zaloguj" />
                </div>
                <div id="rej">Nie masz jeszcze konta? <a id="reje" href="...">Dołącz do nas </a></div>
            </div>
            //</div>
        );
    }
}
export default Logowanie;