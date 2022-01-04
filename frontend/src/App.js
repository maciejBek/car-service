import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router,Route, Routes} from 'react-router-dom'
import Form from './components/Form'
import Logowanie from './components/Logowanie';
import Rejestracja from './components/Rejestracja';
import Rejestracja2 from './components/Rejestracja2';
import Pracownik from './components/Pracownik';
import Klient from './components/Klient';
import Administrator from './components/Administrator';

function App() {
  return (
      <Router>
        <Routes>
        <Route exact path="/" element={<Logowanie/>}></Route>
        <Route path="/Rejestracja" element={<Rejestracja/>}></Route>
        <Route path="/Rejestracja2" element={<Rejestracja2/>}></Route>
        <Route path="/Pracownik" element={<Pracownik/>}></Route>
        <Route path="/Form" element={<Form/>}></Route>
        <Route path="/Klient" element={<Klient/>}></Route>
        <Route path="/Administrator" element={<Administrator/>}></Route>
        
        
        
        
        </Routes>
      </Router>    
  );
}

export default App;