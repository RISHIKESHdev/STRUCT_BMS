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
            <ul id="nav-div-two">
                <% if(((String)session.getAttribute("role")).compareTo("Customer")==0){ %>
                    <li class="nav-li"><a href="createAccount">Add Account</a></li>
                    <li class="nav-li"><a href="openTransferPage">Payment</a></li>
                    <li class="nav-li"><a href="registerCard">Card</a></li>
                    <li class="nav-li"><a href="openLoanPage">Loan</a></li>
                    <li class="nav-li"><a href="#Transaction">Transactions</a></li>
                <% }else if(((String)session.getAttribute("role")).compareTo("Employee")==0){ %>
                    <li class="nav-li"><a href="createAccount">Create Account</a></li>
                    <li class="nav-li"><a href="openCardMasterPage">Card Master</a></li>
                    <li class="nav-li"><a href="updateBranch">Branch Change</a></li>
                <% }else if(((String)session.getAttribute("role")).compareTo("Admin")==0){ %>
                    <li class="nav-li"><a href="signUpEmployee">Add Employee</a></li>
                    <li class="nav-li"><a href="signUpAdmin">Add Admin</a></li>
                    <li class="nav-li"><a href="addBranch">Add Branch</a></li>
                    <li class="nav-li"><a href="#updateEmployee">Update Employee</a></li>
                    <li class="nav-li"><a href="dropEmployee">Drop Employee</a></li>
                <% } %>
                <li class="nav-li"><a href="logoutUser">Logout</a></li>
            </ul>
        </div>
        <div id="body-box">

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
                height:11%;
                background: #ecf0f3;
                border-radius: 20px;
                margin: 20px;
                box-shadow: -6px -6px 6px rgba(255, 255, 255, 0.8),6px 6px 6px rgba(0, 0, 0, 0.2);
            }

            #nav-div-two{
              display:flex;
              justify-content: space-evenly;
              align-items: center;
                width:77%;
                height:70%;
                background: #ecf0f3;
                border-radius: 20px;
                margin: 20px;
                overflow: hidden;
                list-style-type: none;
            }


            .nav-li {
              height:50%;
              margin: 0 1rem;
              padding: 0.5rem 1.5rem;
              border: 2px solid rgba(255, 255, 255, 0.3);
              box-shadow: 4px 4px 6px 0 rgba(0, 0, 0, 0.1), -4px -4px 6px white;
              border-radius: 10px;
              text-decoration: none;
              text-align: center;
              display:flex;
              align-items: center;
              cursor: pointer;
              transition: color 0.2s ease-out, transform 0.2s ease-out;
              color: #f2f2f2;
            }
            .nav-li a{
            text-decoration: none;
            color: black;
            }


            .nav-li:hover {
              transform: scale(1.05);
              box-shadow: 4px 4px 10px 0 rgba(0, 0, 0, 0.1), -4px -4px 10px white;
            }
            .nav-li:focus {
                outline: none;
                transform: scale(0.95);
                box-shadow: 4px 4px 10px 0 rgba(0, 0, 0, 0.1), -4px -4px 10px white, 4px 4px 10px 0 rgba(0, 0, 0, 0.1) inset, -4px -4px 10px white inset;
            }
            .nav-li:hover, .nav-li:focus {
                color: orangered;
            }






            #body-box{
                display:flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                width:100%;
                height:89%;
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
        </style>
</html>
