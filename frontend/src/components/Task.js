import React from 'react';
import axios from "axios";

const CAR_REST_API_URL = 'http://localhost:8080/api/tasks';

class Task extends React.Component {

    constructor(props) {
        super(props);
        this.pobranie = this.pobranie.bind(this);
        this.state = {
            isGoing: true,
            numberOfGuests: 2
        };

    }

    pobranie() {
        console.log("dziala")
     // add car to database with post method
        axios({
            method: "get",
            url: CAR_REST_API_URL,
            params: {
                pageSize: 5,
                pageNo: 1,
                sortBy: "acceptationDate"
              }
        })

            .then(function (response) {
                console.log(response.data)
            })
            .catch(function (response) {
                console.log(response)       
            })

    }


    render() {
        return (
            
            1
        ); 
    }
}

export default Task;