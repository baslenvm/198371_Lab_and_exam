<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$xmlDoc = new DOMDocument();
$xmlDoc->load("nation.xml");
print 'The root element is '.$xmlDoc->documentElement->tagName; 