<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$xmlDoc = new DOMDocument();
$xmlDoc->load("teacher.xml");
$teachers = $xmlDoc->documentElement;
$teacher = $xmlDoc->createElement('teacher');
$classes = $xmlDoc->createElement('classes');
$class = $xmlDoc->createElement('class');
$classValue = $xmlDoc->createTextNode('XML and Web services');
$class->appendChild($classValue);
$name = $xmlDoc->createElement('name');
$nameValue = $xmlDoc->createTextNode('Kanda Runapongsa Saikaew');
$name->appendChild($nameValue);

$classes->appendChild($class);

$teacher->appendChild($name);
$teacher->appendChild($classes);

$teachers->appendChild($teacher);
$xmlDoc->saveXML();
$xmlDoc->save("teaches3.xml");
echo 'Finish writing file '.'<a href="teaches3.xml">teaches3.xml</a>';