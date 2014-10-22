<?php

error_reporting(-1);
ini_set('display_errors', 'On');
header("Content-type:text/xml");
$soapUrl = "http://www.pttplc.com/webservice/pttinfo.asmx?WSDL";
$xml_post_string = '<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><CurrentOilPrice xmlns="http://www.pttplc.com/ptt_webservice/"><Language>string</Language></CurrentOilPrice></soap:Body></soap:Envelope>';
$headers = array(
    'Content-type:text/xml;charset="utf-8"',
    'Accept: text/xml',
    'Cache-Control: no-cache',
    'Pragma: no-cache',
    'SOAPAction: http://www.pttplc.com/ptt_webservice/CurrentOilPrice',
    'Content-length: ' . strlen($xml_post_string),
);
$url = $soapUrl;
$ch = curl_init();
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_ANY);
curl_setopt($ch, CURLOPT_TIMEOUT, 10);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $xml_post_string);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
$response = curl_exec($ch);
curl_close($ch);
echo $response;
$response1 = str_replace("<soap:Body>", "", $response);
$response2 = str_replace("</soap:Body>", "", $response1);
$parser = simplexml_load_string($response2);
?>