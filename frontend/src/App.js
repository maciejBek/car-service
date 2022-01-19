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
import Additem from './components/Additem';
import Additem2 from './components/Additem2';
import Removecar from './components/Removecar';
import Urlop from './components/Urlop';
import Customerhistory from './components/Customerhistory';
import Reklamacja from './components/Reklamacja';
import Reklamacja2 from './components/Reklamacja2';
import Addtask from './components/Addtask';
import Zaplac from './components/Zaplac';



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
        <Route path="/pracownik/additem" element={<Additem/>}></Route>
        <Route path="/pracownik/additem/additem2" element={<Additem2/>}></Route>
        <Route path="/pracownik/removecar" element={<Removecar/>}></Route>
        <Route path="/pracownik/urlop" element={<Urlop/>}></Route>
        <Route path="/klient/customerhistory" element={<Customerhistory/>}></Route>
        <Route path="/klient/harmonogram" element={<Harmonogram/>}></Route>
        <Route path="/klient/reklamacja" element={<Reklamacja/>}></Route>
        <Route path="/klient/reklamacja/reklamacja2" element={<Reklamacja2/>}></Route>
        <Route path="/klient/addtask" element={<Addtask/>}></Route>
        <Route path="/klient/wykonane/zaplac" element={<Zaplac/>}></Route>

        
        
        
        
        </Routes>
      </Router>    
  );
}

export default App;