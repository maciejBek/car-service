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
import Task from './components/Task';
import Harmonogram from './components/Harmonogram';
import Addcustomer from './components/Addcustomer';
import Addcar from './components/Addcar';
import Wykonane from './components/Wykonane';



function App() {
  return (
      <Router>
        <Routes>
        <Route exact path="/" element={<Logowanie/>}></Route>
        <Route path="/Rejestracja" element={<Rejestracja/>}></Route>
        <Route path="/rejestracja2" element={<Rejestracja2/>}></Route>
        <Route path="/pracownik" element={<Pracownik/>}></Route>
        <Route path="/Form" element={<Form/>}></Route>
        <Route path="/klient" element={<Klient/>}></Route>
        <Route path="/admin" element={<Administrator/>}></Route>
        <Route path="/pracownik/task" element={<Task/>}></Route>
        <Route path="/pracownik/harmonogram" element={<Harmonogram/>}></Route>
        <Route path="/pracownik/addcustomer" element={<Addcustomer/>}></Route>
        <Route path="/pracownik/addcar" element={<Addcar></Addcar>}></Route>
        <Route path="/pracownik/wykonane" element={<Wykonane/>}></Route>
        
        
        
        
        </Routes>
      </Router>    
  );
}

export default App;