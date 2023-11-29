<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car Dealer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }

        nav {
            display: flex;
            justify-content: center;
            background-color: #555;
            padding: 10px;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-weight: bold;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            padding: 10px;
        }

        input {
            display: flex;
            width: 50%;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            text-align: center;
        }

        .button {
            /*background-color: #ffeb3b;*/
            background-color: #4caf50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
        }

        button:hover {
            background-color: #45a049;
        }
        .container {
            justify-content: center;
            align-items: center;
            display: block;
            margin: 0;
            padding: 2%;
        }
    </style>
    
    <script>
        
        function showForm(formId) {
            // Hide all forms
            var forms = document.getElementsByClassName('form');
            for (var i = 0; i < forms.length; i++) {
                forms[i].style.display = 'none';
            }
            // Show the selected form
            document.getElementById(formId).style.display = 'block';
            // Highlight the selected tab
            var tabs = document.getElementsByClassName('tab');
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove('active-tab');
            }
            document.getElementById(formId + '-tab').classList.add('active-tab');
        }
    </script>
</head>
    <body>
        <header>
            <h1>Car Dealer Dashboard</h1>
        </header>
        
        <div class="navbar">
            <nav>
                <a class="tab active-tab pointer" id="customerForm-tab" onclick="showForm('customerForm')">Customer</a>
                <a class="tab pointer" id="carForm-tab" onclick="showForm('carForm')">Mobil</a>
                <a class="tab pointer" id="transactionForm-tab" onclick="showForm('transactionForm')">Transaksi</a>
                <a class="tab pointer" id="jasperReportForm-tab" onclick="showForm('jasperReportForm')">Laporan</a>
            </nav>    
        </div>
        
        <div class="container">
            <div id="customerForm" class="form">
                <jsp:include page="customerForm.jsp" />
            </div>
            <div id="carForm" class="form" style="display: none;">
                <jsp:include page="carForm.jsp" />
            </div>
            <div id="transactionForm" class="form" style="display: none;">
                <jsp:include page="transactionForm.jsp" />
            </div>
            <div id="jasperReportForm" class="form" style="display: none;">
                <jsp:include page="jasperReportForm.jsp" />
            </div>
        </div>
    </body>
</html>
