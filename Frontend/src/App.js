import React from 'react';
import NavBar from './components/NavBar';
import AddCourse from './pages/AddCourse';
import AddInstructor from './pages/AddInstructor';
import Profile from './pages/Profile';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './pages/Home';
import Log from './pages/Log';
import Courses from './pages/Courses';

function App() {
  return (
    <>
      <Router>
        <NavBar />

        <Switch>
          <Route path="/home" component={Home} exact>
            <Home />
          </Route>
          <Route path="/addcourse" component={AddCourse} exact>
            <AddCourse />
          </Route>
          <Route path="/addinstructor" component={AddInstructor} exact>
            <AddInstructor />
          </Route>
          <Route path="/profile" component={Profile} exact>
            <Profile />
          </Route>
          <Route path="/log" component={Log} exact>
            <Log />
          </Route>
          <Route path="/courses" component={Courses} exact>
            <Courses />
          </Route>
        </Switch>
      </Router>
    </>
  );
}

export default App;
