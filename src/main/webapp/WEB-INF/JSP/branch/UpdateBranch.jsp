<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Kode+Mono:wght@400..700&display=swap" rel="stylesheet">
        <title>LV-BANK</title>
        <script language="JavaScript" type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery-ui-personalized-1.5.2.packed.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/sprinkle.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>
        $(document).ready(function() {
$.ajaxSetup({
        cache: false,
    });

            $("#b1").change(function() {

                $("#b3").find("option").remove();
                $("#b3").append("<option>Select Account Number</option>");
                console.log("hi")
                $.ajax({
                    url: "AccountId",
                    method: "GET",
                    success: function(data, textStatus, jqXHR) {
                        console.log(data);
                        let obj = $.parseJSON(data);
                        $.each(obj, function(key, value) {
                            $("#b3").append(
                                '<option value="' + value.id + '">' + value.name + "</option>"
                            );
                        });
                        $("select").formSelect();
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        $("#b3").append("<option>Account Unavailable</option>");
                    },
                    cache: false,
                });
            });



        });
        </script>
    </head>
    <body id="body-main">
        <div id="nav-bar">
            <img id="logo" src="logo">
            <p>Lake View Bank</p>
            <div> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Update Branch </div>
        </div>
        <div id="body-box">
            <form class="formBody" name="signUpUserForm" method="post" action="doUpdateBranch">
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
                    <label class="divlDrp" htmlFor="b2" >New Branch-Id</label>
                    <select class="inlDrp" id="b2" name="accountNumber">
                                                <s:if test="{#session.branchIds != null}">
                                                    <s:iterator value="#session.branchIds" status="stat" var="branchId">
                                                        <option value="${branchId}">${branchId}</option>
                                                    </s:iterator>
                                                </s:if>
                                            </select>
                    </div>
                        <label class="divlDrp" htmlFor="b3" >Account Number</label>
                            <select class="inlDrp" id="b3" name="accountNumber">
                                                        <s:if test="{#session.customerAccountNumbers != null}">
                                                            <s:iterator value="#session.customerAccountNumbers" status="stat" var="accountNumber">
                                                                <option value="${accountNumber}">${accountNumber}</option>
                                                            </s:iterator>
                                                        </s:if>
                                                    </select>
                    </div>

                <button type='submit' name='addBranch' >Add Branch</button>
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
