# Servidor HTTP y base de datos

Este directorio contiene los archivos del proyecto correspondientes al servidor HTTP y la base de datos. 

Para iniciar el servidor HTTP, simplemente tenemos que llamar con Python3 al fichero "Server_TFG.py".

El proyecto está subido con la base de datos SQLite3 inicializada con las tablas creadas y datos de prueba. Se adjuntan también los archivos SQL necesarios para crear y poblar las tablas con estos datos de prueba.   
Con el objetivo de automatizar este proceso, se ha creado un pequeño script .bat encargado de eliminar la base de datos y llamar a los scripts de creación y población en orden.

IMPORTANTE: La IP del servidor HTTP está hardcodeada en todas las clases que se comunican con el servidor HTTP, esta IP es "192.168.1.1:8080"; en caso de que se quiera probar la aplicación, el dispositivo desde el que se lance el servidor HTTP tendrá que tener esta misma IP, o se tendrán que modificar las IPs de los archivos de código.
  
Desarrollado por Eduardo Graván Serrano para el TFG.