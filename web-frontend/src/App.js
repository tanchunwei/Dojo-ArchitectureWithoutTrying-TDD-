import React from 'react';
import './App.css';
import Nav from './component/client/nav';
import Home from './component/client/home';
import About from './component/client/about';
import Shop from './component/client/shop';
import ItemDetails from './component/client/item-details';
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';
import {useSelector} from 'react-redux';

function App() {
  const clientAuth = useSelector(state => state.clientAuth);

  const ClientRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={(props) => (
        clientAuth.isAuthenticated === true
        ? <Component {... props} />
        : <Redirect to='/client'/>
    )}/>
  )

  return (
    <Router>
        <div className="App">
            <Nav/>
            <Switch>
                <Route path="/client" exact component={Home} />
                <ClientRoute path="/client/about" component={About} />
                <ClientRoute path="/client/shop" exact component={Shop} />
                <ClientRoute path="/client/shop/:barcode" component={ItemDetails} />
            </Switch>
        </div>
    </Router>
  );
}

export default App;
