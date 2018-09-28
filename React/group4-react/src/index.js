import React from 'react'
import ReactDOM from 'react-dom'
import { Router, Route, Switch } from 'react-router-dom';
//import {withRouter} from "react-router-dom";
import createBrowserHistory from 'history/createBrowserHistory'

import ExpressApp from './main/ExpressApp'
import Contact from './contact/Contact';
import LoginInput from './login/LoginInput'

var history = createBrowserHistory()

ReactDOM.render((
  <Router history={history}>
    <Switch>
      <Route exact path="/" component={LoginInput}/>
      <Route exact path="/main" component={ExpressApp}/>
      <Route path="/contact" component={Contact}/>
    </Switch>
  </Router>
),
  //<ExpressApp />,
  document.getElementById('root')
)
//<Route path="/contact" component={Contact}/>
