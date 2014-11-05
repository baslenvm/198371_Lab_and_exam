<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
# file
$xml_file ='catalog.xml';
$xsl_file ='catalog.xsl';

# create value for transform catalog.xml and catalog.xsl
$doc = new DOMDocument();
$xsl = new XSLTProcessor();

# import tylesheep
$doc->load($xsl_file);
$xsl->importStylesheet($doc);

# echo Stylesheep of XML doc
$doc->load($xml_file);
echo $xsl->transformToXml($doc);