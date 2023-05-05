import React, { useState } from "react"
import "../styles/navbarzehra.css"
import { Link } from "react-router-dom"
import { FaBars } from "react-icons/fa"
import { ImCross } from "react-icons/im"

const NavBar = ({ username }) => {
  const [Mobile, setMobile] = useState(false)

  return (
    <>
      <nav class='NavBar'>

      <div class="row">
          <div class="column">SEMESTER 2023 - SPRING</div>
          <div class="column"><Link to='/profile' class='profile'>
            <li class="profiletext"> {username} </li>
          </Link></div>
      </div>

        


        {/*
        if large screen ma xa bhane Mobile add huxa
        if mobile screen ma xa bhane nav-links-mobile add huxa
        */}
        <ul class={Mobile ? "nav-links-mobile" : "nav-links"} onClick={() => setMobile(false)}>
          <Link to='/home' class='home'>
            <li>Home</li>
          </Link>
          <Link to='/courses' class='courses'>
            <li>Courses</li>
          </Link>
          <Link to='/users' class='users'>
            <li>User List</li>
          </Link>
          <Link to='/addinstructor' class='addinstructor'>
            <li>AddInstructor</li>
          </Link>
          <Link to='/log' class='log'>
            <li>Log</li>
          </Link>
          
        </ul>
        {/* 
        whenever we click on button = setMobile(!Mobile) ==  is mobile oppsite to setMobile 
        */}
        <button class='mobile-menu-icon' onClick={() => setMobile(!Mobile)}>
          {Mobile ? <ImCross /> : <FaBars />}
        </button>
      </nav>
    </>
  )
}
export default NavBar