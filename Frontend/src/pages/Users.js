import React, { useState, useEffect } from 'react';
import "../styles/Courses.css";


function Users() {
    const [users, setUsers] = useState([]);


    useEffect(() => {
        fetch('http://localhost:8082/api/users/', {
          method: 'GET',
          headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' }
        })
        .then(response => response.json())
        .then(users => setUsers(users))
        .catch(error => console.log(error));
      }, []);

    return (
        <div>
            <div className='coursesBlock courseInfo'>
                <div className='insideBlock'>
                    <ul>
                    {users.map(users => (
                        <div className='userblock'>
                            <li key={users.id} >
                                <p><strong>User ID:</strong> {users.id}</p>
                                <p><strong>User Name:</strong> {users.name}</p>
                                <p><strong>User Name:</strong> {users.mail}</p>
                            </li>
                        </div>
                    
                    ))}
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Users




