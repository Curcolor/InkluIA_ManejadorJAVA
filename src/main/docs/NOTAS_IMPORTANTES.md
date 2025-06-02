# Notas Importantes para el Uso de InkluIA

## Configuración de SQL Server para Conexión desde Java

### Habilitar Protocolo TCP/IP
Para que una aplicación Java pueda conectarse correctamente a SQL Server, es necesario habilitar el protocolo TCP/IP:

1. Abrir **SQL Server Configuration Manager**
   - Puede encontrarse en: `C:\Windows\SysWOW64\SQLServerManager15.msc` (el número puede variar según la versión)
   - También puede buscarlo en el menú inicio

2. En el panel izquierdo, navegar a:
   - **Configuración de red de SQL Server** > **Protocolos para SQLEXPRESS** (o el nombre de tu instancia)

3. En el panel derecho, asegurarse que **TCP/IP** esté **Habilitado**:
   - Si muestra "Deshabilitado", hacer clic derecho y seleccionar "Habilitar"
   - Confirmar cualquier mensaje de advertencia

4. Hacer clic derecho en **TCP/IP** y seleccionar **Propiedades**:
   - En la pestaña **Direcciones IP**, asegurarse que el **Puerto TCP** esté configurado (normalmente 1433)
   - Verificar que "Habilitado" esté en "Sí" para las direcciones IP activas

5. **Reiniciar el servicio de SQL Server** para que los cambios surtan efecto:
   - En el panel izquierdo, seleccionar "Servicios de SQL Server"
   - Clic derecho en "SQL Server (SQLEXPRESS)" o tu instancia
   - Seleccionar "Reiniciar"

### Errores Comunes de Conexión

Si encuentras errores al conectar la aplicación Java con SQL Server, verifica:

1. **Error de conexión TCP/IP**: Confirma que el protocolo TCP/IP esté habilitado como se describe arriba.

2. **Error de autenticación**: Verifica que el usuario y contraseña sean correctos en el archivo `Inkludb.properties`.

3. **Error 'Named Pipes Provider'**: Asegúrate de que el protocolo TCP/IP esté habilitado, no solo Named Pipes.

4. **Error de certificado SSL**: Se ha incluido `trustServerCertificate=true` en la URL de conexión para evitar este problema.

5. **Error 'SQL Server Browser'**: Si usas una instancia con nombre (no el puerto predeterminado), el servicio SQL Server Browser debe estar en ejecución.

## Configuración de Dependencias en NetBeans

Para agregar la dependencia de SQL Server JDBC en tu proyecto NetBeans:

### Opción 1: Usando la Interfaz Gráfica

1. Clic derecho en el proyecto > "Propiedades"
2. Seleccionar "Libraries" en el panel izquierdo
3. Seleccionar la pestaña "Dependencies"
4. Hacer clic en "Add Dependency..."
5. Completar los campos:
   - Group ID: `com.microsoft.sqlserver`
   - Artifact ID: `mssql-jdbc`
   - Version: `12.8.1.jre11`

### Opción 2: Editando el archivo pom.xml

Añadir el siguiente fragmento dentro de la sección `<dependencies>` del archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>12.8.1.jre11</version>
</dependency>
```

### Verificación de Dependencias

Para verificar que la dependencia se ha añadido correctamente:

1. Clic derecho en el proyecto > "Dependencies"
2. Deberías ver "mssql-jdbc-12.8.1.jre11.jar" en la lista de dependencias
3. Si no aparece, intenta realizar una limpieza y reconstrucción del proyecto:
   - Clic derecho en el proyecto > "Clean and Build"

## Configuración del Sistema

### Requisitos mínimos

- Java 17 o superior
- SQL Server 2019 Express o superior
- 4GB de RAM
- Pantalla con resolución mínima de 1280x720
