CREATE SCHEMA `productos` ;

CREATE TABLE `productos`.`categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(150) NULL,
  PRIMARY KEY (`id_categoria`));

CREATE TABLE `productos`.`producto` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `precio` VARCHAR(45) NULL,
  `stock` VARCHAR(45) NULL,
  `foto` VARCHAR(45) NULL,
  `id_categoria` INT NULL,
  `fec_creacion` DATETIME,
  `fec_actualizacion` DATETIME,
  PRIMARY KEY (`id_producto`),
  INDEX `FK_PRODUCTO_CATEGORIA_idx` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `FK_PRODUCTO_CATEGORIA`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `productos`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
