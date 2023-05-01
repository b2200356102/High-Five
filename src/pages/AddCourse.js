import React, { useState } from "react";
import "../styles/AddCourse.css";

function AddCourse() {
    const [courseIdName, setCourseIdName] = useState("");
    const [department, setDepartment] = useState("");
    const [credit, setCredit] = useState("");
    const [courseType, setCourseType] = useState("");
    const [education, setEducation] = useState("");

    const handleCourseIdNameChange = (e) => {
        setCourseIdName(e.target.value);
    };

    const handleDepartmentChange = (e) => {
        setDepartment(e.target.value);
    };

    const handleCreditChange = (e) => {
        setCredit(e.target.value);
    };

    const handleCourseTypeChange = (e) => {
        setCourseType(e.target.value);
    };

    const handleEducationChange = (e) => {
        setEducation(e.target.value);
    };


    const handleButtonClick = () => {
        /* */

    };
    return (
        <div className="pageContainer"> {/* Parent container */}
            <div className="rectangle"> {/* Rectangle in the middle */}
                {/* Content inside the rectangle */}
                <div className='slotrectangle'>
                    <p className='mainText'>Course ID - Course Name:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Course ID - Course Name */}
                        <input type="text" id="courseidname" value={courseIdName} onChange={handleCourseIdNameChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Department:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Department */}
                        <input type="text" id="department" value={department} onChange={handleDepartmentChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Credit:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Credit */}
                        <input type="text" id="credit" value={credit} onChange={handleCreditChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Course Type:</p>
                    <div className='fieldrectangle'>
                        {/* Radio buttons for Course Type */}
                        <div>
                            <label className="radio-label">
                                <input type="radio" name="courseType" value="Mandatory" checked={courseType === "Mandatory"} onChange={handleCourseTypeChange} /> Mandatory
                            </label>
                            <label className="radio-label">
                                <input type="radio" name="courseType" value="Elective" checked={courseType === "Elective"} onChange={handleCourseTypeChange} /> Elective
                            </label>
                        </div>
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Education:</p>
                    <div className='fieldrectangle'>
                        {/* Radio buttons for Education */}
                        <div>
                            <label className="radio-label">
                                <input type="radio" name="education" value="Undergraduate" checked={education === "Undergraduate"} onChange={handleEducationChange} />
                                Undergraduate
                            </label>
                            <label className="radio-label">
                                <input type="radio" name="education" value="Graduate" checked={education === "Graduate"} onChange={handleEducationChange} />
                                Graduate
                            </label>
                        </div>
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

export default AddCourse
