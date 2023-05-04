import React, { useState } from "react";
import "../styles/AddCourse.css";

function AddInstructor() {
    const [instructorName, setInstructorName] = useState("");
    const [email, setEmail] = useState("");
    const [department, setDepartment] = useState("");
    const [password, setPassword] = useState("");

    const handleInstructorNameChange = (e) => {
        setInstructorName(e.target.value);
    };

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handleDepartmentChange = (e) => {
        setDepartment(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };




    const handleButtonClick = () => {
        /* */

    };
    return (
        <div className="pageContainer"> {/* Parent container */}
            <div className="rectangle"> {/* Rectangle in the middle */}
                {/* Content inside the rectangle */}
                <div className='slotrectangle'>
                    <p className='mainText'>Name - Surname:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Course ID - Course Name */}
                        <input type="text" id="instructorname" value={instructorName} onChange={handleInstructorNameChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>E-mail:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Department */}
                        <input type="text" id="email" value={email} onChange={handleEmailChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Department:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Credit */}
                        <input type="text" id="department" value={department} onChange={handleDepartmentChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Password:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Department */}
                        <input type="text" id="password" value={password} onChange={handlePasswordChange} />
                    </div>
                </div>


            </div>

            <div className="buttonContainer">
                {/* Button goes here */}
                <button className="myButton" onClick={handleButtonClick}>
                    SUBMIT
                </button>
            </div>
        </div>
    );
}

export default AddInstructor
