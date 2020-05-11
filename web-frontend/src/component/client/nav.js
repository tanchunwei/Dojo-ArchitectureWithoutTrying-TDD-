import React from 'react';
import './../../App.css';
import {Link} from 'react-router-dom'
import {useSelector, useDispatch} from 'react-redux'
import {clientSignoutAction} from './../../redux/actions/clientAuthAction'

function Nav(){

    const navStyle = {
        color: 'white'
    }

    const dispatch = useDispatch()
    const clientAuth = useSelector(state => state.clientAuth);

    const logout = () => {
        dispatch(clientSignoutAction());
    };

    return (
        <nav>
            <div>
                <h3>Logo</h3>
            </div>

            <div>
                <ul className="nav-links">
                    <Link style={navStyle} to="/client/about">
                        <li>About</li>
                    </Link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Link style={navStyle} to="/client/shop">
                        <li>Shop</li>
                    </Link>
                </ul>
            </div>
            <div>
            {clientAuth.isAuthenticated && (
                <div>
                    <small>
                        ClientID:<br/>
                        {clientAuth.clientID} <br/>
                        <ul className="nav-links">
                            <Link style={navStyle} onClick={logout}>
                                <li>Logout</li>
                            </Link>
                        </ul>
                    </small>
                </div>
            )}
            </div>
        </nav>
    );
}

export default Nav;