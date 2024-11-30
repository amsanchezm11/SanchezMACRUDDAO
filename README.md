# 🆕📖🔄🗑️ Operaciones CRUD

## 📄 Descripci&oacute;n del proyecto: 

* Aplicaci&oacute;n web que permite al usuario crear, visualizar, modificar y eliminar usuarios..
* El objetivo de &eacute;ste proyecto es aprender los conceptos b&aacute;sicos de CRUD en MySQL utilizando Java.
* En &eacute;sta versi&oacute;n se aplican los DAO(Data Access Object).

## 🛢️ Script  para crear la tabla `usuarios` en SQL:

```sql
DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(15) COLLATE utf8_spanish_ci NOT NULL,
    apellidos VARCHAR(30) COLLATE utf8_spanish_ci NOT NULL,
    fechaNacimiento DATE NOT NULL,
    genero SET('M', 'H', 'O') NOT NULL DEFAULT 'M',
    username VARCHAR(20) COLLATE utf8_spanish_ci NOT NULL UNIQUE,
    password VARCHAR(100) COLLATE utf8_spanish_ci NOT NULL,
    PRIMARY KEY (id)
);

```

## 🛠️ Tecnolog&iacute;as utilizadas en el proyecto:

[![Java](https://skillicons.dev/icons?i=java&theme=light)](https://github.com/amsanchezm11)
[![MySql](https://skillicons.dev/icons?i=mysql&theme=light)](https://github.com/amsanchezm11)
[![Tomcat](https://simpleskill.icons.workers.dev/svg?i=apachetomcat)](https://github.com/amsanchezm11)

## ⚙️ Especificaciones:
* **Proyecto realizado mediante:** *NetBeans 19*
* **JDK usado:** *JDK 11*
* **Servidor:** *Tomcat*
* **Java EE versi&oacute;n:** *Java EE 7 Web*
* **Proyecto subido mediante:** *GitHub Desktop*

## ℹ️ Informaci&oacute;n del proyecto:
* **Autor:** *Alberto S&aacute;nchez Mac&iacute;as*
* **Nota:** *Sin calificar*
* **Curso:** *DAW 2-B*
* **Asignatura:** *Desarrollo Web en Entorno Servidor*
* **Profesor:** *Jes&uacute;s Garc&iacute;a Garrijo*
* **Instituto:** *IES Albarregas*
* **A&ntilde;o:** *2024/25*
