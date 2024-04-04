const express = require('express');
const mysql = require('mysql');

const app = express();

// Configuración de la conexión a la base de datos
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'root',
  database: 'Fcaultad'
});

// Conectar a la base de datos
connection.connect((err) => {
  if (err) {
    console.error('Error al conectar a la base de datos: ' + err.stack);
    return;
  }
  console.log('Conexión a la base de datos exitosa con el ID: ' + connection.threadId);

  // Aquí puedes realizar consultas a la base de datos

  // Consulta SQL para obtener la denominación de la empresa con ID 
  const sql = 'SELECT Denominacion FROM Empresa WHERE id = ?';
  const idEmpresa = 3;

  connection.query(sql, [idEmpresa], (err, result) => {
    if (err) {
      console.error('Error al ejecutar la consulta: ' + err.stack);
      return;
    }
    console.log('La denominación de la empresa con ID ' + idEmpresa + ' es: ' + result[0].Denominacion);

    connection.end();
  });
});

 
