# Merqueo Test -Android

## MAD SCORE
https://madscorecard.withgoogle.com/scorecards/3831957094/#jetpack

# Clean Architecture - MVVM Multi Modulos
En este proyecto se utiliza el patron de presentación MVVM, navigation Components y una arquitectura Clean.



### Librerias
1) JetPack:
 - ViewModel (https://developer.android.com/topic/libraries/architecture/viewmodel)
 - LiveData (https://developer.android.com/topic/libraries/architecture/livedata)
 - Navigation Component (https://developer.android.com/guide/navigation)
 - Room DB with Coroutines (https://developer.android.com/training/data-storage/room)
 - Data Binding (https://developer.android.com/topic/libraries/data-binding)
2) Retrofit2 with coroutines v.2.7.+ (https://square.github.io/retrofit/)
3) Image loading with Coil (https://github.com/coil-kt/coil)
4) DI with Koin (https://github.com/InsertKoinIO/getting-started-koin-core)
5) Clean Architecture
6) Protecto Multi-modules 
12) Kotlin Gradle DSL
13) Material Design
14) Kotlin 1.3.+ with coroutines
15) Kotlin Flow

2) Otras librerias
- Inyeccion de dependencias con Koin (https://insert-koin.io/)
- Carga de imagenes con coil (https://bumptech.github.io/glide/)
- Consumo de servicios Retrofit (https://square.github.io/retrofit/)
- Cliente HTTP OkHtt3 (https://square.github.io/okhttp/)

### Requisitos tecnicos
- Administrador de dependencias GDM con Kotlin Gradle DSL (https://docs.gradle.org/current/dsl/index.html)
- Koltin 1.4
- Multi modules project
- Material design
- Clean Architecture


### Modulos
1) app
Modulo encargado de gestionar toda la interfaz de usuario
2) buildSrc
Modulo encargo de gestionar las dependencias de los modulos
3) core
Modulo encargado de contener los archivos compartidos por todos los modulos
4) usescases
Modulo encargado de las orquestar el flujo los flujos de datos relacionados con las entidades y asi cumplir las reglas de negocio requeridas.
5) domain
Modulo encargado de tener todos los modelos del dominio de negocio.
6) infraestructura
Modulo encargado de interactuar con el hardware del dispositivo.
7) data
Modulo encargaado de gestionar la data interna y externa con la cual se trabaja en la app. 


### Patrones de diseño involucrados
1) Patron Handler
2) Patron repository

### Principios y practicas implementadas
1) Principios Solid - Stupid
2) Principio Yagni
3) Principio KISS
4) Modulos con alta cohesion Bajo acoplamiento
5. Principio de menor conocimiento

### Patron de presentacion MVVM 
- Modelo: Se encarga de obtener, almacenar y proveer los datos además de la lógica de negocios.
- Vista: Muestra la información y reacciona ante la interacción del usuario.
- ViewModel: El el medio que utiliza la vista para acceder a los datos, a la vez, se encarga de notificar la vista cuando los datos han cambiado.


## Ingeniería de Software
Principio de Responsabilidad Única consiste en que cada clase tendrá una única función o propósito con el propósito de mantener juntas las funcionalidades relacionadas lo que reduce el acoplamiento produciendo un código más flexible, mantenible y testeable.

Un buen código es aquel que se escribe teniendo en cuenta las siguientes consideraciones:
- Llevar un control de versiones
- Consultar la documentación en caso de dudas.
- Se sigue la guía de estilo y nombramiento del lenguaje, framework y plataforma.
- Se utilizan patrones de diseño.
- No abusar de las reducciones extremas en la sintáxis ya que reducen la legibilidad.
- Seguir los principios SOLID
- Seguir buenas prácticas de programación.
- En el caso de Android se deben hacer pruebas en varios dispositivos con diferentes tamaños y densidades de pantalla, además de diversas marcas y versiones de OS.
- Seguir buenas prácticas de programación.

# DEEPLINK
Deep Link es un concepto que viene desde las aplicaciones web que cumple el objetivo de vincular un enlace apuntado a un contenido en particular.
por lo general se interpreta como un url el cual puede ser de la siguiente manera:

https:// www.sample.com /profile  ?id=1&name=pavan

https es el esquema.
www.sample.com es el host.
/profile la ruta deacuerdo al contenido que desea buscar .
?id=1&name=pavan los parametros de entrada al contenido a buscar.

El sistema identifica el enlace profundo en la siguiente manera. 
 1. Abra la aplicación preferida del usuario que pueda manejar el URI, si se designa una.(App elegida)
 2. Abra la única aplicación disponible que puede manejar el URI.(Ofrecer aplicaciones posibles para manejar la uri)
 3. Permita que el usuario seleccione una aplicación de un cuadro de diálogo. (El usuario elige una aplicacion que permita abrir)
 
Navigation Component hace parte de la arquitectura Jetpack propuesta por google Ofrece la posibilidad de gestionar deep links y lo realiza de dos maneras diferentes.

 1. Intent Implicito 
 Un vínculo directo implícito es un URI que hace referencia a un destino específico en una app. Cuando se invoca un URI, por ejemplo, cuando un usuario hace clic en un vínculo, Android puede abrir tu app en el destino correspondiente.
Implementacion dentro de Navigation Graph
<deepLink app:uri="https://www.google.com" />

 2. Intent Explicito 
 Un vínculo directo explícito es una instancia única de un vínculo directo que usa un PendingIntent para dirigir a los usuarios a una ubicación específica dentro de tu app.



 



