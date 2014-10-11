<?php
# file
$xml_file = $_GET['xml'];
$xsl_file = $_GET['xsl'];

# create value for transform catalog.xml and catalog.xsl
$doc = new DOMDocument();
$xsl = new XSLTProcessor();

# import tylesheep
$doc->load($xsl_file);
$xsl->importStylesheet($doc);

# echo Stylesheep of XML doc
$doc->load($xml_file);
echo $xsl->transformToXml($doc);
