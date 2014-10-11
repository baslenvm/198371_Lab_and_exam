<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$xmlDoc = new DOMDocument();
$xmlDoc->load("nation.xml");
$nation = $xmlDoc->documentElement;
$child = $nation->childNodes;
print $nation->nodeName . ' has ' . $child->length . ' children<br />';
followNode($child);


function followNode($child) {
    foreach ($child AS $item) {
        print $item->nodeName . " = " . $item->nodeValue . "<br />";
        if ($item->hasChildNodes()) {
            followNode($item->childNodes);
        }
    }
}
