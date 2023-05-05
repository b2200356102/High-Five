import React, { useState, useEffect } from 'react';
import "../styles/Courses.css";
import { useHistory } from 'react-router-dom';

function Courses() {
    const [courses, setCourses] = useState([]);


    useEffect(() => {
        fetch('http://localhost:8081/api/courses', {
          method: 'GET',
          headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' }
        })
        .then(response => response.json())
        .then(courses => setCourses(courses))
        .catch(error => console.log(error));
      }, []);


    const history = useHistory();
    const handleCoursePageButton = () => {
        history.push('/addcourse');
    };
    return (
        <div>
            <div>
                {/* Button goes here */}
                <button className="coursesButton" onClick={handleCoursePageButton}>
                    Add Course
                </button>
            </div>
            <div className='coursesBlock courseInfo'>
            <ul>
                {courses.map(course => (
                    <div className='block'>
                        <div className='insideBlock'>
                            <li key={course.id} >
                                <p><strong>Course Code:</strong> {course.courseCode}</p>
                                <p><strong>Course Name:</strong> {course.name}</p>
                                <p><strong>Credit:</strong> {course.credit}</p>
                            </li>
                        </div>
                    </div>
                
                ))}
            </ul>
            </div>
        </div>
    );
}

export default Courses
