<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kode+Mono:wght@400..700&display=swap" rel="stylesheet">

        <title>LV-BANK</title>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script type="text/javascript"
                src="jquery-ui-1.10.0/tests/jquery-1.9.0.js"></script>
            <script src="jquery-ui-1.10.0/ui/jquery-ui.js"></script>
            <script>
            function populate(drpType){
                var a = drpType.selectedIndex;
                if(a==0){
                document.getElementById("b6").style.display='none';
                document.getElementById("b7").style.display='none';
                document.getElementById("b4").style.display='none';
                document.getElementById("b9").style.display='none';
                document.getElementById("b5").style.display='none';
                document.getElementById("b8").style.display='none';
                }else if(a==1){
                document.getElementById("b6").style.display='block';
                document.getElementById("b4").style.display='block';
                document.getElementById("b7").style.display='block';
                document.getElementById("b9").style.display='block';
                document.getElementById("b5").style.display='none';
                document.getElementById("b8").style.display='none';
                }else{
                document.getElementById("b6").style.display='block';
                document.getElementById("b5").style.display='block';
                document.getElementById("b7").style.display='block';
                document.getElementById("b8").style.display='block';
                document.getElementById("b4").style.display='none';
                document.getElementById("b9").style.display='none';}
            }
            </script>
    </head>

    <body id="body-main">
        <div id="nav-bar">
            <img id="logo" src="logo">
            <p>Lake View Bank</p>
            <div> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Account Creation </div>
        </div>
        <div id="body-box">
            <form class="formBody" name="createCustomerForm" method="post" action="createNewAccount">
                    <%@ taglib prefix="s" uri="/struts-tags" %>
                    <div id ="div1">
                        <label class="divlDrp" htmlFor="b1" >Customer-Id</label>

                    <select class="inlDrp" id="b1" name="CIFNumber">
                        <s:if test="{#request.branchCustomerIds != null}">
                            <s:iterator value="#request.branchCustomerIds" status="stat" var="CIFNumber">
                                <option value="${CIFNumber}">${CIFNumber}</option>
                            </s:iterator>
                        </s:if>
                    </select>



                    </div>
                    <div id ="div1">
                        <label class="divlDrp" htmlFor="b2" >Account-Type</label>
                        <select class="inlDrp" id="b2" name="accountType" onchange="populate(this);">
                            <option value="Current Account">Current Account</option>
                            <option value="Saving Account">Saving Account</option>
                            <option value="FD Account">FD Account</option>
                        </select>
                    </div>
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
                    <label htmlFor="b3">Initial Current Balance</label>
                    <input type='number' step="0.0001" id='b3' name="currentBalance" required="required"/>
                    <label id='b9' htmlFor="b4">Withdrawal Limit</label>
                    <input type='number' step="0.0001" id='b4' name="withdrawalLimit"/>
                    <label id='b8' htmlFor="b5">Tenure</label>
                    <input type='number' id='b5' name="tenure"/>
                    <label id='b7' htmlFor="b6">Rate Of Interest</label>
                    <input type='number' step="0.01" id='b6' name="rateOfInterest"/>


                            <label htmlFor="b10" >First Name</label>
                            <input type="text" id="b10" name='firstName' required="required"/>
                            <label htmlFor="b11" >Middle Name</label>
                            <input type="text" id="b11" name='middleName'/>
                            <label htmlFor="b12" >Last Name</label>
                            <input type="text" id="b12" name='lastName' required="required"/>
                            <label htmlFor="b13" >Gender</label>
                            <input type="text" id="b13" name='gender' required="required"/>
                            <label htmlFor="b14" >Age</label>
                            <input type="number" id="b14" name='age' required="required"/>
                            <label htmlFor="b15" >Mobile-Number</label>
                            <input type="tel" id="b15" name="mobileNumber" pattern="[1-9]{1}[0-9]{9}" required>
                            <label htmlFor="b16" >Email-id</label>
                            <input type="tel" id="b16" name='emailId' required="required"/>
                            <label htmlFor="b17" >CKYC Doc</label>
                            <input type="text" id="b17" name='CKYCVerificationDocument' required="required"/>
                            <label htmlFor="b18" >CKYC Id</label>
                            <input type="text" id="b18" name='CKYCVerificationId' required="required"/>


                    <button type='submit' name='accountCreation' >Create Account</button>
                </form>
        </div>
    </body>
    <style type="text/css">

            #b4{
                           display:none;} #b5{
                                                         display:none;} #b6{
                                                                                       display:none;} #b7{
                                                                                                                     display:none;} #b8{
                                                                                                                                                   display:none;} #b9{
            display:none;}
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
        </style>
</html>
