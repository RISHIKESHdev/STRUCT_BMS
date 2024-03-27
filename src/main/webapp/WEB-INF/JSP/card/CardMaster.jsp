<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kode+Mono:wght@400..700&display=swap" rel="stylesheet">
        <title>LV-BANK</title>
        <script type="text/javascript"
                                src="jquery-ui-1.10.0/tests/jquery-1.9.0.js"></script>
                            <script src="jquery-ui-1.10.0/ui/jquery-ui.js"></script>
                            <script>
                            function populate(drpType){
                                var a = drpType.selectedIndex;
                                if(a==0){
                                document.getElementById("rot").style.display='block';
                                document.getElementById("ifc").style.display='block';
                                document.getElementById("b5").style.display='block';
                                document.getElementById("b6").style.display='block';
                                document.getElementById("cmn").style.display='none';
                                document.getElementById("b7").style.display='none';
                                document.getElementById("cmno").style.display='none';
                                document.getElementById("b8").style.display='none';
                                document.getElementById("debit").style.display='none';
                                document.getElementById("b4").style.display='none';
                                }else if(a==1){
                                document.getElementById("rot").style.display='block';
                                document.getElementById("ifc").style.display='block';
                                document.getElementById("b5").style.display='block';
                                document.getElementById("b6").style.display='block';
                                document.getElementById("cmn").style.display='block';
                                document.getElementById("b7").style.display='block';
                                document.getElementById("cmno").style.display='block';
                                document.getElementById("b8").style.display='block';
                                document.getElementById("debit").style.display='none';
                                document.getElementById("b4").style.display='none';
                                }else{
                                document.getElementById("rot").style.display='none';
                                document.getElementById("ifc").style.display='none';
                                document.getElementById("b5").style.display='none';
                                document.getElementById("b6").style.display='none';
                                document.getElementById("cmn").style.display='none';
                                document.getElementById("b7").style.display='none';
                                document.getElementById("cmno").style.display='none';
                                document.getElementById("b8").style.display='none';
                                document.getElementById("debit").style.display='block';
                                document.getElementById("b4").style.display='block';}
                            }
                            </script>
    </head>
    <body id="body-main">
        <div id="nav-bar">
            <img id="logo" src="logo">
            <p>Lake View Bank</p>
            <div> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Card Master Registration </div>
        </div>
        <div id="body-box">
            <form class="formBody" name="signUpUserForm" method="post" action="addCardMaster">
                    <div id ="div1">
                        <label class="divlDrp" htmlFor="b2" >Card Type</label>
                        <select class="inlDrp" id="b2" name="cardType" onchange="populate(this);">
                            <option value="creditCard">Credit Card</option>
                            <option value="coBrandedCreditCard">Co Branded Credit Card</option>
                            <option value="debitCard">Debit Card</option>
                        </select>
                    </div>
                    <label htmlFor="b2">Card Name</label>
                    <input type="text" id="b2" name="cardName" required="required"/>
                    <label htmlFor="b3">Card Payment Gateway</label>
                    <input type="text" id="b3" name="cardPaymentGateway" required="required"/>

                        <label id ="debit" htmlFor="b4">Debit Card Withdrawal Limit</label>
                        <input type="number" id="b4" name="withdrawalLimit"/>

                        <label id ="ifc" htmlFor="b5">Interest Free Credit Days</label>
                        <input type="number" id="b5" name="interestFreeCreditDays"/>
                        <label id ="rot" htmlFor="b6">Rate Of Interest</label>
                        <input type="number" step="0.01" id="b6" name="rateOfInterest"/>

                        <label id ="cmn" htmlFor="b7">Credit Card Merchant Name</label>
                        <input type="text" id="b7" name="merchantName"/>
                        <label id ="cmno" htmlFor="b8">Credit Card Merchant Offer Percentage</label>
                        <input type="number" step="0.01" id="b8" name="merchantOfferPercentage"/>

                    <button type='submit' name='signUp' >Add Card Master</button>
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
        </style>
</html>
