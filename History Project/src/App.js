import React, { useState, useEffect } from "react";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';


import Header from "./components/Header";
import Home from "./components/LandingPage";
import News from "./components/News";
import Support from "./components/Store";
import Profile from "./components/Profile";

const App = () => {
  return (
    <BrowserRouter basename={process.env.PUBLIC_URL}>
        <div>
          <Header/>
            <Switch>
             <Route path="/" component={Home} exact/>
             <Route path="/news" component={News} exact/>
             <Route path="/support" component={Support} exact/>
             <Route path="/profile" component={Profile} exact/>
             <Route path="*" component={Home}/>
           </Switch>
        </div> 
      </BrowserRouter>
  )
}

export default App
