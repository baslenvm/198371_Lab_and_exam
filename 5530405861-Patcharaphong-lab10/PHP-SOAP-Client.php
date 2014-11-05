<?php
error_reporting(-1);
ini_set('display_errors', 'On');

header("Content-type:text/xml");
$wsdl = 'http://www.webservicex.net/globalweather.asmx?WSDL';
$function = 'GetWeather';
$pram = array('CityName' => 'Khon Kaen','CountryName' => 'Thailand');
$soap = new SoapClient($wsdl);
$response = $soap->__soapCall($function, array($pram));
echo $response->GetWeatherResult;