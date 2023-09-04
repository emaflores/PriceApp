# Aplicación Spring Boot para Inditex

Esta es una aplicación Spring Boot desarrollada por Emanuel Flores como parte de un desafío propuesto por la empresa Inditex. La aplicación se centra en la gestión de precios de productos y brinda la funcionalidad de buscar el precio más adecuado para un producto y una marca en una fecha determinada. A continuación, se proporciona una descripción general de la aplicación y su estructura.

## Requisitos

La aplicación se desarrolló utilizando las siguientes tecnologías y dependencias:

- Spring Boot 3.1.3
- Java 17
- Base de datos H2
- Dependencia Swagger para documentación API
- Dependencia Lombok para reducir el código boilerplate

## Herramienta de Construcción

Este proyecto utiliza Maven como herramienta de construcción. Se pueden utilizar los comandos de Maven para compilar y gestionar las dependencias del proyecto. Maven debe estar instalado en el sistema antes de continuar.

## Estructura del Proyecto

La aplicación está organizada en varios componentes y capas:

- **Controlador (`PriceController`):** Expone los puntos finales REST para acceder a los precios de los productos y maneja las solicitudes HTTP.

- **DTO (`PriceDTO`):** Define un objeto de transferencia de datos que representa la información de precio en un formato amigable para el cliente.

- **Entidad (`Price`):** Representa una entidad de precio que se almacena en la base de datos H2.

- **Excepciones (`GlobalExceptionHandler`):** Maneja excepciones específicas y proporciona respuestas HTTP adecuadas.

- **Repositorio (`PriceRepository`):** Define las operaciones de acceso a la base de datos para la entidad de precio.

- **Servicios (`PriceMapper` y `PriceService`):** Proporciona la lógica de negocio para mapear entidades de precio y encontrar el precio más adecuado.

- **Validación (`Validation`):** Contiene funciones para validar la integridad de los identificadores de productos y marcas.

- **Pruebas (`PriceControllerTests`):** Incluye pruebas unitarias y de integración para garantizar el correcto funcionamiento de los controladores.

## Uso de la Aplicación

La aplicación expone un punto final REST a través de la ruta `/getPrice`. Los parámetros requeridos son:

- `date`: La fecha en formato `yyyy-MM-dd-HH.mm.ss`.
- `productId`: El identificador del producto (debe ser un número entero).
- `brandId`: El identificador de la marca (debe ser un número entero).

La aplicación realizará las siguientes acciones:

1. Validará la integridad de los parámetros de entrada.
2. Buscará en la base de datos el precio más adecuado para el producto y la marca en la fecha proporcionada.
3. Devolverá el precio encontrado en formato JSON.

Si no se encuentra ningún precio, la aplicación devolverá un error 404 "No se encontraron precios para los parámetros proporcionados".

## Calidad del Código

Se ha prestado especial atención a la calidad del código en esta aplicación. Se siguen las mejores prácticas de programación y se utilizan patrones de diseño para mantener el código limpio y modular.

## Documentación de la API

Se ha integrado Swagger para documentar la API de la aplicación. Puedes acceder a la documentación y probar los puntos finales visitando la siguiente URL después de iniciar la aplicación en el ambiente de desarrollo:

```
http://localhost:8080/swagger-ui/index.html
```

## Pruebas

Para ejecutar las pruebas incluidas en el proyecto, primero hay que detener el proyecto si se esta ejecutando para que la base de datos este disponible, luego utilizar el siguiente comando Maven:

```
mvn test
```

Esto ejecutará todas las pruebas definidas en la clase `PriceControllerTests` para garantizar el correcto funcionamiento de la aplicación.

## Configuración de la Base de Datos

La base de datos H2 se configura automáticamente al ejecutar la aplicación Spring Boot. Además, la base de datos H2 ya incluye datos iniciales que se utilizan en la tabla `price`. Los datos iniciales de la tabla `price` son los siguientes:

| BRAND_ID | START_DATE           | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|----------------------|----------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

Estos datos iniciales son utilizados por la aplicación para proporcionar resultados en las pruebas y demostraciones de la API.

Estos datos son fijos y no se modifican durante la ejecución de la aplicación. Para agregar, modificar o eliminar datos, puedes hacerlo directamente en la base de datos H2 utilizando la consola de H2 o mediante instrucciones SQL. La consola de H2 está habilitada y está disponible en la ruta `http://localhost:8080/h2-console/` cuando la aplicación se está ejecutando en el entorno de desarrollo. Para acceder a la consola de H2, debes proporcionar la siguiente información: 

* Driver Class: `org.h2.Driver`
* JDBC URL: `jdbc:h2:file:./src/main/resources/h2data/db`
* User Name: `sa`
* Password: (dejar en blanco)

## Ejecución

Para ejecutar la aplicación utilizando Maven, sigue estos pasos:

1. Tener Java 17 instalado en el sistema.
2. Clonar el repositorio de la aplicación.
3. Abrir el proyecto en el entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA).
4. Utilizar el siguiente comando Maven en la raíz del proyecto para compilar y ejecutar la aplicación:

```
mvn spring-boot:run
```

La aplicación se iniciará y estará disponible para ser probada en, por ejemplo, Postman en: `http://localhost:8080/getPrice?date=2020-06-14-10.00.00&productId=35455&brandId=1`.

## Conclusiones

Esta aplicación Spring Boot cumple con los requisitos del desafío propuesto por Inditex y se enfoca en la gestión de precios de productos de manera eficiente y robusta. La estructura organizada del proyecto, la calidad del código y las pruebas aseguran su fiabilidad y rendimiento.