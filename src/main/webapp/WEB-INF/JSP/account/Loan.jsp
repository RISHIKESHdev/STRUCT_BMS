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
                document.getElementById("b5").style.display='block';
                document.getElementById("b6").style.display='block';
                document.getElementById("b7").style.display='block';
                document.getElementById("b8").style.display='block';
                document.getElementById("b9").style.display='block';
                document.getElementById("b10").style.display='block';
                document.getElementById("b11").style.display='none';
                document.getElementById("b12").style.display='none';
                document.getElementById("b13").style.display='none';
                document.getElementById("b14").style.display='none';
                document.getElementById("b15").style.display='none';
                document.getElementById("b16").style.display='none';
                document.getElementById("b17").style.display='none';
                document.getElementById("b18").style.display='none';
                }else if(a==1){
                document.getElementById("b5").style.display='none';
                document.getElementById("b6").style.display='none';
                document.getElementById("b7").style.display='none';
                document.getElementById("b8").style.display='none';
                document.getElementById("b9").style.display='none';
                document.getElementById("b10").style.display='none';
                document.getElementById("b11").style.display='block';
                document.getElementById("b12").style.display='block';
                document.getElementById("b13").style.display='block';
                document.getElementById("b14").style.display='block';
                document.getElementById("b15").style.display='block';
                document.getElementById("b16").style.display='block';
                document.getElementById("b17").style.display='block';
                document.getElementById("b18").style.display='block';
                }}
            </script>
    </head>

    <body id="body-main">
        <div id="nav-bar">
            <img id="logo" src="../../images/logo.png">
            <p>Lake View Bank</p>
            <div> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Register Loan </div>
        </div>
        <div id="body-box">
            <form class="formBody" name="createCustomerForm" method="post" action="AddLoan">

                    <div id ="div1">
                        <label class="divlDrp" htmlFor="b2" >Loan-Type</label>
                        <select class="inlDrp" id="b2" name="loanType" onchange="populate(this);">
                            <option value="goldLoan">Gold Loan</option>
                            <option value="homeLoan">Home Loan</option>
                        </select>
                    </div>
    <%@ taglib prefix="s" uri="/struts-tags" %>
                        <div id ="div1">
                                                <label class="divlDrp" htmlFor="b1" >Account Number</label>

                    <select class="inlDrp" id="b1" name="CIFNumber">
                        <s:if test="{#session.customerAccountNumbers != null}">
                            <s:iterator value="#session.customerAccountNumbers" status="stat" var="accountNumber">
                                <option value="${accountNumber}">${accountNumber}</option>
                            </s:iterator>
                        </s:if>
                    </select>
                    </div>
                    <label htmlFor="b3" >Loan Amount</label>
                    <input type="text" id="b3" name='loanAmount' required="required"/>
                    <label htmlFor="b4" >Interest Rate</label>
                    <input type="text" id="b4" name='interestRate' required="required"/>

                    <label id='b10' htmlFor="b5">Gold Purity</label>
                    <input type='number' id='b5' name="goldPurity" required="required"/>
                    <label id='b9' htmlFor="b^">Gold Value</label>
                    <input type='text' id='b6' name="goldValue"/>
                    <label id='b8' htmlFor="b7">Gold Weigh</label>
                    <input type='text' id='b7' name="goldWeigh"/>

                    <label id='b18' htmlFor="b11">Total Area</label>
                    <input type='text' id='b11' name="totalArea" required="required"/>
                    <label id='b17' htmlFor="b12">Built Up Area</label>
                    <input type='text' id='b12' name="builtUpArea"/>
                    <label id='b16' htmlFor="b13">No Of Floors</label>
                    <input type='number' id='b13' name="noOfFloors"/>
                    <label id='b15' htmlFor="b14">Total Value</label>
                    <input type='text' id='b14' name="totalValue"/>



                    <button type='submit' name='loanCreation' >Register Loan</button>
                </form>
        </div>
    </body>
    <style type="text/css">

            #b11{
            display:none;}
            #b12,#b13,#b14,#b15,#b16,#b17,#b18{
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
