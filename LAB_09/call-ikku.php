<?php
error_reporting(-1);
ini_set('display_errors', 'On');
header("Content-type: text/html; charset=utf-8");
$json = file_get_contents('http://www.kku.ac.th/ikku/api/activities/services/topActivity.php');
$text = json_decode($json, true);
?>
<html>
    <head><title>Patcharaphong</title>
    </head>
    <body>
        <table>
            <?php
            foreach ($text["activities"] as $key => $value) {
                $key += 1;
                echo '<tr><td>'.$key.'</td><td>'.$value["dateSt"].' '. '<a href="' . $value["image"] . '">' . $value["title"] . '</a>'.'</td><td>'.$value["contact"]["phone"].'</td></tr>';
            }
            ?>
            </table>
    </body>
</html>