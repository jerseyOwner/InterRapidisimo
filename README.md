# InterRapidisimo

Esta aplicación permite consultar información de animes haciendo uso del API https://apitesting.interrapidisimo.co/FtAppAgencias012/apiControllerPruebas/api/SincronizadorDatos/ObtenerEsquema/true 

Fue construida con Kotlin, arquitectura MVVM, inyección de dependencias con Hilt y llamadas de red con Retrofit. A continuación de presenta
información de los features de la App.

## Pantalla Home

En esta pantalla se presentan dos botones, el primero hará la consulta de los datos a la API, 
El segundo dirige a la pantalla con las tablas que hayan sido creadas por el usuario. 

<img src="./assest/images/home.png" width=270 height=555>

Si se presenta un error de conexión o accesos a los recursos, se presentará al usuario un error genérico.

<img src="./assets/images/internet.png" width=270 height=555>

Cuando la respuesta de la API es correcta, se presenta el mensaje al usuario

<img src="./assets/images/infoRecibida.png" width=270 height=555>

## Pantalla Lista de tablas

En esta pantalla se presentan las tablas creadas hasta el momento, para el caso es el que no hay tablas creadas:

<img src="./assets/images/sinTablas.png" width=270 height=555>

Y para el caso en el que hay tablas y se quiere filtrar por nombre:

<img src="./assets/images/buscador.png" width=270 height=555>
