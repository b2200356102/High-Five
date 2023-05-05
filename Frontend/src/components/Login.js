import { useState, useEffect  } from "react";
import { useHistory } from 'react-router-dom';
import FormInput from "./FormInput";
import App from "../App";

const Login = (props) => {
  const [values, setValues] = useState({
    name: "",
    surname: "",
    id: "",
    email: "",
  });

  const inputs = [
    {
      id: 1,
      name: "id",
      type: "text",
      label: "User ID:",
      errorMessage: "Pleace fill this section.",
      required: true,
    },
    {
      id: 2,
      name: "pwd",
      type: "password",
      label: "Password:",
      errorMessage: "Please enter a valid password.",
      required: true,
    },
  ];

  const [faultyLogin, setFaultyLogin] = useState(false);
  const history = useHistory();

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  const submitLogin = (e) => {
    if(e.userDTO.name != null){
      setFaultyLogin(false);
      localStorage.setItem('userid', e.userDTO.id);
      localStorage.setItem('username', e.userDTO.name);
      localStorage.setItem('usersurname', e.userDTO.surname);
      history.push('/home');
    }
    else{
      setFaultyLogin(true);
    }
  };

  const controlLogin = (e) => {
    if(e != null){
      setFaultyLogin(true);
    }
  };

  const handleLogin = () => {
    const input_id = values.id;   
    const input_pwd = values.pwd; 
    const url='http://localhost:8082/api/psw/?userId='+input_id+'&password='+input_pwd;
    
    fetch(url, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'},
    })
    .then(response => response.json())
    .then(data => submitLogin(data))
    .catch(error => controlLogin(error))
  };

  return (
    <div className="login">
      <header>
        <h1 class="page-title">Login</h1>
        <button className="page-button" onClick={() => props.onFormSwitch('sign-up')}>Sign Up</button>
      </header>
      <form onSubmit={handleSubmit}>
        {inputs.map((input) => (
          <FormInput
            key={input.id}
            {...input}
            value={values[input.name]}
            onChange={onChange}
          />
        ))}
      </form>
      <button className="reset-pwd-button">Reset Password</button>
      <button className="submit-button" onClick={handleLogin}>LOGIN</button>
      {faultyLogin && (<p class="faulty-login-text">Incorrect ID or password. Try again.</p>)}
    </div>
  );
}
 
export default Login;