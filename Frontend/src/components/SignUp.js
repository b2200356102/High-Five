import { useState } from "react";
import FormInput from "./FormInput";

const SignUp = (props) => {
  const [values, setValues] = useState({
    name: "",
    surname: "",
    id: "",
    email: "",
  });

  const [checkedValue, setValue] = useState(true);

  const inputs = [
    {
      id: 1,
      name: "name",
      type: "text",
      label: "Name:",
      errorMessage: "Pleace fill this section.",
      pattern: "^[A-Za-z]{1,50}$",
      required: true,
    },
    {
      id: 2,
      name: "surname",
      type: "text",
      label: "Surname:",
      errorMessage: "Pleace fill this section.",
      pattern: "^[A-Za-z]{1,50}$",
      required: true,
    },
    {
      id: 3,
      name: "id",
      type: "text",
      label: "ID:",
      errorMessage: "Pleace fill this section.",
      pattern: "^[0-9]{1,20}$",
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

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  const handleChange = (e) => {
    const {value, checked} = e.target
    if(checked){setValue(value)}
  };
  console.log(checkedValue)

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
        <div className="user-class">
          <div><input type="checkbox" value="student" onChange={handleChange} /><p className="checkbox">Student</p></div> 
          <div><input type="checkbox" value="departmentManager" onChange={handleChange} /><p className="checkbox">Department Manager</p></div> 
        </div>
      </form>
      <button className="submit-button">SIGN UP</button>
    </div>
  );
};

export default SignUp;
