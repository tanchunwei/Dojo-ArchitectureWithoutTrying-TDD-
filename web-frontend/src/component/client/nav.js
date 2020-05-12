import React from 'react';
import './../../App.css';
import {Link} from 'react-router-dom'
import {useSelector, useDispatch} from 'react-redux'
import {clientSignoutAction} from './../../redux/actions/clientAuthAction'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

function Nav(){

    const dispatch = useDispatch()
    const clientAuth = useSelector(state => state.clientAuth);

    const logout = () => {
        dispatch(clientSignoutAction());
    };

    return (
        <nav>
            <Container fluid>
                <Row>
                    <Col>
                        <h3>Logo</h3>
                    </Col>

                    <Col>
                        <ul className="nav-links">
                            <Link className="nav-style" to="/client/about">
                                <li>About</li>
                            </Link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <Link className="nav-style" to="/client/shop">
                                <li>Shop</li>
                            </Link>
                        </ul>
                    </Col>
                    <Col>
                        {clientAuth.isAuthenticated && (
                            <Container fluid>
                                <Row>
                                    <Col>
                                        <small>
                                            ClientID: {clientAuth.clientID}
                                        </small>
                                    </Col>
                                    <Col>
                                        <small>
                                            <ul className="nav-links">
                                                <li className="nav-style" onClick={logout}>Logout</li>
                                            </ul>
                                        </small>
                                    </Col>
                                </Row>
                            </Container>
                        )}
                    </Col>
                </Row>
            </Container>
        </nav>
    );
}

export default Nav;