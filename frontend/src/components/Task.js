import React from 'react';
import axios from "axios";

const CAR_REST_API_URL = 'http://localhost:8080/api/tasks';

class Task extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            obj: 0,
            isGoing: true,
            numberOfGuests: 2
        };

    }

    componentDidMount(){
        console.log(document.getElementById("select"))
        var pomoc
        axios({
            method: "get",
            url: CAR_REST_API_URL,
            params: {
                pageSize: 5,
                pageNo: 1,
                sortBy: "acceptanceDate"
              }
        })

            .then(function (response) {
                console.log(response.data[1])
                var obj = (response.data);
            })
            .catch(function (response) {
                console.log(response)       
            })
            this.state.obj = pomoc
            console.log(this.state.obj)
    }
    
   


    render() {
        console.log("kkkkkkkkkkkkkkkkkk")
        return (
            <div>
                <select id="select">
                <option id="options" value="1">{document.getElementById("options").value}</option>
            
                </select>
            </div>
        ); 
    }
}

export default Task;