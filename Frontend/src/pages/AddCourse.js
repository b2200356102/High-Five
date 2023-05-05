import React, { useState } from "react";
import "../styles/AddCourse.css";
import { useHistory } from 'react-router-dom';

function AddCourse() {
    const [courseCode, setCourseId] = useState("");
    const [name, setCourseName] = useState("");
    const [departmentName, setDepartment] = useState("");
    const [credit, setCredit] = useState("");
    const [courseCapacity, setCapacity] = useState("");
    const [type, setCourseType] = useState("");
    const [underGradStatus, setEducation] = useState("");

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

    const [errorMessage, setErrorMessage] = useState(null);

    const history = useHistory();
    const handleButtonClick = () => {

        
        let isUndergrad = (underGradStatus === "Undergraduate");
        let status = "On";
        let numberOfStudents = 0;

 
        const data = { courseCode, name, departmentName, credit, courseCapacity, type,  isUndergrad, status, numberOfStudents};
        
        


        fetch('http://localhost:8081/api/courses', {
          method: 'POST',
          headers: { 'Accept': 'application/json','Content-Type': 'application/json','Transfer-Encoding': 'chunked'},
          body: JSON.stringify(data)
        })
        .then(response => response.json()).then((response) => {
            if(response.status !== 201){
                
                setErrorMessage(response.message);
                console.log(response);
            }else{
                console.log('Course created successfully');
                history.push('/courses');
                console.log(response);
            }
            
        })
        .catch(error => {
            console.error('Error creating course:', error);
            setErrorMessage(error.message);
        });
    
    };

    

    return (
        <div className="pageContainer"> {/* Parent container */}

            <div className="rectangle"> {/* Rectangle in the middle */}
                {/* Content inside the rectangle */}
                <div className='slotrectangle'>
                    <p className='mainText'>Course ID:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Course ID - Course Name */}
                        <input type="text" id="courseCode" value={courseCode} onChange={handleCourseIdChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Course Name:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Course ID - Course Name */}
                        <input type="text" id="name" value={name} onChange={handleCourseNameChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Department:</p>
                    <div className='fieldrectangle'>
                        {/* Text field for Department */}
                        <input type="text" id="departmentName" value={departmentName} onChange={handleDepartmentChange} />
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
                        <input type="text" id="courseCapacity" value={courseCapacity} onChange={handleCapacityChange} />
                    </div>
                </div>

                <div className='slotrectangle'>
                    <p className='mainText'>Course Type:</p>
                    <div className='fieldrectangle'>
                        {/* Radio buttons for Course Type */}
                        <div class="rowradio">
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="type" value="Mandatory" checked={type === "Mandatory"} onChange={handleCourseTypeChange} />
                               Mandatory
                            </label>
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="type" value="Elective" checked={type === "Elective"} onChange={handleCourseTypeChange} /> Elective
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
                                <input class="radio-position" type="radio" name="underGradStatus" value="Undergraduate" checked={underGradStatus === "Undergraduate"} onChange={handleEducationChange} />
                                Undergraduate
                            </label>
                            <label className="radio-label" class="columnradio">
                                <input class="radio-position" type="radio" name="underGradStatus" value="Graduate" checked={underGradStatus === "Graduate"} onChange={handleEducationChange} />
                                Graduate
                            </label>
                        </div>
                    </div>
                </div>
            </div>


            <div>
                {errorMessage && <p>{errorMessage}</p>}
                {/* rest of your component code */}
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
