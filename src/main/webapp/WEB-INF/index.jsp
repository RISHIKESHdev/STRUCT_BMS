<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
              <form action="loginIndex" id="button-box">
                  <button name="entryParam" value="customerLogin" class="unit" type="submit">Log In As Customer</button>
                  <button name="entryParam" value="employeeLogin" class="unit" type="submit">Log In As Employee</button>
                  <button name="entryParam" value="adminLogin" class="unit" type="submit">Log In As Admin</button>
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
                  height:800px;
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
                  height:100%;
              }
              button:hover {
                                cursor: pointer;
                                box-shadow: -3px -3px 3px rgba(255, 255, 255, 0.8),3px 3px 3px rgba(0, 0, 0, 0.2);
                                transition: 0.5s;
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
          </style>
  </html>