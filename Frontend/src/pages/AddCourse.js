import React, { useState } from "react";
import "../styles/AddCourse.css";

function AddCourse() {
    const [courseId, setCourseId] = useState("");
    const [courseName, setCourseName] = useState("");
    const [department, setDepartment] = useState("");
    const [credit, setCredit] = useState("");
    const [capacity, setCapacity] = useState("");
    const [courseType, setCourseType] = useState("");
    const [education, setEducation] = useState("");

    const handleCourseIdChange = (e) => {
        setCourseId(e.target.value);
    };

    const handleCourseNameChange = (e) => {
        setCourseName(e.target.value);
    };

    const handleDepartmentChange = (e) => {
        setDepartment(e.target.value);
    };

    const handleCreditChange = (e) => {
        setCredit(e.target.value);
    };

    const handleCapacityChange = (e) => {
        setCapacity(e.target.value);
    };

    const handleCourseTypeChange = (e) => {
        setCourseType(e.target.value);
    };

    const handleEducationChange = (e) => {
        setEducation(e.target.value);
    };


    const handleButtonClick = () => {

        const data = { courseId, courseName, department, credit,capacity, courseType, education };
        /*
        fetch('/api/courses', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(data)
        })
        .then(response => {
          if (response.ok) {
            console.log('Course added successfully');
          } else {
            console.error('Error adding course');
          }
        })
        .catch(error => {
          console.error('Error adding course', error);
        });*/

        console.log("Course ID - Course Name: ", courseId, " - ", courseName);
        console.log("Department: ", department);
        console.log("Credit: ", credit);
        console.log("Capacity: ", capacity);
        console.log("Course Type: ", courseType);
        console.log("Education: ", education);
    };

    return (
        <div className="pageContainer"> {/* Parent container */}
            <div className="rectangle"> {/* Rectangle in the middle */}
                {/* Content inside the rectangle */}
                <div className='slotrectangle'>
                    <p className='mainText'>Course ID:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Course ID - Course Name */}
                        <input type="text" id="courseid" value={courseId} onChange={handleCourseIdChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Course Name:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Course ID - Course Name */}
                        <input type="text" id="coursename" value={courseName} onChange={handleCourseNameChange} />
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
                    <p className='mainText'>Course Capacity:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Credit */}
                        <input type="text" id="capacity" value={capacity} onChange={handleCapacityChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Course Type:</p>
                    <div className='fieldrectangle'>
                        {/* Radio buttons for Course Type */}
                        <div class="rowradio">
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="courseType" value="Mandatory" checked={courseType === "Mandatory"} onChange={handleCourseTypeChange} />
                               Mandatory
                            </label>
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="courseType" value="Elective" checked={courseType === "Elective"} onChange={handleCourseTypeChange} /> Elective
                            </label>
                        </div>
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Education:</p>
                    <div className='fieldrectangle'>
                        {/* Radio buttons for Education */}
                        <div class="rowradio">
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="education" value="Undergraduate" checked={education === "Undergraduate"} onChange={handleEducationChange} />
                                Undergraduate
                            </label>
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="education" value="Graduate" checked={education === "Graduate"} onChange={handleEducationChange} />
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
