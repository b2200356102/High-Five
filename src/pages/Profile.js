
import "../styles/profile.css"
import React, { useState } from "react";

function Profile() {
  const [username, setUsername] = useState("");
  const [surname, setSurname] = useState("");
  const [email, setEmail] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordMatch, setPasswordMatch] = useState(true);
  const [passwordLength, setPasswordLength] = useState(true);
  const [newProfilePic, setNewProfilePic] = useState(null);

  const handleNewPasswordChange = (event) => {
    setNewPassword(event.target.value);
  };

  const handleConfirmPasswordChange = (event) => {
    setConfirmPassword(event.target.value);
  };
  const [showPassword, setShowPassword] = useState(false);
  const handleSubmit = (event) => {
    event.preventDefault();
    // Şifrelerin eşleştiğini ve uzunluğunun yeterli olduğunu kontrol
    if (newPassword !== confirmPassword) {
      setPasswordMatch(false);
    } else {
      setPasswordMatch(true);
    }
    if (newPassword.length < 6) {
      setPasswordLength(false);
    } else {
      setPasswordLength(true);
    }
    console.log("Form submitted");
    // Gönderme işlemini burada yapacağız
  };

  return (
    <>
    <section >
     
   
    <body>
    <div>
    

      <div class="login-root">
   
      <div class="loginbackground padding-top--64 flexgrow">
        <div class="loginbackground-gridContainer">
          <div class="box-root flex-flex gridzero">
            <div class="box-root backimg flexgrow" >
            </div>
          </div>
          <div class="box-root flex-flex gridone" >
            <div class="box-root box-divider--light-all-2 animationLeftRight tans3s flexgrow"></div>
          </div>
          <div class="box-root flex-flex gridtwo" >
            <div class="box-root box-background--blue800 flexgrow" ></div>
          </div>
          <div class="box-root flex-flex gridthree">
            <div class="box-root box-background--blue animationLeftRight flexgrow"></div>
          </div>
          <div class="box-root flex-flex gridfour">
            <div class="box-root box-background--gray100 animationLeftRight tans3s flexgrow"></div>
          </div>
          <div class="box-root flex-flex gridfive">
            <div class="box-root box-background--cyan200 animationRightLeft tans4s flexgrow"></div>
          </div>
          <div class="box-root flex-flex girdsix">
            <div class="box-root box-background--blue animationRightLeft flexgrow flexgrow"></div>
          </div>
          <div class="box-root flex-flex gridseven">
            <div class="box-root box-background--gray100 animationRightLeft tans4s flexgrow" ></div>
          </div>
          <div class="box-root flex-flex grideight">
            <div class="box-root box-divider--light-all-2 animationRightLeft tans3s flexgrow" ></div>
          </div>
        </div>
      </div>
      <div class="box-root padding-top--24 flex-flex flex-direction--column zindex flexgrow" >
      <div class="formbg-outer">
          <div class="formbg">
            <div class="formbg-inner padding-horizontal--48">
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="newProfilePic">Profile Picture:</label>
          <input
            type="file"
            id="newProfilePic"
            accept="image/*"
            onChange={(e) => setNewProfilePic(e.target.files[0])}
          />
        </div>
        <div class="field padding-bottom--24">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div class="field padding-bottom--24">
          <label htmlFor="surname">Surname:</label>
          <input
            type="text"
            id="surname"
            value={surname}
            onChange={(e) => setSurname(e.target.value)}
          />
        </div>
        <div class="field padding-bottom--24">
          <label htmlFor="email">E-mail:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div class="field padding-bottom--24">
          <label htmlFor="new-password">New Password:</label>
          <input
            //type="password"
            type={showPassword ? "text" : "password"}
            id="new-password"
            value={newPassword}
            onChange={handleNewPasswordChange}
          />
          <button
          class="button2"
          type="button"
          onClick={() => setShowPassword(!showPassword)}
          className="show-password-btn"
        >
          {showPassword ? "Hide Password" : "Show Password"}
        </button>
          
        </div>
        <div class="field padding-bottom--24">
          <label htmlFor="confirm-password">Confirm Password:</label>
          <input
            //type="password"
            type={showPassword ? "text" : "password"}
            id="confirm-password"
            value={confirmPassword}
            onChange={handleConfirmPasswordChange}
          />
        </div>
        {!passwordMatch && (
          <p style={{ color: "red" }}>Passwords do not match</p>
        )}
        {!passwordLength && (
          <p style={{ color: "red" }}>
            Password must be at least 6 characters
          </p>
        )}
        
        <button class="button1"type="submit">Save</button>
      
        
      </form>
      </div></div></div></div></div></div>
    </body>
    </section>
  </>
  );
}

export default Profile;


