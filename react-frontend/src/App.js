import React, { Component } from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import TasksContainer from './components/Tasks'

class App extends Component {
  render() {
    return (
      <div>
        <div className="header">
          <h1>Task Management App</h1>
        </div>
        <div className="container">
          <TasksContainer />
        </div>
      </div>

    );
  }
}

export default App;