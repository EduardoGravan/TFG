# Aplicación de Android para fichar con NFC

Este directorio contiene un proyecto del IDE IntelliJ Idea en su versión 2020.2. 

Para comprobar su funcionamiento, simplemente tenemos que importarlo, conectar nuestro dispositivo móvil con soporte para NFC a nuestro PC y compilar y ejecutar la aplicación en este dispositivo.  
Es necesario que el servidor HTTP esté corriendo de fondo para poder acceder a la aplicación, ya que la primera pantalla es un login.  

IMPORTANTE: La IP del servidor HTTP está hardcodeada en todas las clases que se comunican con el servidor HTTP, esta IP es "192.168.1.1:8080"; en caso de que se quiera probar la aplicación, el dispositivo desde el que se lance el servidor HTTP tendrá que tener esta misma IP, o se tendrán que modificar las IPs de los archivos de código.
  
Desarrollado por Eduardo Graván Serrano para el TFG.