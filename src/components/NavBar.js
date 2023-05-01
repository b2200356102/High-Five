import React, { useState } from "react"
import "../styles/navbarzehra.css"
import { Link } from "react-router-dom"
import { FaBars } from "react-icons/fa"
import { ImCross } from "react-icons/im"

const NavBar = () => {
  const [Mobile, setMobile] = useState(false)
  return (
    <>
      <nav className='NavBar'>
        <h3 className='logo'>Hacettepe</h3>
        {/*
        if large screen ma xa bhane Mobile add huxa
        if mobile screen ma xa bhane nav-links-mobile add huxa
        */}
        <ul className={Mobile ? "nav-links-mobile" : "nav-links"} onClick={() => setMobile(false)}>
          <Link to='/home' className='home'>
            <li>Home</li>
          </Link>
          <Link to='/addcourse' className='addcourse'>
            <li>AddCourse</li>
          </Link>
          <Link to='/addinstructor' className='addinstructor'>
            <li>AddInstructor</li>
          </Link>
          <Link to='/profile' className='profile'>
            <li>Profile</li>
          </Link>
          <Link to='/log' className='sena-app'>
            <li>Log</li>
          </Link>
          
        </ul>
        {/* 
        whenever we click on button = setMobile(!Mobile) ==  is mobile oppsite to setMobile 
        */}
        <button className='mobile-menu-icon' onClick={() => setMobile(!Mobile)}>
          {Mobile ? <ImCross /> : <FaBars />}
        </button>
      </nav>
    </>
  )
}
export default NavBar