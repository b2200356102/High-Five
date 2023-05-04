import React from 'react';
import NavBar from './components/NavBar';
import AddCourse from './pages/AddCourse';
import AddInstructor from './pages/AddInstructor';
import Profile from './pages/Profile';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './pages/Home';
import SenaApp from './pages/SenaApp';

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
          <Route path="/log" component={SenaApp} exact>
            <SenaApp />
          </Route>
        </Switch>
      </Router>
    </>
  );
}

export default App;
