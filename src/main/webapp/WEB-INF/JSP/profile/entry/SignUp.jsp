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
            <form class="formBody" name="signUpUserForm" method="post" action="signUpUser">


                    <% if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b1" >First Name</label>
                            <input type="text" class="inl" id="b1" name='firstName' required="required"/>
                            <label class="divl" htmlFor="b9" >PAN-Number</label>
                            <input type="text" class="inl" id="b9" name='panNumber' required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0 || request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b1" >First Name</label>
                            <input type="text" class="inl" id="b1" name='firstName' required="required"/>
                            <label class="divl" htmlFor="b9"> Is Active </label>
                            <div class="inldiv">
                                <label class="switch">
                                    <input class="switch-input" id="b9" type="checkbox" name='activeString' value="Checked" required="required"/>
                                    <span class="slider"></span>
                                    <span class="on"></span>
                                    <span class="off"></span>
                                </label>
                            </div>
                        </div>
                    <% } %>



                    <% if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b2" >Middle Name</label>
                            <input type="text" class="inl" id="b2" name='middleName'/>
                            <label class="divl" htmlFor="b10" >CKYC Doc</label>
                            <input type="text" class="inl" id="b10" name='CKYCVerificationDocument' required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b2" >Middle Name</label>
                            <input type="text" class="inl" id="b2" name='middleName'/>
                            <label class="divl" htmlFor="b10" >Designation</label>
                            <input type="text" class="inl" id="b10" name='employeeDesignation' required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                       <div id="div1">
                           <label class="divl" htmlFor="b2" >Middle Name</label>
                           <input type="text" class="inl" id="b2" name='middleName'/>
                           <label class="divl" htmlFor="b10">Address Line 1</label>
                           <input type="text" class="inl" id="b10" name="addressLineOne" required="required"/>
                       </div>
                    <% } %>



                    <% if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b3" >Last Name</label>
                            <input type="text" class="inl" id="b3" name='lastName' required="required"/>
                            <label class="divl" class="divl" htmlFor="b11" >CKYC Id</label>
                            <input type="text" class="inl" id="b11" name='CKYCVerificationId' required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b3" >Last Name</label>
                            <input type="text" class="inl" id="b3" name='lastName' required="required"/>
                            <label class="divl" htmlFor="b11" >CTC</label>
                            <input type="text" class="inl" id="b11" name='employeeCTC' required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b3" >Last Name</label>
                            <input type="text" class="inl" id="b3" name='lastName' required="required"/>
                            <label class="divl" htmlFor="b11">Address Line 2</label>
                            <input type="text" class="inl" id="b11" name="addressLineTwo" required="required"/>
                        </div>
                    <% } %>


                                        <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                                            <div id="div1">
                                                <label class="divl" htmlFor="b4" >Gender</label>
                                                <input type="text" class="inl" id="b4" name='gender' required="required"/>
                                                <label class="divl" htmlFor="b12" >YOE</label>
                                                <input type="Number" class="inl" id="b12" name='yearOfExperience' required="required"/>
                                            </div>
                                        <% }else if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                                            <div id="div1">
                                                <label class="divl" htmlFor="b4" >Gender</label>
                                                <input type="text" class="inl" id="b4" name='gender' required="required"/>
                                                <label class="divl" htmlFor="b12">Address Line 1</label>
                                                <input type="text" class="inl" id="b12" name="addressLineOne" required="required"/>
                                            </div>
                                        <% }else if(request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                                            <div id="div1">
                                                <label class="divl" htmlFor="b4" >Gender</label>
                                                <input type="text" class="inl" id="b4" name='gender' required="required"/>
                                                <label class="divl" htmlFor="b12">Address Line 3</label>
                                                <input type="text" class="inl" id="b12" name="addressLineThree" required="required"/>
                                            </div>
                                        <% } %>

                    <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b5" >Age</label>
                            <input type="text" class="inl" id="b5" name='age' required="required"/>
                            <label class="divl" htmlFor="b13">Address Line 1</label>
                            <input type="text" class="inl" id="b13" name="addressLineOne" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b5" >Age</label>
                            <input type="text" class="inl" id="b5" name='age' required="required"/>
                            <label class="divl" htmlFor="b13">Address Line 2</label>
                            <input type="text" class="inl" id="b13" name="addressLineTwo" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b5" >Age</label>
                            <input type="text" class="inl" id="b5" name='age' required="required"/>
                            <label class="divl" htmlFor="b13">City</label>
                            <input type="text" class="inl" id="b13" name="city" required="required"/>
                        </div>
                    <% } %>

                    <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b6" >Mobile-Number</label>
                            <input type="tel" class="inl" id="b6" name="mobileNumber" pattern="[1-9]{1}[0-9]{9}" required>
                            <label class="divl" htmlFor="b14">Address Line 2</label>
                            <input type="text" class="inl" id="b14" name="addressLineTwo" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b6" >Mobile-Number</label>
                            <input type="tel" class="inl" id="b6" name="mobileNumber" pattern="[1-9]{1}[0-9]{9}" required>
                            <label class="divl" htmlFor="b14">Address Line 3</label>
                            <input type="text" class="inl" id="b14" name="addressLineThree" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b6" >Mobile-Number</label>
                            <input type="tel" class="inl" id="b6" name="mobileNumber" pattern="[1-9]{1}[0-9]{9}" required>
                            <label class="divl" htmlFor="b14">State</label>
                            <input type="text" class="inl" id="b14" name="state" required="required"/>
                        </div>
                    <% } %>



                    <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b7" >Email-id</label>
                            <input type="tel" class="inl" id="b7" name='emailId' required="required"/>
                            <label class="divl" htmlFor="b14">Address Line 3</label>
                            <input type="text" class="inl" id="b14" name="addressLineThree” required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b7" >Email-id</label>
                            <input type="tel" class="inl" id="b7" name='emailId' required="required"/>
                            <label class="divl" htmlFor="b14">City</label>
                            <input type="text" class="inl" id="b14" name="city" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("adminSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b7" >Email-id</label>
                            <input type="tel" class="inl" id="b7" name='emailId' required="required"/>
                            <label class="divl" htmlFor="b14">Pin-Code</label>
                            <input type="text" class="inl" id="b14" name="pinCode" required="required"/>
                        </div>
                    <% } %>


                    <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b8" >Password</label>
                            <input type="password" class="inl" id="b8" name='password' required="required"/>
                            <label class="divl" htmlFor="b15">City</label>
                            <input type="text" class="inl" id="b15" name="city" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b8" >Password</label>
                            <input type="password" class="inl" id="b8" name='password' required="required"/>
                            <label class="divl" htmlFor="b15">State</label>
                            <input type="text" class="inl" id="b15" name="state" required="required"/>
                        </div>
                    <% }else{ %>
                        <label htmlFor="b8" >Password</label>
                        <input type="password" id="b8" name='password' required="required"/>
                    <% } %>


                    <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                        <div id="div1">
                            <label class="divl" htmlFor="b17" >Pin-Code</label>
                            <input type="password" class="inl" id="b17" name='pinCode' required="required"/>
                            <label class="divl" htmlFor="b16">State</label>
                            <input type="text" class="inl" id="b16" name="state" required="required"/>
                        </div>
                    <% }else if(request.getParameter("signUpParam").compareTo("customerSignUp")==0){ %>
                        <label htmlFor="b16">Pin-Code</label>
                        <input type="text" id="b16" name="pinCode" required="required"/>
                    <% } %>


                    <% if(request.getParameter("signUpParam").compareTo("employeeSignUp")==0){ %>
                    <%@ taglib prefix="s" uri="/struts-tags" %>
                    <div id ="div1">
                        <label class="divlDrp" htmlFor="b18" >Branch-Id</label>
                                                    <select class="inlDrp" id="b18" name="branchId">
                                                        <s:if test="{#session.branchIds != null}">
                                                            <s:iterator value="#session.branchIds" status="stat" var="branchId">
                                                               <option value="${branchId}">${branchId}</option>
                                                           </s:iterator>
                                                        </s:if>
                                                    </select>
                    </div>
                    <% } %>

                    <button type='submit' name='signUp' >SIGN UP</button>
                </form>
        </div>
    </body>
    <style type="text/css">
            #logo{
                padding:5px;
                width:5%;
                height:80%;
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
            .formDiv {
                            width: 70%;
                            height: 85%;
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
                            height: 100%;
                            border-radius: 35px;
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            justify-content: space-evenly;
                        }
                        label{
                            width:80%;
                            height:3%;
                        }
                        input{
                            border: none;
                            border-radius: 15px;
                            width:80%;
                            height:5.5%;
                            outline: none;
                            text-align: left;
                            text-indent: 10px;
                            box-shadow: inset 5px 5px 5px #cbced1,
                            inset -5px -5px 5px #ffffff;
                          }
                        #div1{
                            display:flex;
                            justify-content: space-around;
                            align-items: center;
                            height:5.5%;
                            width:80%;
                        }
                        .inl{
                            border: none;
                            border-radius: 15px;
                            width:40%;
                            height:100%;
                            outline: none;
                            text-align: left;
                            text-indent: 10px;
                            box-shadow: inset 5px 5px 5px #cbced1,
                            inset -5px -5px 5px #ffffff;
                        }
                        .divldrp{
                                                    width:50%;
                                                    padding-left:2%;
                                                    padding-bottom:1.7%;
                                                }
                        .inldrp{
                                                    border: none;
                                                    border-radius: 15px;
                                                    width:50%;
                                                    outline: none;
                                                    text-align: left;
                                                    text-indent: 10px;
                                                    box-shadow: inset 5px 5px 5px #cbced1,
                                                    inset -5px -5px 5px #ffffff;
                                                }
                        .divl{
                            width:20%;
                            padding-left:2%;
                            padding-bottom:1.7%;
                        }
                        button{
                            border: none;
                            border-radius: 35px;
                            width:30%;
                            height:6%;
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
                        .switch {
                                      position: relative;
                                      display: inline-block;
                                      width: 80px;
                                      height: 30px;
                                      cursor: pointer;
                                    }
                                    .switch input {
                                      height:20px;
                                      display: none;
                                    }
                                    .slider {
                                      position: absolute;
                                      top: 0;
                                      left: 0;
                                      right: 0;
                                      bottom: 0;
                                      width:70px;
                                      border: 2px solid #ecf0f3;
                                      border-radius: 20px;
                                      box-shadow: -2px -2px 8px rgba(255, 255, 255, 1),
                                                  -2px -2px 12px rgba(255, 255, 255, 0.5),
                                                  inset -2px -2px 8px rgba(255, 255, 255, 1),
                                                  inset -2px -2px 12px rgba(255, 255, 255, 0.5),
                                                  inset 2px 2px 4px rgba(255, 255, 255, 0.1),
                                                  inset 2px 2px 8px rgba(0, 0, 0, .3),
                                                  2px 2px 8px rgba(0, 0, 0, .3);
                                      background-color: #ecf0f3;
                                    }
                                    .slider:before {
                                      position: absolute;
                                      bottom: 5px;
                                      left: 10px;
                                      z-index: 999;
                                      width: 17px;
                                      height: 17px;
                                      border-radius: 50%;
                                      box-shadow: -2px -2px 8px rgba(255, 255, 255, 1),
                                                  -2px -2px 12px rgba(255, 255, 255, 0.5),
                                                  inset 2px 2px 4px rgba(255, 255, 255, 0.1),
                                                  2px 2px 8px rgba(0, 0, 0, .3);
                                      background-color: #ecf0f3;
                                      content: "";
                                      transition: .5s;
                                    }
                                    .off {
                                      position: absolute;
                                      top: 14px;
                                      right: 12px;
                                      color: #3d3935;
                                      font-size: .8125rem;
                                    }
                                    .on {
                                      position: absolute;
                                      top: 14px;
                                      left: 14px;
                                      color: #f252a5;
                                      font-size: .8125rem;
                                    }
                                    input:checked + .slider:before {
                                      transform: translateX(33px);
                                      background-color:#068FFF;
                                    }
                                    .inldiv{
                                        border: none;
                                        width:40%;
                                        height:100%;
                                        outline: none;
                                    }
        </style>
</html>
