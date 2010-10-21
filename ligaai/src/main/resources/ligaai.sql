-- Indice da consulta para do topo que foca no campo top da tabela ligaai
ALTER TABLE `liga_ai`.`ligaai` 
ADD INDEX `topo` USING BTREE (`top` ASC) ;

