<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
if (isset($_GET['class'])) {
    $class = $_GET['class'];
}
if (isset($_GET['class'])) {
    $paper = $_GET['paper'];
}

$status = FALSE;

if ($class != "") {
    seach("class", $class);
}
if ($paper != "") {
    seach("paper", $paper);
}

function seach($type, $value) {
    global $status;
    $xmlDoc = new DOMDocument();
    $xmlDoc->load("teacher.xml");
    $xmlDoc->saveXML();
    $root = $xmlDoc->documentElement;
    followNode($root->childNodes, $type, $value, $status);
    if (!$status) {
        if ($type == "class") {
            global $class;
            echo "Class with keyword '" . $class . "' is not found<br />";
        } else {
            global $paper;
            echo "Paper with keyword '" . $paper . "' is not found<br />";
        }
    }
    $status = FALSE;
}

function followNode($child, $type, $value, &$status, $teacher = "") {
    foreach ($child AS $item) {
        if ($item->nodeName == "name") {
            $teacher = $item->nodeValue;
        } elseif ($item->nodeName == $type) {
            if (strtolower($item->nodeValue) != str_replace(strtolower($value), '', strtolower($item->nodeValue))) {
                $status = true;
                print $teacher . " class" . " = " . $item->nodeValue . "<br />";
            } elseif ($item->hasAttributes()) {
                foreach ($item->attributes as $i) {
                    if ($i->nodeName == 'name')
                        if (strtolower($i->nodeValue) != str_replace(strtolower($value), '', strtolower($i->nodeValue))) {
                            $status = true;
                            print $teacher . " paper" . " = " . $i->nodeValue . "<br />";
                        }
                }
            }
        }
        if ($item->hasChildNodes()) {
            followNode($item->childNodes, $type, $value, $status, $teacher);
        }
    }
}
