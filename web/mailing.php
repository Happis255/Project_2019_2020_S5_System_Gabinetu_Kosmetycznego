<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require $_SERVER['DOCUMENT_ROOT'] . '/mail/Exception.php';
require $_SERVER['DOCUMENT_ROOT'] . '/mail/PHPMailer.php';
require $_SERVER['DOCUMENT_ROOT'] . '/mail/SMTP.php';

ob_start();
$mail = new PHPMailer;
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
$mail->Subject = '[M] Wiadomosc ze strony Gabinet Kosmetyczny Gracja od: ' .  $_POST['email'] . ' ' . $_POST['imie'];
$mail->msgHTML($_POST['tresc']);
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
    header("Location: index.jsp");
    ob_end_flush();
    die();
}