<?php

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require $_SERVER['DOCUMENT_ROOT'] . '/mail/Exception.php';
require $_SERVER['DOCUMENT_ROOT'] . '/mail/PHPMailer.php';
require $_SERVER['DOCUMENT_ROOT'] . '/mail/SMTP.php';

ob_start();
$target_dir = "upload/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;

$docFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

if($docFileType != "pdf" && $docFileType != "doc" && $docFileType != "docx") {
    $uploadOk = 0;
    header("Location: blad01.jsp");
    ob_end_flush();
    die();
}

if (file_exists($target_file)) {
    $uploadOk = 0;
    header("Location: blad01.jsp");
    ob_end_flush();
    die();
}

if ($_FILES["fileToUpload"]["size"] > 52428800) {
    $uploadOk = 0;
    header("Location: blad01.jsp");
    ob_end_flush();
    die();
}


if ($uploadOk == 0) {
    header("Location: blad01.jsp");
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
    } else {
        ob_end_flush();
        die();
        header("Location: blad01.jsp");
    }
}

$mail = new PHPMailer;
$mail->CharSet = "UTF-8";
$mail->isSMTP(); 
$mail->SMTPDebug = 2;
$mail->Host = "smtp.gmail.com";
$mail->Port = 587;
$mail->SMTPSecure = 'tls';
$mail->SMTPAuth = true;
$mail->Username = 'projektgracja2019@gmail.com';
$mail->Password = 'Projekt_2019';
$mail->setFrom('projektgracja2019@gmail.com', $_POST['imie']);
$mail->addAddress('projektgracja2019@gmail.com', 'Gabinet Gracja');
$mail->Subject = '[M - CV] Nowe złoszenie o pracę od: ' .  $_POST['imie-nazwisko'] .'.';
$mail->msgHTML(
"Imię i nazwisko: " . $_POST['imie-nazwisko'] . "<br />" .
"Data urodzenia: " . $_POST['data-urodzenia'] . "<br />" .
"E-Mail: " . $_POST['e-mail'] . "<br />" .
"Telefon kontaktowy: " . $_POST['telefon'] . "<br />" .
"Adres: " .  $_POST['ulica'] . " " . $_POST['Numer-domu'] . "<br />" .
$_POST['kod-pocztowy'] . " " . $_POST['miejscowosc'] . "<br />"
);
$mail->AddAttachment($target_file);
$mail->AltBody = 'HTML messaging not supported';
$mail->SMTPOptions = array(
                    'ssl' => array(
                        'verify_peer' => false,
                        'verify_peer_name' => false,
                        'allow_self_signed' => true
                    )
                );

if(!$mail->send()) {
    header("Location: blad01.jsp");
    ob_end_flush();
    die();
} else {
    header("Location: potwierdz_email.jsp");
    unlink($target_file);
    ob_end_flush();
    die();
}
?>