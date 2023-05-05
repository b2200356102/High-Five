import { useState } from "react";
import FormInput from "./FormInput";

const SignUp = (props) => {
  const [values, setValues] = useState({
    name: "",
    surname: "",
    id: "",
    email: "",
  });

  const inputs = [
    {
      id: 1,
      name: "name",
      type: "text",
      label: "Name:",
      errorMessage: "Pleace fill this section.",
      required: true,
    },
    {
      id: 2,
      name: "surname",
      type: "text",
      label: "Surname:",
      errorMessage: "Pleace fill this section.",
      required: true,
    },
    {
      id: 3,
      name: "id",
      type: "text",
      label: "ID:",
      errorMessage: "Pleace fill this section.",
      required: true,
    },
    {
      id: 4,
      name: "email",
      type: "email",
      label: "Email:",
      errorMessage: "Please enter a valid email address.",
      required: true,
    },
  ];

  const [validSignUp, setValidSignUp] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  return (
    <div className="sign-up">
      <header>
        <button className="page-button" onClick={() => props.onFormSwitch('login')}>Login</button>
        <h1 class="page-title">Sign Up</h1>
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
        <div className="user-type-radio">
          <div className="user-type-input">
            <input type="radio" name="userType" value="student" required onChange={onChange}/> Student
          </div>
          <div className="user-type-input">
            <input type="radio" name="userType"  value="departmenManager" onChange={onChange}/> Department Manager
          </div>
        </div>
      </form>
      <button className="submit-button">SIGN UP</button>
      {validSignUp && (<p class="faulty-login-text">Registiration request is sent.</p>)}
    </div>
  );
};

export default SignUp;
