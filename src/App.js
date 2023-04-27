import React from "react"
import Navbar from "./component/Navbar"
import Profile from "./component/Profile"
import UserList from "./component/UserList"
import Home from "./component/Home"
import Courses from "./component/Courses"
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"


//npm install react-router-dom@5

function App() {
  return (
    <>
      <Router>
        <Navbar />

        <Switch>
          <Route path='/home' component={Home} exact>
            <Home />
          </Route>
          <Route path='/userList' component={UserList} exact>
            <UserList />
          </Route>
          <Route path='/profile' component={Profile} exact>
            <Profile />
          </Route>
          <Route path='/courses' component={Courses} exact>
            <Courses />
          </Route>
         
        </Switch>
      </Router>
    </>
  )
}

export default App