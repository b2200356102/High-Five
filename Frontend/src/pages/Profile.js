import '../styles/profile.css';
import React, { useState, useEffect } from 'react';

function Profile() {
  const [user, setUser] = useState(null);

  const [mail, setEmail] = useState(null);
  const [departmentName, setDepartment] = useState(null);
  const [role, setRole] = useState('');
  // const [status, setStatus] = useState("");
  const [password, setNewPassword] = useState(null);
  const [confirmPassword, setConfirmPassword] = useState(null);
  const [passwordMatch, setPasswordMatch] = useState(true);
  const [passwordLength, setPasswordLength] = useState(true);
  const [success, setSuccess] = useState(false);
  const [newProfilePic, setNewProfilePic] = useState(null);
  const userID = localStorage.getItem('userid');

  useEffect(() => {
    async function fetchData() {
      const response = await fetch(
        `http://localhost:8082/api/users/?userId=${userID}`
      );
      const data = await response.json();

      console.log(data.userDTO.name); // check the data returned from the API
      setUser(data);
    }
    fetchData();
  }, []);

  const handleNewPasswordChange = (event) => {
    setNewPassword(event.target.value);
  };

  const handleConfirmPasswordChange = (event) => {
    setConfirmPassword(event.target.value);
  };
  const [showPassword, setShowPassword] = useState(false);

  const handleSubmit = (event) => {

    var myPasswordMatch = false;
    var myPasswordLength = false;

    event.preventDefault();
    // Şifrelerin eşleştiğini ve uzunluğunun yeterli olduğunu kontrol et
    if (password !== confirmPassword) {
      setPasswordMatch(false);
      myPasswordMatch = false;
    } else {
      setPasswordMatch(true);
      myPasswordMatch = true;
    }
    if (password.length < 6) {
      setPasswordLength(false);
      myPasswordLength = false;
    } else {
      setPasswordLength(true);
      myPasswordLength = true;
    }
    setSuccess(false);

    if (myPasswordLength && myPasswordMatch) {
      fetch('http://localhost:8082/api/psw/' + "1234567893" + '/?password=' + password, { method: 'PUT' })
        .then(response => {
          if (response.status === 200) {
            setSuccess(true);
          }
          response.json()
        })
        .then(a => { console.log(a); });
    }


    console.log('Form submitted');

    setNewPassword("");
    setConfirmPassword("");
    // Gönderme işlemini burada yapacağız
  };

  return (
    <>
      {user ? (
        <section>
          <body>
            <div>
              <div class="login-root">
                <div class="box-root padding-top--24 flex-flex flex-direction--column zindex flexgrow">
                  <div class="formbg-outer">
                    <div class="formbg">
                      <div class="formbg-inner padding-horizontal--48">
                        <form onSubmit={handleSubmit}>
                          <div className="form-group">
                            <label htmlFor="newProfilePic">
                              Profile Picture:
                            </label>
                            <input
                              class="imgtext"
                              type="file"
                              id="newProfilePic"
                              accept="image/*"
                              onChange={(e) =>
                                setNewProfilePic(e.target.files[0])
                              }
                            />
                          </div>
                          <div class="field padding-bottom--24">
                            <label htmlFor="username">Username:</label>
                            <input
                              type="text"
                              id="name"
                              value={user.userDTO.name}
                              onChange={(e) => setUser(e.target.value)}
                            />
                          </div>
                          <div class="field padding-bottom--24">
                            <label htmlFor="surname">Surname:</label>
                            <input
                              type="text"
                              id="surname"
                              value={user.userDTO.surname}
                              onChange={(e) => setUser(e.target.value)}
                            />
                          </div>
                          <div class="field padding-bottom--24">
                            <label htmlFor="email">E-mail:</label>
                            <input
                              type="email"
                              id="mail"
                              value={user.userDTO.mail}
                              onChange={(e) => setEmail(e.target.value)}
                            />
                          </div>
                          <div class="field padding-bottom--24">
                            <label htmlFor="departmentName">Department:</label>
                            <input
                              type="departmentName"
                              id="departmentName"
                              value={user.department.name}
                              onChange={(e) => setDepartment(e.target.value)}
                            />
                          </div>
                          <div class="field padding-bottom--24">
                            <label htmlFor="role">Role:</label>
                            <input
                              type="role"
                              id="role"
                              value={user.userDTO.role}
                              onChange={(e) => setRole(e.target.value)}
                            />
                          </div>

                          <div class="field padding-bottom--24">
                            <label htmlFor="new-password">New Password:</label>
                            <input
                              //type="password"
                              type={showPassword ? 'text' : 'password'}
                              id="new-password"
                              value={password}
                              onChange={handleNewPasswordChange}
                            />
                            <button
                              type="button"
                              onClick={() => setShowPassword(!showPassword)}
                              class="show-password-button"
                            >
                              {showPassword ? 'Hide Password' : 'Show Password'}
                            </button>
                          </div>
                          <div class="field padding-bottom--24">
                            <label htmlFor="confirm-password">
                              Confirm Password:
                            </label>
                            <input
                              //type="password"
                              type={showPassword ? 'text' : 'password'}
                              id="confirm-password"
                              value={confirmPassword}
                              onChange={handleConfirmPasswordChange}
                            />
                          </div>
                          {!passwordMatch && (
                            <p class="warningtext">Passwords do not match</p>
                          )}
                          {!passwordLength && (
                            <p class="warningtext">
                              Password must be at least 6 characters
                            </p>
                          )}
                          {success && (
                            <p class="successtext">
                              Password changed successfully
                            </p>
                          )}

                          <button class="button1" type="submit">
                            Save
                          </button>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="background">
                  <img src={require('../image.png')} alt="triangle" />
                </div>
              </div>
            </div>
          </body>
        </section>
      ) : (
        <p>Loading...</p>
      )}
    </>
  );
}

export default Profile;


