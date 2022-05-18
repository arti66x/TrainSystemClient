import React from 'react';
import {BrowserRouter as Router, Route,Switch} from 'react-router-dom'
import Header from './components/Header';
import CreateForm from './components/CreateForm';
import Home from './components/StartPage';
import Table from './components/Table';
import './App.css';

function App() {
  return (
    <Router>
      <Header/>
      <div className='main-body-container'>
        <Switch>  
          <Route exact path = "/"  component = {Home} />                       
          <Route exact path = "/:table" component = {Table} />
          <Route exact path = "/:table/:id" component = {CreateForm} />
        </Switch> 
      </div>
    </Router>
  );
}

export default App;
