const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser'); 

const app = express();
app.use(bodyParser.urlencoded({ extended: true })); 

// Configuración de la conexión a la base de datos
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'root',
  database: 'Fcaultad'
});

// Ruta para manejar la solicitud de envío del formulario de empresas
app.post('/enviar_empresa', (req, res) => {
  const datosFormulario = req.body;

  // Insertar datos en la tabla de empresas en la base de datos
  const sql = 'INSERT INTO Empresa (Denominacion, Telefono, HorarioAtencion, QuienesSomos, Latitud, Longitud, Domicilio, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)';
  const valores = [
    datosFormulario.denominacion,
    datosFormulario.telefono,
    datosFormulario.horario,
    datosFormulario.quienes_somos,
    datosFormulario.latitud,
    datosFormulario.longitud,
    datosFormulario.domicilio,
    datosFormulario.email
  ];

  connection.query(sql, valores, (err, result) => {
    if (err) {
      console.error('Error al insertar datos en la base de datos: ' + err.stack);
      return res.status(500).send('Error al enviar el formulario de empresa');
    }
    console.log('Datos insertados correctamente en la base de datos');
    res.send('¡Formulario de empresa enviado correctamente!');
  });
});

// Ruta para manejar la solicitud de envío del formulario de noticias
app.post('/enviar_noticia', (req, res) => {
  const datosFormulario = req.body;

  // Insertar datos en la tabla de noticias en la base de datos
  const sql = 'INSERT INTO Noticia (TituloNoticia, ResumenNoticia, ImagenNoticia, ContenidoHTML, Publicada, FechaPublicacion, idEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)';
  const valores = [
    datosFormulario.titulo,
    datosFormulario.resumen,
    datosFormulario.imagen,
    datosFormulario.contenidoHTML,
    datosFormulario.publicada ? 1 : 0, 
    datosFormulario.fecha,
    datosFormulario.empresa
  ];

  connection.query(sql, valores, (err, result) => {
    if (err) {
      console.error('Error al insertar datos en la base de datos: ' + err.stack);
      return res.status(500).send('Error al enviar el formulario de noticia');
    }
    console.log('Datos insertados correctamente en la base de datos');
    res.send('¡Formulario de noticia enviado correctamente!');
  });
});

// Ruta para obtener las empresas desde la base de datos
app.get('/empresas', (req, res) => {
  connection.query('SELECT id, Denominacion FROM Empresa', (err, results) => {
    if (err) {
      console.error('Error al obtener empresas: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener empresas' });
    }
    res.json(results);
  });
});

// Ruta para obtener la denominación de una empresa por su ID
app.get('/empresas/:id', (req, res) => {
  const idEmpresa = req.params.id;

  // Consultar la base de datos para obtener la denominación de la empresa por su ID
  const sql = 'SELECT Denominacion FROM Empresa WHERE id = ?';
  connection.query(sql, [idEmpresa], (err, result) => {
    if (err) {
      console.error('Error al obtener la denominación de la empresa: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener la denominación de la empresa' });
    }
    if (result.length === 0) {
      return res.status(404).json({ error: 'Empresa no encontrada' });
    }
    // El resultado es un array, debes acceder al primer elemento para obtener la denominación
    const denominacion = result[0].Denominacion;
    res.json({ denominacion: denominacion });
  });
});


// Servir archivos estáticos desde el directorio 'public'
app.use(express.static('public'));

// Iniciar el servidor
const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Servidor Express en funcionamiento en el puerto ${PORT}`);
});
