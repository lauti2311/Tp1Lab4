<?php
// Conexión a la base de datos
$servername = "localhost";
$username = "root";
$password = "root";
$database = "Fcaultad";

$conn = new mysqli($servername, $username, $password, $database);

// Verificar la conexión
if ($conn->connect_error) {
    die("La conexión ha fallado: " . $conn->connect_error);
}

// Consultar las empresas
$sql = "SELECT * FROM Empresa";
$result = $conn->query($sql);

$empresas = array();

if ($result->num_rows > 0) {
    // Si hay empresas, agregarlas al array
    while($row = $result->fetch_assoc()) {
        $empresas[] = $row;
    }
}

// Cerrar la conexión
$conn->close();

// Devolver las empresas en formato JSON
header('Content-Type: application/json');
echo json_encode($empresas);
?>
