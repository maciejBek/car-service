import React from 'react';
import axios from "axios";

const CUSTOMER_REST_API_URL = 'http://localhost:8080/api/customers';
const TASK_REST_API_URL = 'http://localhost:8080/api/tasks';

class Task extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            obj: [],
            isGoing: true,
            numberOfGuests: 2
        };

    }

    componentDidMount(){
        console.log(document.getElementById("select"))
        console.log(this.state.obj)
        
        const a = axios({
            method: "get",
            url: CUSTOMER_REST_API_URL,
            params: {
                pageSize: 5,
                pageNo: 0,
                sortBy: "acceptanceDate"
              }
        })

            const b = a.then(response => {
                console.log(response.data)
                this.setState({obj: response.data }) 
                console.log(this.state.obj)
                
            })
            a.catch(function (response) {
                console.log(response)       
            })

            console.log(this.state.obj)
    }
    
    doSomething(data) {
       this.state.obj = data
       console.log(this.state.obj)
    }


    render() {
        return (
            <div>
                <select id="select">
                    <option value="" disabled selected></option>
                    {this.state.obj.map(el =>(
                        <option>{el.name}{" "}{el.surname}</option>
                    ))}
                </select>
            </div>
        ); 
    }
}

export default Task;