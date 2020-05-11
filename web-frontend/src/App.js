import React from 'react';
import './App.css';
import Home from './component/home';
import ClientNav from './component/client/nav';
import ClientHome from './component/client/home';
import ClientAbout from './component/client/about';
import ClientShop from './component/client/shop';
import ClientItemDetails from './component/client/item-details';
import HostNav from './component/host/nav';
import HostHome from './component/host/home';
import HostAbout from './component/host/about';
import HostOrder from './component/host/order';
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

    const hostAuth = useSelector(state => state.hostAuth);

    const HostRoute = ({component: Component, ...rest}) => (
      <Route {...rest} render={(props) => (
          hostAuth.isAuthenticated === true
          ? <Component {... props} />
          : <Redirect to='/host'/>
      )}/>
    )

  return (
    <Router>
        <div className="App">
            <Switch>
                <Route path="/client" component={ClientNav} />
                <Route path="/host" component={HostNav} />
            </Switch>
            <Switch>
                <Route path="/" exact component={Home} />

                <Route path="/client" exact component={ClientHome} />
                <ClientRoute path="/client/about" component={ClientAbout} />
                <ClientRoute path="/client/shop" exact component={ClientShop} />
                <ClientRoute path="/client/shop/:barcode" component={ClientItemDetails} />

                <Route path="/host" exact component={HostHome} />
                <HostRoute path="/host/about" component={HostAbout} />
                <HostRoute path="/host/order" exact component={HostOrder} />
            </Switch>
        </div>
    </Router>
  );
}

export default App;
