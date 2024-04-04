<?php
// Configuración de la conexión a la base de datos
$servername = "localhost"; // Cambia localhost por el nombre del servidor si es diferente
$username = "root";
$password = "root";
$dbname = "Fcaultad";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Consulta SQL para obtener la denominación de la empresa
$sql = "SELECT denominacion FROM Empresa";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Si se encontraron resultados
    while($row = $result->fetch_assoc()) {
        // Imprimir el valor de la columna denominacion
        echo "Denominación: " . $row["denominacion"];
        print("Se conecto exitosamente la base de datos");
    }
} else {
    echo "No se encontraron resultados";
}

// Cerrar conexión
$conn->close();
?>
