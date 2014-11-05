<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$xmlfilenmae = 'myProfile.xml';
$doc = new DOMDocument('1.0');
#create element 'profile'
$root = $doc->createElement('profile');
#assign the element 'profile' as the root node of the XML doc
$root = $doc->appendChild($root);
$name = $doc->createElement('name');
$nameValue = $doc->createTextNode('Patcharaphong Batdee');
$name->appendChild($nameValue);
#make the element note 'name' be the child of root node of XML doc
$root->appendChild($name);
#save DOM tree in an XML file
$doc->saveXML();
$doc->save($xmlfilenmae);
echo 'Finish writing file '.$xmlfilenmae;