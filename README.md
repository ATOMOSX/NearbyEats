# NearbyEats

NearbyEats es una plataforma web diseñada para fomentar el comercio y la gastronomía regional, priorizando el apoyo a los pequeños comerciantes. Permite a los usuarios buscar, explorar y compartir lugares de interés como restaurantes, cafeterías, museos y hoteles, cercanos a su ubicación.


## Tecnologías Utilizadas

- **Backend**: Spring Boot, Gradle, MongoDB
- **Frontend**: Angular (TypeScript), Npm, Bootstrap 5, FontAwesome

## Funcionalidades Principales

### Moderador

- Autorizar o rechazar lugares.
- Registro de actividades de moderación.
- Lista de lugares pendientes y autorizados.

### Usuario

- Registro y autenticación.
- Recuperación de contraseña por email.
- Explorar y visualizar lugares en un mapa.
- Crear, editar y eliminar lugares.
- Comentar y calificar lugares.
- Guardar lugares como favoritos.
- Búsqueda por nombre, tipo y distancia.
- Solicitar ruta entre ubicaciones con distancia y tiempo de viaje.
- Ver lista de lugares creados y responder comentarios.
- Eliminar cuenta Permanentes.

## Consideraciones Importantes

- Utilización de Mapbox para la integración de mapas.
- Notificaciones por correo electrónico para acciones relevantes.
- Gestión de imágenes a través de un servicio externo (Cloudinary, Firebase, etc.).
- Código fuente alojado en GitHub para colaboración del equipo de desarrollo.
- Moderadores preconfigurados en la base de datos mongodb.

## Instalación y Uso

### Prerrequisitos

- Java, Gradle y MongoDB para el backend.
- TypeScript y Angular CLI para el frontend.

### Instalación

# Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/nearbyeats.git
```

# Configurar y ejecutar el backend:

```bash
cd nearbyeats/backend
./gradlew bootRun
```

# Configurar y ejecutar el frontend:
```bash
cd nearbyeats/frontend
npm install
ng serve
```

# Contribución

## Clona el repositorio.

```bash
Crea una rama (`git checkout -b feature/nueva-funcionalidad`).
```

```bash
Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`).
```

```bash
Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
Crea un nuevo Pull Request.
```
¡Gracias por contribuir al proyecto NearbyEats!
