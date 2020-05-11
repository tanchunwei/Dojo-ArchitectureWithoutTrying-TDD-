import React from 'react';
import './../App.css';
import {Link} from 'react-router-dom'
import Button from 'react-bootstrap/Button'
import 'bootstrap/dist/css/bootstrap.min.css'

function Home(){

    return (
        <div>
            <h3>Home Page</h3>
            <Link to="/client/">
                <Button variant="primary" size="lg">Client</Button>
            </Link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/host/">
            <Button variant="primary" size="lg">Host</Button>
            </Link>
        </div>
    );
}

export default Home;