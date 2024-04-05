const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser'); 

const app = express();

// Definir la ruta para eliminar una empresa por su ID
app.delete('/empresas/:id', (req, res) => {
  const empresaId = req.params.id; // Obtener el ID de la empresa de la URL

  // Ejecutar la consulta SQL para eliminar la empresa de la base de datos
  const sql = `DELETE FROM Empresa WHERE id = ?`;
  connection.query(sql, [empresaId], (error, results) => {
    if (error) {
      // Enviar una respuesta de error si ocurre un error al ejecutar la consulta
      res.status(500).json({ error: 'Error al eliminar la empresa' });
    } else {
      // Enviar una respuesta de éxito si la empresa se eliminó correctamente
      res.status(200).json({ message: 'Empresa eliminada correctamente' });
    }
  });
});

// Agregar una ruta para eliminar noticias por su ID
app.delete('/noticias/:id', (req, res) => {
  const noticiaId = req.params.id; // Obtener el ID de la noticia de la URL

  // Ejecutar la consulta SQL para eliminar la noticia de la base de datos
  const sql = `DELETE FROM Noticia WHERE id = ?`;
  connection.query(sql, [noticiaId], (error, results) => {
    if (error) {
      // Enviar una respuesta de error si ocurre un error al ejecutar la consulta
      res.status(500).json({ error: 'Error al eliminar la noticia' });
    } else {
      // Enviar una respuesta de éxito si la noticia se eliminó correctamente
      res.status(200).json({ message: 'Noticia eliminada correctamente' });
    }
  });
});


// Ruta para eliminar noticias por el ID de la empresa
app.delete('/empresas/:id/noticias', (req, res) => {
  const idEmpresa = req.params.id; // Obtener el ID de la empresa de la URL

  // Ejecutar la consulta SQL para eliminar las noticias de la empresa de la base de datos
  const sql = `DELETE FROM Noticia WHERE idEmpresa = ?`;
  connection.query(sql, [idEmpresa], (error, results) => {
    if (error) {
      // Enviar una respuesta de error si ocurre un error al ejecutar la consulta
      res.status(500).json({ error: 'Error al eliminar las noticias de la empresa' });
    } else {
      // Enviar una respuesta de éxito si las noticias se eliminaron correctamente
      res.status(200).json({ message: 'Noticias eliminadas correctamente' });
    }
  });
});

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
    res.redirect('/admin.html');
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
    res.redirect('/admin.html');
  });
});

app.put('/modificar_empresa/:id', (req, res) => {
  const idEmpresa = req.params.id;
  const datosFormulario = req.body;

  // Actualizar datos en la tabla de empresas en la base de datos
  const sql = 'UPDATE Empresa SET Denominacion = ?, Telefono = ?, HorarioAtencion = ?, QuienesSomos = ?, Latitud = ?, Longitud = ?, Domicilio = ?, Email = ? WHERE id = ?';
  const valores = [
    datosFormulario.denominacion,
    datosFormulario.telefono,
    datosFormulario.horario,
    datosFormulario.quienes_somos,
    datosFormulario.latitud,
    datosFormulario.longitud,
    datosFormulario.domicilio,
    datosFormulario.email,
    idEmpresa
  ];

  connection.query(sql, valores, (err, result) => {
    if (err) {
      console.error('Error al actualizar datos en la base de datos: ' + err.stack);
      return res.status(500).send('Error al modificar la empresa');
    }
    console.log('Datos actualizados correctamente en la base de datos');
    res.redirect('/admin');
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

app.get('/empresas/:id', (req, res) => {
  const idEmpresa = req.params.id;

  // Consultar la base de datos para obtener la información de la empresa por su ID
  const sql = 'SELECT Denominacion, QuienesSomos, Telefono FROM Empresa WHERE id = ?';
  connection.query(sql, [idEmpresa], (err, result) => {
    if (err) {
      console.error('Error al obtener la información de la empresa: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener la información de la empresa' });
    }
    if (result.length === 0) {
      return res.status(404).json({ error: 'Empresa no encontrada' });
    }
    // El resultado es un array, debes acceder al primer elemento para obtener los datos
    const empresaInfo = result[0];
    res.json({ 
      denominacion: empresaInfo.Denominacion, 
      quienesSomos: empresaInfo.QuienesSomos,
      telefono: empresaInfo.Telefono // Nombre de propiedad corregido
    });
  });
});

app.get('/noticias', (req, res) => {
  connection.query('SELECT TituloNoticia, ResumenNoticia FROM Noticia WHERE Publicada = 1', (err, results) => {
    if (err) {
      console.error('Error al obtener noticias: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener noticias' });
    }
    res.json(results);
  });
});

// Ruta para obtener noticias por ID de empresa
app.get('/noticias/:idEmpresa', (req, res) => {
  const idEmpresa = req.params.idEmpresa;

  // Consultar la base de datos para obtener las noticias de la empresa por su ID
  const sql = 'SELECT TituloNoticia, ResumenNoticia FROM Noticia WHERE idEmpresa = ? AND Publicada = 1';
  connection.query(sql, [idEmpresa], (err, results) => {
    if (err) {
      console.error('Error al obtener noticias: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener noticias' });
    }
    res.json(results);
  });
});

app.put('/empresas/:id', async (req, res) => {
  const id = parseInt(req.params.id);
  try {
    // Busca la empresa por su ID
    const empresa = await Empresa.findByPk(id);
    if (empresa) {
      // Actualiza la empresa con los datos proporcionados en el cuerpo de la solicitud
      await empresa.update(req.body);
      res.json({ message: 'Empresa actualizada correctamente', empresa });
    } else {
      res.status(404).json({ message: 'Empresa no encontrada' });
    }
  } catch (error) {
    console.error('Error al actualizar la empresa:', error);
    res.status(500).json({ message: 'Error interno del servidor' });
  }
});

// Servir archivos estáticos desde el directorio 'public'
app.use(express.static('public'));

// Iniciar el servidor
const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Servidor Express en funcionamiento en el puerto ${PORT}`);
});
