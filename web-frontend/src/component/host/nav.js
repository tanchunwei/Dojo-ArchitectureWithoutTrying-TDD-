import React from 'react';
import './../../App.css';
import {Link} from 'react-router-dom'
import {useSelector, useDispatch} from 'react-redux'
import {hostSignoutAction} from './../../redux/actions/hostAuthAction'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'


function Nav(){
    const dispatch = useDispatch()
    const hostAuth = useSelector(state => state.hostAuth);

    const logout = () => {
        dispatch(hostSignoutAction());
    };

    return (
        <nav>
            <Container fluid>
                <Row>
                    <Col xs={3}>
                        <h3>Logo</h3>
                    </Col>
                    <Col xs={6}>
                        <ul className="nav-links">
                            <Link className="nav-style" to="/host/about">
                                <li>About</li>
                            </Link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <Link className="nav-style" to="/host/order">
                                <li>Order</li>
                            </Link>
                        </ul>
                    </Col>
                    <Col xs={3}>
                        {hostAuth.isAuthenticated && (
                            <div>
                                <small>
                                    <ul className="nav-links">
                                        <li className="nav-style" onClick={logout}>Logout</li>
                                    </ul>
                                </small>
                            </div>
                        )}
                    </Col>
                </Row>
            </Container>
        </nav>
    );
}

export default Nav;