# InkluIA - Sistema de Gestión

## Descripción
Sistema de gestión completo desarrollado en Java con NetBeans, utilizando SQL Server como base de datos. Incluye módulos para gestión de usuarios, vacantes, postulaciones, cursos y más.

## Estructura del Proyecto

### Módulos Implementados
1. **Usuarios** - Gestión completa de usuarios del sistema
2. **Tipos de Discapacidad** - Catálogo de tipos de discapacidad
3. **Vacantes** - Gestión de ofertas laborales
4. **Postulaciones** - Manejo de postulaciones a vacantes
5. **Cursos** - Gestión de cursos disponibles
6. **Inscripciones** - Manejo de inscripciones a cursos
7. **Indicadores** - Gestión de métricas y indicadores

### Arquitectura
```
src/main/java/managment/inkluia/
├── Controllers/          # Controladores (ConexionDB, Propiedades)
├── Businesslogic/       # Lógica de negocio (Usuario, Vacante, etc.)
├── Presentations/       # Interfaces gráficas (JFrames)
└── Tests/              # Clases de prueba
```

## Configuración de Base de Datos

### Prerrequisitos
1. SQL Server Express instalado
2. Base de datos `inkludb` creada
3. Usuario `inkluia` con contraseña `hackathon2025`

### Configuración
El archivo `src/main/properties/Inkludb.properties` contiene:
```properties
db.url=jdbc:sqlserver://DESKTOP-G6V2TF4\\SQLEXPRESS:1433;databaseName=inkludb;encrypt=true;trustServerCertificate=true;
db.user=inkluia
db.password=hackathon2025
```

**IMPORTANTE**: Actualiza el servidor en `db.url` con tu instancia de SQL Server.

### Scripts SQL
Los scripts necesarios están en `src/main/SQL/scripts/`:
- `inkludb.sql` - Estructura de tablas
- `procedimientos_[inkludb]_.sql` - Procedimientos almacenados
- `vistas_[inkludb].sql` - Vistas SQL

## Ejecución

### Desde NetBeans
1. Abrir el proyecto en NetBeans
2. Ejecutar la clase principal: `managment.inkluia.InkluIA`

### Desde línea de comandos (si Maven está instalado)
```bash
cd InkluIA
mvn clean compile exec:java
```

### Prueba de Conexión
Para verificar que todo funciona:
```bash
java -cp target/classes managment.inkluia.Tests.TestConexion
```

## Funcionalidades

### Dashboard Principal
- Interfaz centralizada con acceso a todos los módulos
- Navegación intuitiva mediante botones

### Gestión de Usuarios
- CRUD completo con validaciones
- Integración con tipos de discapacidad
- Procedimientos almacenados: `sp_InsertarUsuario`, `sp_ActualizarUsuario`, etc.

### Gestión de Vacantes
- Vista empresas con información detallada
- Uso de vista `vw_VacantesConEmpresas`
- Validación de campos obligatorios

### Gestión de Postulaciones
- Vista detallada con información de usuario y vacante
- Uso de vista `vw_PostulacionesDetalle`
- Control de estados de postulación

### Gestión de Cursos e Inscripciones
- Administración completa de cursos
- Sistema de inscripciones con vista `vw_CursosInscritos`
- Control de fechas y cupos

### Gestión de Indicadores
- Métricas y KPIs del sistema
- Vista `vw_IndicadoresUsuarios` para reportes
- Valores numéricos con validación

## Tecnologías Utilizadas
- **Java 17+**
- **Swing** para interfaces gráficas
- **JDBC** para conectividad con base de datos
- **SQL Server** como motor de base de datos
- **Maven** para gestión de dependencias

## Dependencias
- `com.microsoft.sqlserver:mssql-jdbc:12.8.1.jre11`

## Validaciones Implementadas
- Campos obligatorios en todos los formularios
- Validación de tipos de datos (números, fechas)
- Verificación de existencia antes de eliminaciones
- Manejo de errores con mensajes informativos

## Estructura de Base de Datos

### Tablas Principales
- `Usuarios` - Información de usuarios
- `TiposDiscapacidad` - Catálogo de discapacidades  
- `Empresas` - Información de empresas
- `Vacantes` - Ofertas laborales
- `Postulaciones` - Aplicaciones a vacantes
- `Cursos` - Cursos disponibles
- `CursosUsuarios` - Inscripciones
- `Indicadores` - Métricas del sistema

### Procedimientos Almacenados
Cada tabla tiene procedimientos para:
- Insertar (`sp_Insertar[Tabla]`)
- Actualizar (`sp_Actualizar[Tabla]`)
- Eliminar (`sp_Eliminar[Tabla]`)
- Obtener por ID (`sp_Obtener[Tabla]PorId`)

### Vistas
- `vw_VacantesConEmpresas` - Vacantes con información de empresa
- `vw_PostulacionesDetalle` - Postulaciones con detalles completos
- `vw_CursosInscritos` - Inscripciones con información del curso
- `vw_IndicadoresUsuarios` - Indicadores con información del usuario

## Notas Importantes
1. Verificar que SQL Server esté ejecutándose antes de iniciar la aplicación
2. Asegurarse de que todos los scripts SQL hayan sido ejecutados
3. Actualizar la configuración de conexión según tu entorno
4. Los IDs de usuario en algunos métodos están hardcodeados para pruebas

## Mantenimiento
- Las clases están organizadas por funcionalidad
- Código simple y directo, fácil de mantener
- Separación clara entre lógica de negocio y presentación
- Uso consistente de patrones CRUD
