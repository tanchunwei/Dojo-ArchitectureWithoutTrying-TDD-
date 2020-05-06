import React from 'react';
import './App.css';
import Nav from './component/client/nav';
import Home from './component/client/home';
import About from './component/client/about';
import Shop from './component/client/shop';
import ItemDetails from './component/client/item-details';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {
  return (
    <Router>
        <div className="App">
            <Nav/>
            <Switch>
                <Route path="/client" exact component={Home} />
                <Route path="/client/about" component={About} />
                <Route path="/client/shop" exact component={Shop} />
                <Route path="/client/shop/:barcode" component={ItemDetails} />
            </Switch>
        </div>
    </Router>
  );
}

export default App;
