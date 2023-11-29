<!DOCTYPE html>
<html>
<head>
    <title>Add Transaction</title>
</head>
<body>
    <form action="addTransaction" method="post">
        <label for="customerId">Customer ID:</label>
        <input type="number" id="customerId" name="customerId" required>
        <br>

        <label for="carId">Car ID:</label>
        <input type="number" id="carId" name="carId" required>
        <br>

        <!-- Add more input fields for other transaction attributes -->

        <input type="submit" class="button" value="Tambah Transaksi">
    </form>
</body>
</html>
