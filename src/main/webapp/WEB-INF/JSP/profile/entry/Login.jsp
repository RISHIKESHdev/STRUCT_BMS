<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kode+Mono:wght@400..700&display=swap" rel="stylesheet">
        <title>LV-BANK</title>
    </head>
    <body id="body-main">
        <div id="nav-bar">
            <img id="logo" src="logo">
            <p>Lake View Bank</p>
        </div>
        <div id="body-box">
            <div class="formDiv">
                <form class="formBody" name="signInUserForm" method="post" action="loginUser">
                    <label htmlFor="b7" >Email-id</label>
                    <input type="email" id="b7" name='emailId' required="required"/>
                    <label htmlFor="b8" >Password</label>
                    <input type="password" id="b8" name='password' required="required"/>
                    <button type='submit' name='signin' >SIGN IN</button>
                    <span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
                    <p>
                        <a href="Forgotpassword">Forgot passwaord?</a>
                        <% if(request.getParameter("entryParam").compareTo("customerLogin")==0){ %>
                            or
                            <a href="signUpCustomer">Sign Up</a>
                        <% } %>
                    </p>
                    <% if(request.getParameter("entryParam").compareTo("customerLogin")==0){ %>
                        <input type="hidden" name='hdn_loginParam' value='customerLogin'/>
                    <% }else if(request.getParameter("entryParam").compareTo("employeeLogin")==0){ %>
                        <input type="hidden" name='hdn_loginParam' value='employeeLogin'/>
                    <% }else if(request.getParameter("entryParam").compareTo("adminLogin")==0){ %>
                        <input type="hidden" name='hdn_loginParam' value='adminLogin'/>
                    <% } %>
                </form>
            </div>
        </div>
    </body>
    <style type="text/css">
            #logo{
                padding:5px;
                width:5%;
                height:80%;
            }
            button.unit {
                border: none;
                border-radius: 35px;
                width:40%;
                height:12%;
                box-shadow: -6px -6px 6px rgba(255, 255, 255, 0.8),6px 6px 6px rgba(0, 0, 0, 0.2);
                color: black;
                font-family: "Kode Mono", monospace;
                font-optical-sizing: auto;
                font-style: normal;
            }
            button:hover {
                cursor: pointer;
                box-shadow: -3px -3px 3px rgba(255, 255, 255, 0.8),3px 3px 3px rgba(0, 0, 0, 0.2);
                transition: 0.5s;
            }
            #body-main{
                font-family: "Kode Mono", monospace;
                font-optical-sizing: auto;
                font-style: normal;

                margin-left:5%;
                display:flex;
                background: #ecf0f3;
                justify-content: center;
                align-items: center;
                flex-direction:column;
                height:100%;
                width:90%;
            }
            #nav-bar{
                display:flex;
                align-items: center;
                width:100%;
                height:10%;
                background: #ecf0f3;
                border-radius: 20px;
                margin: 20px;
                box-shadow: -6px -6px 6px rgba(255, 255, 255, 0.8),6px 6px 6px rgba(0, 0, 0, 0.2);
            }
            #body-box{
                display:flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                width:100%;
                height:90%;
                background: #ecf0f3;
                border-radius: 20px;
                box-shadow: -6px -6px 6px rgba(255, 255, 255, 0.8),6px 6px 6px rgba(0, 0, 0, 0.2);
                margin-bottom:2%;
            }
            #button-box{
                display:flex;
                flex-direction: column;
                justify-content: space-around;
                align-items: center;
                width:40%;
                height:55%;
            }
            .formDiv {
                width: 30%;
                height: 75%;
                padding: 20px 35px 15px 35px;
                border-radius: 35px;
                background: #ecf0f3;
                box-shadow:
                -6px -6px 6px rgba(255, 255, 255, 0.8),
                6px 6px 6px rgba(0, 0, 0, 0.2);
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: space-evenly;
                margin-bottom: 2%;
            }
            .formBody{
                width: 100%;
                height: 85%;
                border-radius: 35px;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: space-evenly;
                box-shadow:
                -6px -6px 6px rgba(255, 255, 255, 0.8),
                6px 6px 6px rgba(0, 0, 0, 0.2);
            }
            label{
                width:80%;
            }
            input{
                border: none;
                border-radius: 15px;
                width:80%;
                height:8%;
                outline: none;
                text-align: left;
                text-indent: 10px;
                box-shadow: inset 5px 5px 5px #cbced1,
                inset -5px -5px 5px #ffffff;
              }
            button{
                border: none;
                border-radius: 35px;
                width:60%;
                height:12%;
                box-shadow:
                -6px -6px 6px rgba(255, 255, 255, 0.8),
                6px 6px 6px rgba(0, 0, 0, 0.2);
            }
            button:hover{
                cursor: pointer;
                box-shadow:
                -3px -3px 3px rgba(255, 255, 255, 0.8),
                3px 3px 3px rgba(0, 0, 0, 0.2);
                transition: 1s;
            }
        </style>
</html>
