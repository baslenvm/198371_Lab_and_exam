<?php
#กำหนดการเข้ารหัส html ในแบบ utf-8
header('Content-Type: text/html; charset=utf-8');
#นำข้อมูลของการ post ของแท็กที่ชื่อ comments มาเก็บใน $str
$str = $_POST["comments"];
#ประกาศไปที่หน้าเวบ
echo htmlspecialchars("Thank you for your comments \"" . $str . "\" :)", ENT_QUOTES);
