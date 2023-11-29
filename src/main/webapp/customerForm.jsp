<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <form action="addCustomer" method="post">
        <label for="customer_id">ID:</label>
        <input type="number" id="customer_id" name="customer_id" required>
        <br>
        <label for="name">Nama:</label>
        <input type="text" id="nama" name="nama" required>
        <br>

        <label for="lastName">Email:</label>
        <input type="text" id="email" name="email" required>
        <br>
        
        <input type="submit" class="button" value="Tambah Customer">
    </form>
</body>
</html>
