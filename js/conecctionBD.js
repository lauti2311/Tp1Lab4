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
      return res.status(500).send(`
        <div style="text-align: center; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
          <p style="font-family: Arial, sans-serif; color: red;">Error al enviar el formulario de empresa</p>
          <button style="background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;" onclick="window.location.href = 'http://localhost:3000/admin.html';">Volver</button>
        </div>
      `);
    }
    console.log('Datos insertados correctamente en la base de datos');
    res.send(`
      <div style="text-align: center; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
        <p style="font-family: Arial, sans-serif; color: green;">¡Formulario de empresa enviado correctamente!</p>
        <button style="background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;" onclick="window.location.href = 'http://localhost:3000/admin.html';">Volver</button>
      </div>
    `);
  });
});

// Ruta para manejar la solicitud de envío del formulario de noticias
app.post('/enviar_noticia', (req, res) => {
  const datosFormulario = req.body;

  // Insertar datos en la tabla de noticias en la base de datos
  const sqlInsert = 'INSERT INTO Noticia (TituloNoticia, ResumenNoticia, ImagenNoticia, ContenidoHTML, Publicada, FechaPublicacion, idEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)';
  const valoresInsert = [
    datosFormulario.titulo,
    datosFormulario.resumen,
    datosFormulario.imagen,
    datosFormulario.contenidoHTML,
    datosFormulario.publicada ? 1 : 0, 
    datosFormulario.fecha,
    datosFormulario.empresa
  ];

  connection.query(sqlInsert, valoresInsert, (err, result) => {
    if (err) {
      console.error('Error al insertar datos en la base de datos: ' + err.stack);
      return res.status(500).send(`
        <div style="text-align: center; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
          <p style="font-family: Arial, sans-serif; color: red;">Error al enviar formulario de noticia</p>
          <button style="background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;" onclick="window.location.href = 'http://localhost:3000/admin.html';">Volver</button>
        </div>
      `);
    }

    // Verificar si hay más de 5 noticias para esta empresa y eliminar la más antigua si es necesario
    const sqlCount = 'SELECT COUNT(*) AS total FROM Noticia WHERE idEmpresa = ?';
    connection.query(sqlCount, [datosFormulario.empresa], (err, resultCount) => {
      if (err) {
        console.error('Error al contar las noticias de la empresa: ' + err.stack);
        return res.status(500).json({ error: 'Error al contar las noticias de la empresa' });
      }
      
      const totalNoticias = resultCount[0].total;

      if (totalNoticias > 5) {
        // Obtener la ID de la noticia más antigua para esta empresa
        const sqlOldestNewsId = 'SELECT id FROM Noticia WHERE idEmpresa = ? ORDER BY FechaPublicacion ASC LIMIT 1';
        connection.query(sqlOldestNewsId, [datosFormulario.empresa], (err, resultOldest) => {
          if (err) {
            console.error('Error al obtener la noticia más antigua: ' + err.stack);
            return res.status(500).json({ error: 'Error al obtener la noticia más antigua' });
          }

            // Envía una respuesta de éxito después de eliminar la noticia más antigua
            res.send(`
              <div style="text-align: center; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
                <p style="font-family: Arial, sans-serif; color: green;">¡Formulario de noticia enviado correctamente!</p>
                <button style="background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;" onclick="window.location.href = 'http://localhost:3000/admin.html';">Volver</button>
              </div>
            `);
        });
      } else {
        console.log('Datos insertados correctamente en la base de datos');
        // Envía una respuesta de éxito si no se eliminó ninguna noticia
        res.send(`
          <div style="text-align: center; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
            <p style="font-family: Arial, sans-serif; color: green;">¡Formulario de noticia enviado correctamente!</p>
            <button style="background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;" onclick="window.location.href = 'http://localhost:3000/admin.html';">Volver</button>
          </div>
        `);
      }
    });
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
  const sql = 'SELECT Denominacion, QuienesSomos, Telefono, HorarioAtencion, Latitud, Longitud FROM Empresa WHERE id = ?';
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
      telefono: empresaInfo.Telefono,
      horarioAtencion: empresaInfo.HorarioAtencion,
      latitud: empresaInfo.Latitud,
      longitud: empresaInfo.Longitud
    });
  });
});
app.get('/empresas/:id/noticias', (req, res) => {
  const idEmpresa = req.params.id;

  // Consultar la base de datos para obtener las noticias de la empresa por su ID en orden descendente
  const sql = 'SELECT TituloNoticia, ResumenNoticia, ImagenNoticia, id FROM Noticia WHERE idEmpresa = ? AND Publicada = 1 ORDER BY FechaPublicacion DESC';
  connection.query(sql, [idEmpresa], (err, results) => {
    if (err) {
      console.error('Error al obtener noticias de la empresa: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener noticias de la empresa' });
    }
    res.json(results);
  });
});
app.get('/empresas/:idEmpresa/noticias/:idNoticia', (req, res) => {
  const idEmpresa = req.params.idEmpresa;
  const idNoticia = req.params.idNoticia;

  // Consultar la base de datos para obtener la noticia específica por su ID y el ID de la empresa
  const sql = 'SELECT e.Denominacion AS DenominacionEmpresa, n.TituloNoticia, n.ResumenNoticia, n.ImagenNoticia, n.ContenidoHTML, n.FechaPublicacion FROM Noticia n INNER JOIN Empresa e ON n.idEmpresa = e.id WHERE n.idEmpresa = ? AND n.id = ?';
  connection.query(sql, [idEmpresa, idNoticia], (err, result) => {
    if (err) {
      console.error('Error al obtener la información de la noticia: ' + err.stack);
      return res.status(500).json({ error: 'Error al obtener la información de la noticia' });
    }
    if (result.length === 0) {
      return res.status(404).json({ error: 'Noticia no encontrada' });
    }
    // El resultado es un array, debes acceder al primer elemento para obtener los datos
    const noticiaInfo = result[0];
    res.json({ 
      denominacionEmpresa: noticiaInfo.DenominacionEmpresa,
      titulo: noticiaInfo.TituloNoticia, 
      resumen: noticiaInfo.ResumenNoticia,
      imagen: noticiaInfo.ImagenNoticia,
      contenidoHTML: noticiaInfo.ContenidoHTML,
      fechaPublicacion: noticiaInfo.FechaPublicacion
    });
  });
});





// Servir archivos estáticos desde el directorio 'public'
app.use(express.static('public'));

// Iniciar el servidor
const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Servidor Express en funcionamiento en el puerto ${PORT}`);
});
