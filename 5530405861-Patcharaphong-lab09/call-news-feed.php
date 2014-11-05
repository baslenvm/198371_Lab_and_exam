<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
header("Content-type: text/xml; charset=utf-8");
$url = 'http://www.dailynews.co.th/rss/rss.xml';
$doc = new DOMDocument('1.0');
$doc->load($url);
print $doc->saveXML();