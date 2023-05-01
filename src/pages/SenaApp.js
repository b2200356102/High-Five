import { useState } from "react";
import SignUp from "../components/SignUp";
import Login from "../components/Login";
import "../styles/Sena.css";

const SenaApp = () => {
  const [currentForm, setCurrentForm] = useState('login');

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
    <div className="sena-app">
      <div clasName="form">
      {
        currentForm === "login" ? <Login onFormSwitch={toggleForm} /> : <SignUp onFormSwitch={toggleForm} />
      }
      </div>
      <div className="background">
        <img src={require('../image.png')} alt="triangle"/>
      </div>
    </div>
  );
}
 
export default SenaApp;