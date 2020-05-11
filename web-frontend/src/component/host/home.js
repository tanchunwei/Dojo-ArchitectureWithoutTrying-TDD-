import React from 'react';
import './../../App.css';
import {useSelector, useDispatch} from 'react-redux'
import {hostAuthAction} from './../../redux/actions/hostAuthAction'

function Home(){
    const dispatch = useDispatch();
    const hostAuth = useSelector(state => state.hostAuth);

    const login = () => {
        var pwd = document.getElementById("pwd").value;
        if(pwd === 'password123')
            dispatch(hostAuthAction());
    };

    return (
        <div>
            <h3>Host Home Page</h3>
            {hostAuth.isAuthenticated && (
                <h3>Welcome Host</h3>
            )}
            {!hostAuth.isAuthenticated && (
            <form onSubmit={login}>
                <label>
                    Password: &nbsp;
                    <input type="password" id = "pwd" />
                </label>
                <input type="button" value="submit" onClick={login}/>
            </form>
            )}
        </div>
    );
}

export default Home;