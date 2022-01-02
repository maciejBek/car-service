import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router} from 'react-router-dom'
//import Form from './components/Form'
import Logowanie from './components/Logowanie';
//import Rejestracja from './components/Rejestracja';
//import Pracownik from './components/Pracownik';
require('react-dom');
window.React2 = require('react');
console.log(window.React1 === window.React2);


function App() {
  return (
      <Router>
        
        <Logowanie />
        
        
      </Router>    
  );
}

export default App;