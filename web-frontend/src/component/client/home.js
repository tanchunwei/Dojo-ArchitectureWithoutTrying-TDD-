import React from 'react';
import './../../App.css';
import {useSelector, useDispatch} from 'react-redux'
import {clientAuthAction} from './../../redux/actions/clientAuthAction'

function Home(){
    const dispatch = useDispatch();
    const clientAuth = useSelector(state => state.clientAuth);

    const login = () => {
        var cid = document.getElementById('cid').value;
        dispatch(clientAuthAction(cid));
    };

    return (
        <div>
            <h3>Client Home Page</h3>
            {clientAuth.isAuthenticated && (
                <h3>Welcome {clientAuth.clientID}</h3>
            )}
            {!clientAuth.isAuthenticated && (
            <form onSubmit={login}>
                <label>
                    Client ID: &nbsp;
                    <input type="text" id = "cid" />
                </label>
                <input type="button" value="submit" onClick={login}/>
            </form>
            )}
        </div>
    );
}

export default Home;