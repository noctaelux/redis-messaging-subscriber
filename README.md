# Spring Boot - Redis Messaging Consumer

## Requerimientos
- Java v11+
- Maven v3.8.3+
- Docker v20.10.21+ (Opcional)
- RedisInsight v2+ (Opcional) ([App escritorio](https://redis.com/es/redis-enterprise/redisinsight/))
- Redis v7.0.5+ ([Docker Container](https://hub.docker.com/_/redis))

## Instalación / Ejecución
Se debe instalar o tener algún servidor de Redis en ejecución, en la clase `RedisConfig` se puede modificar para ajustar 
el canal por el cual la aplicación observará los mensajes.

### Redis
Si ocupamos Redis dentro de un contenedor de Docker, ejecutamos el siguiente comando:
```
docker run -d --name=redis -p 6379:6379 redis
```
### Spring boot
Para esta prueba de concepto, se ocupa la librería data-redis de spring boot, la cual proporcionará los componentes
necesarios para trabajar con Redis, por lo que agregaremos las siguientes líneas dentro del archivo `pom.xml`:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
Para levantar este proyecto de Spring Boot, vamos a la carpeta del proyecto y por medio de Maven:
```
$ mvn spring-boot:run
```

## Descripción
Esta prueba de concepto recupera mensajes de tipo JSON desde un canal arbitrario `stream:cliente` de Redis, el cual se
puede ver en el archivo de configuraciones `RedisConfig` donde además se pueden ver otras configuraciones de Redis.

El punto de partida en este PoC se encuentra en la clase `RedisSubscriber`, la cual está configurada como un componente
genérico de Spring Boot, la implementación de `MessageListener` permitirá que el método `onMessage` pueda ser sobreescrito
y recuperar de la cola los mensajes del canal indicado.

Dentro de la definición del método `onMessage` se llama al servicio `ClientPrinter` que contiene la lógica de negocio
suficiente para procesar el mensaje proveniente del queue. Dentro del método se puede notar que se llama a otra interfaz 
`UnmarshallMessage` que contiene el método `fromJson()` que desenvolverá el mensaje y lo convertirá al objeto de java 
`Cliente`, posteriormente, se mostrará en pantalla este objeto.

Adicionalmente, se ocupa la librería de Lombok para facilitar la lectura de
las clases y evitar boilerplate de código (constructores, getters, setters, patrón builder). Para mayor información sobre
el funcionamiento de esta librería: https://www.baeldung.com/intro-to-project-lombok 

Para agregar mensajes y visualizar logs sobre los mensajes que transitan sobre el queue, se sugiere instalar opcionalmente
la herramienta RedisInsight.

### Fuentes
- https://spring.io/guides/gs/messaging-redis/
- https://www.baeldung.com/spring-data-redis-pub-sub