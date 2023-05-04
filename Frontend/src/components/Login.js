import { useState } from "react";
import FormInput from "./FormInput";

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

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  const handleLogin = () => {
    const input_id = values.id;   
    const input_pwd = values.pwd; 
    const url='http://localhost:8082/api/psw/?userId='+input_id+'&password='+input_pwd;
    console.log(url);
    fetch(url, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'},
    })
    .then(response => response.json()).then(a => {
      console.log(a);
    })
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
    </div>
  );
}
 
export default Login;