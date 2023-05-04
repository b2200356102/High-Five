import React from "react";
import NavBar from './components/NavBar'
import AddCourse from "./pages/AddCourse";
import AddInstructor from "./pages/AddInstructor";
import Profile from "./pages/Profile";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import SenaApp from "./pages/SenaApp";

function App() {

  return (
    <>
      <Router>
        <NavBar />

        <Routes>
          <Route path="/home" exact Component={Home} />
          <Route path="/addcourse" exact Component={AddCourse} />
          <Route path="/addinstructor" exact Component={AddInstructor} />
          <Route path="/profile" exact Component={Profile} />
          <Route path="/log" exact Component={SenaApp} />
         
        </Routes>
      </Router>
    </>
  )
}

export default App;
