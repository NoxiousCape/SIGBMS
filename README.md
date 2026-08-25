<div align="center">

# 📚 SIGBMS

### Sistema Integral de Gestión para la Biblioteca Municipal de Sogamoso

Plataforma web para la administración integral de los procesos bibliotecarios:
catálogo, préstamos, devoluciones, usuarios y reportes — todo en un solo lugar.

[![Status](https://img.shields.io/badge/status-Alpha-yellow?style=for-the-badge)](#-estado-del-proyecto)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](#-stack-tecnológico)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](#-stack-tecnológico)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](#-stack-tecnológico)
[![License](https://img.shields.io/badge/license-Académico-blue?style=for-the-badge)](#-licencia)

[Características](#-características) •
[Arquitectura](#-arquitectura) •
[Instalación](#-instalación) •
[Uso](#-uso) •
[Roadmap](#-roadmap) •
[Equipo](#-equipo)

</div>

---

## 🎯 Acerca del proyecto

**SIGBMS** es una solución web desarrollada para la **Biblioteca Municipal de
Sogamoso** con el objetivo de modernizar y centralizar la gestión de sus
procesos internos y la atención al público. El sistema cubre desde la
administración del catálogo bibliográfico hasta la consulta, el préstamo y la
devolución de libros, ofreciendo además un portal público y un panel
administrativo con estadísticas.

Este proyecto fue desarrollado como parte del curso de **Ingeniería de Software
II** de la **Universidad Pedagógica y Tecnológica de Colombia (UPTC)**, bajo la
dirección del **Ing. Edmundo Junco**.

### ¿Por qué existe?

- ❌ Procesos manuales con planillas y hojas de cálculo.
- ❌ Catálogo difícil de consultar para los usuarios.
- ❌ Sin trazabilidad de préstamos ni estadísticas confiables.
- ✅ Centralizar la información en una sola plataforma.
- ✅ Ofrecer un portal autoservicio para los lectores.
- ✅ Generar reportes útiles para la toma de decisiones.

---

## ✨ Características

### 👥 Para usuarios / lectores
- 🔍 Búsqueda y consulta del catálogo bibliográfico en línea.
- 📝 Registro y autenticación de usuarios.
- 📖 Solicitud de préstamos y renovaciones.
- 📬 Gestión de peticiones y solicitudes.
- 🖼️ Galería de novedades y eventos.

### 🛠️ Para personal bibliotecario
- 📚 Gestión del catálogo (altas, bajas, ediciones).
- 🔄 Control de préstamos y devoluciones.
- 👤 Administración de cuentas de usuarios.
- 📊 Visualización de indicadores y estadísticas.

### 🧑‍💼 Para administradores
- 🗂️ Panel de control con métricas del sistema.
- 👥 Gestión de personal y roles.
- 📈 Gráficos y reportes operativos (`chart_Admin.html`).
- 🔐 Configuración de parámetros del sistema.

---

## 🏗️ Arquitectura

SIGBMS sigue una arquitectura **cliente-servidor** clásica, con un frontend
estático desacoplado que consume un API REST.

```
┌──────────────────────┐        HTTPS / JSON        ┌──────────────────────────┐
│  Frontend (cliente)  │  ───────────────────────▶  │   Backend (API REST)     │
│  HTML5 / CSS3 / JS   │  ◀───────────────────────  │   Spring Boot 3.1.4      │
│  páginas estáticas   │                            │   Java 21                │
└──────────────────────┘                            └──────────┬───────────────┘
                                                                 │ JPA / Hibernate
                                                                 ▼
                                                      ┌──────────────────────┐
                                                      │     MySQL 8.0        │
                                                      │     (persistencia)   │
                                                      └──────────────────────┘
```

**Capas del backend (Spring Boot):**

- **Controller** — expone los endpoints REST.
- **Service** — lógica de negocio (préstamos, validaciones, etc.).
- **Repository** — acceso a datos con Spring Data JPA.
- **Model** — entidades del dominio (Libro, Usuario, Préstamo, etc.).

---

## 🧰 Stack tecnológico

### Frontend
| Tecnología       | Uso                                      |
|------------------|------------------------------------------|
| HTML5            | Estructura de las páginas                |
| CSS3             | Estilos y diseño responsivo              |
| JavaScript (ES6) | Lógica de cliente, consumo del API       |
| jQuery + UI      | Interacciones y datepickers              |
| Gulp 3.9         | Automatización de build del frontend     |

### Backend
| Tecnología             | Versión | Uso                              |
|------------------------|---------|----------------------------------|
| Java                   | 21      | Lenguaje principal               |
| Spring Boot            | 3.1.4   | Framework del servicio REST      |
| Spring Data JPA        | 3.1.4   | Persistencia                     |
| Spring Security Crypto | 5.5.0   | Hashing de credenciales          |
| Hibernate (vía JPA)    | managed | ORM                              |
| MySQL Connector        | 8.0.27  | Driver de la base de datos       |
| Lombok                 | managed | Reducción de boilerplate         |
| Maven                  | wrapper | Gestión de dependencias y build   |

### Base de datos
- **MySQL 8.0** — modelo relacional para libros, usuarios, préstamos, etc.

### Despliegue
- **Azure App Service** — `sigbsdeployment.azurewebsites.net`

---

## 🚀 Instalación

### Prerrequisitos

Asegúrate de tener instalado:

- [Git](https://git-scm.com/)
- [Java JDK 21](https://adoptium.net/) o superior
- [Maven 3.8+](https://maven.apache.org/) (o usa el wrapper `./mvnw` incluido)
- [Node.js 16+](https://nodejs.org/) y npm (para el build del frontend con Gulp)
- [MySQL 8.0+](https://dev.mysql.com/downloads/) (o Docker)

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/NoxiousCape/SIGBMS.git
cd SIGBMS
```

### 2️⃣ Configurar la base de datos

Crea la base de datos y un usuario dedicado:

```sql
CREATE DATABASE sigbms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'sigbms_user'@'localhost' IDENTIFIED BY 'tu_password_segura';
GRANT ALL PRIVILEGES ON sigbms.* TO 'sigbms_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3️⃣ Configurar el backend

Edita el archivo de configuración del backend (por ejemplo
`BackModificado/src/main/resources/application.properties` o
`application.yml`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sigbms?useSSL=false&serverTimezone=UTC
spring.datasource.username=sigbms_user
spring.datasource.password=tu_password_segura
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 🔒 **Importante:** no subas tus credenciales al repositorio. Usa variables de
> entorno o un archivo ignorado por `.gitignore`.

### 4️⃣ Compilar y arrancar el backend

```bash
cd BackModificado
./mvnw clean install
./mvnw spring-boot:run
```

El API quedará escuchando en `http://localhost:8080` por defecto.

### 5️⃣ Levantar el frontend

El frontend son páginas estáticas. Puedes servirlas de dos formas:

**Opción A — abrir directamente:** abre `index.html` en tu navegador.

**Opción B — servidor local (recomendado):**

```bash
# Desde la raíz del proyecto
npx http-server . -p 5500
# o, si prefieres Gulp:
npx gulp
```

Luego entra a `http://localhost:5500/index.html`.

> ⚠️ Si el frontend apunta a un backend remoto (por ejemplo
> `https://sigbsdeployment.azurewebsites.net`), actualiza la URL base en `app.js`
> y en los demás archivos `.html` del directorio raíz.

---

## 💻 Uso

Una vez levantado el entorno:

1. Abre `index.html` en el navegador.
2. Regístrate como usuario o entra con una cuenta existente.
3. Explora el catálogo en `Libros.html` y realiza búsquedas.
4. Los usuarios administradores pueden acceder a `Admin.html` para gestionar
   el sistema.
5. El personal bibliotecario dispone de `Personal.html` con su flujo de
   trabajo.
6. Los indicadores se visualizan en `chart_Admin.html`.

### Páginas disponibles

| Página                | Descripción                                     |
|-----------------------|-------------------------------------------------|
| `index.html`          | Landing principal                               |
| `SignIn.html`         | Inicio de sesión y registro                     |
| `Users.html`          | Portal del usuario                              |
| `Libros.html`         | Catálogo y búsqueda de libros                   |
| `Personal.html`       | Panel de personal bibliotecario                 |
| `Personal_Users.html` | Gestión de usuarios para personal               |
| `Admin.html`          | Panel de administración                         |
| `Admin_Users.html`    | Gestión de usuarios para administradores        |
| `chart_Admin.html`    | Gráficos y reportes                             |
| `gallery.html`        | Galería pública                                 |
| `gallery_personal.html` | Galería interna                               |

---

## 📁 Estructura del proyecto

```
SIGBMS/
├── 📄 index.html                 # Landing principal
├── 📄 SignIn.html                # Login / registro
├── 📄 Users.html                 # Portal de usuario
├── 📄 Libros.html                # Catálogo
├── 📄 Admin.html                 # Panel admin
├── 📄 Admin_Users.html
├── 📄 Personal.html              # Panel de personal
├── 📄 Personal_Users.html
├── 📄 chart_Admin.html           # Reportes y gráficos
├── 📄 gallery.html               # Galería pública
├── 📄 gallery_personal.html
├── 📄 app.js                     # Lógica de auth y registro
├── 📄 package.json               # Dependencias frontend (Gulp)
│
├── 📂 BackModificado/            # 🔧 Backend Spring Boot
│   ├── pom.xml
│   ├── mvnw / mvnw.cmd
│   └── src/
│
├── 📂 css/                       # 🎨 Estilos
├── 📂 js/                        # ⚙️ Scripts del cliente
├── 📂 img/                       # 🖼️ Recursos gráficos
├── 📂 bower_components/          # Dependencias frontend legacy
├── 📂 node_modules/              # Dependencias npm
├── 📂 misc/                      # Archivos varios
└── 📂 php-version/               # Specs legacy
```

---

## 🗺️ Roadmap

- [x] Catálogo y búsqueda de libros
- [x] Autenticación y registro de usuarios
- [x] Panel administrativo con estadísticas
- [ ] Sistema de reservas en línea
- [ ] Notificaciones por correo (préstamos por vencer)
- [ ] API de códigos ISBN para carga masiva
- [ ] Aplicación móvil complementaria
- [ ] Internacionalización (i18n) ES / EN
- [ ] Módulo de informes exportables (PDF / Excel)

Consulta la lista de [issues](../../issues) para reportar bugs o proponer
nuevas funcionalidades.

---

## 🤝 Contribución

Las contribuciones son bienvenidas. Para mantener orden en el proyecto:

1. Haz **fork** del repositorio.
2. Crea una rama descriptiva:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza commits claros y atómicos:
   ```bash
   git commit -m "feat: agregar búsqueda avanzada por autor"
   ```
4. Asegúrate de que el proyecto compile (`./mvnw clean install` y `npm run build`).
5. Push a tu fork y abre un **Pull Request** describiendo los cambios.

### Convención de commits

Usamos [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` nueva funcionalidad
- `fix:` corrección de bug
- `docs:` cambios en documentación
- `style:` formato sin cambio de lógica
- `refactor:` cambio interno sin nueva funcionalidad
- `test:` agregar o corregir pruebas

---

## 🐛 Reporte de bugs

Si encuentras un problema, abre un [issue](../../issues) con:

- Descripción clara del bug.
- Pasos para reproducirlo.
- Comportamiento esperado vs. observado.
- Capturas de pantalla (si aplica).
- Entorno (SO, navegador, versión de Java/Node).

---

## 📜 Licencia

Este proyecto es de carácter **académico** y su uso está restringido a la
**Universidad Pedagógica y Tecnológica de Colombia (UPTC)** y a los
desarrolladores mencionados. Todos los derechos reservados.

Para cualquier otro uso, contactar a los autores.

---

## 👥 Equipo

<table>
  <tr>
    <td align="center"><b>Laura Tamayo</b><br/>Desarrolladora</td>
    <td align="center"><b>Jorge Gamboa</b><br/>Desarrollador</td>
    <td align="center"><b>Daniel Páez</b><br/>Desarrollador</td>
  </tr>
</table>

**Docente:** Ing. Edmundo Junco
**Curso:** Ingeniería de Software II
**Universidad:** Universidad Pedagógica y Tecnológica de Colombia (UPTC)

---

## 🙏 Agradecimientos

- 📖 **Biblioteca Municipal de Sogamoso** — por la apertura y colaboración
  durante el levantamiento de requerimientos.
- 🎓 **Universidad Pedagógica y Tecnológica de Colombia (UPTC)** — por el
  espacio académico y el acompañamiento docente.
- ☕ A todos los que probaron el sistema y aportaron su feedback.

---

<div align="center">

Hecho con ❤️ en Sogamoso, Boyacá — Colombia 🇨🇴

</div>
