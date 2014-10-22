<?php

error_reporting(-1);
ini_set('display_errors', 'On');
header("Content-type: text/html; charset=utf-8");
$service_url = 'https://www.blognone.com/atom.xml';
$curl = curl_init($service_url);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
//curl_setopt($curl, CURLOPT_POST, true);
$curl_response = curl_exec($curl);
curl_close($curl);
$parser = simplexml_load_string($curl_response);
$doc = new DOMDocument('1.0');
$doc->loadXML($curl_response);
$child = $doc->childNodes;
//$doc->firstChild->nextSibling->nodeValue;


function followNode($child) {
    foreach ($child AS $item) {
        if ($item->nodeName == 'item') {
            echo '<li>';
            echo '<a href="'.$item->firstChild->nextSibling->nextSibling->nextSibling->nodeValue.'">';
            echo $item->firstChild->nextSibling->nodeValue;
            echo '</li>';
            
        }
        if ($item->hasChildNodes()) {
                followNode($item->childNodes);
            }
    }
}

?>
<html>
    <body>
        <h1>Feed items from Blognone</h1>
        <ll>
            <?php followNode($child); ?>
        </ll>
    </body>
</html>