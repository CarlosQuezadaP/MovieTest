# Merqueo Test -Amdroid

# MVVM Multi Modulos
En este proyecto se utiliza el patron de presentación MVVM, navigation Components y una arquitectura limpia.

# Modulos
La correspondiente arquitectura fue una arquitectura en capas la cual contiene los siguientes modulos:
* app
contiene la Actividad de punto de entrada para la aplicación. Contiene MainActivity que alberga BottomNavigationView para navegar entre las características
* CORE
contiene código compartido en toda la aplicación, como se aplica Gradle Dependency Management (GDM), centraliza todas las dependencias del proyecto.
* home
contiene las caracteristicas del modelo. en este caso se encarga de mostrar la lista de pelicula y su detalle.
* shopping cart
contiene las caracteristicas del modelo. en este caso se encarga de mostrar la lista de pelicula que se encuentran en el carro de compras.
* Models
Provee los diferentes modelos del proyecto, Dominio, Dtos, Entidades.
* provide
Provee el repositorio de datos local y remoto.
* infraestructura
Obtiene el acceso a los datos remotos y los datos locales. 

![Scheme](images/scheme.png)




# Requisitos tecnicos.

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


### Patron de presentacion MVVM 
- Modelo: Se encarga de obtener, almacenar y proveer los datos además de la lógica de negocios.
- Vista: Muestra la información y reacciona ante la interacción del usuario.
- ViewModel: El el medio que utiliza la vista para acceder a los datos, a la vez, se encarga de notificar la vista cuando los datos han cambiado.

## Responsabilidad de los packages segun modulos
* app - home - shoppingCart
    - Presentation: 

    Encontramos los elementos dirigidos a la parte visual categorizados en diferentes carpetas:
    - Activityes 
    - Fragments
    - Adapters
    - ViewModels
          
    Elementos de dominio como  y el respectivo inyeccion de dependencias.
    - Dominio: 
         - Di = Permite proveer las instancias de la implementaciones realizadas
        - Servicios de dominio = servicios que contienen la comunicacion para la capa de datos establecida en infraestructura 
        
* infraestructura 
     - repository: Se utiliza Repository Pattern para abstraer el acceso a la información, de esta manera se separan responsabilidades y se puede cambiar la fuente (DB, API o ambos) sin afectar la implementación.
         - api: Están las clases encargadas de consumir los recursos del API Rest, utilizamos Retrofit2 para los llamados junto a una clase de utilidad que genera los url para las imágenes.
         - db: Clases para manejar la base de datos Room, encontramos el Data Access Object, un convertidor de tipos para almacenar objetos propios en la DB, además la clase que inicializa la DB.
         - deviceHardware: Obtiene las implementaciones relacionadas con el harware de dispositivo, como capa de red, datos, bluetooth etc...
* Provide
  - DB = Contiene la implementacion del storoge local que implementa el proyecto.
  - DI = Permite proveer las instancias de la implementaciones realizadas.
  - Network = Contiene la implementacion para el acceso a los datos alojados en un repositorio remoto.
* Models
   este paquete se incluyen todos los modelos de datos a utilizar en la app clasificados como:
    - Entities = modelos de datos enfocadas a base de datos.
    - DTOS = Modelos que se utilizan como un objeto de transferencia entre el cliente y el servidor
    - Modelos de dominio = Modelos de dominio de caracter de modelado de negocio
* Core
  - Activityes
  - Fragments
  - Adapters
  - ViewModels


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



 



