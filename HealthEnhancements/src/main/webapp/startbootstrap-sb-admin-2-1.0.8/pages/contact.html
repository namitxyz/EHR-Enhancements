<!DOCTYPE html>
<html lang="en">
    <link href="http://steveville.org/assets/css/cosmo.css" rel="stylesheet" type="text/css" media="all" />
    
    <body style="background-color: #ccc;">
        <div class="text-center">
            <h1>
                [site name]
            </h1>
        </div>
        <hr>
        <br />
        <div class="maincontent" style="background-color: #FFF; margin: auto; padding: 20px; width: 450px; border-top: 2px solid #27ae60;">
            <div class="text-center">
                <h1>Dear {{{ $user->name }}},</h1>
                <p>Welcome to the [site name] website!</p>
                <p>
                    Someone registered the username <b>{{{ $user->name }}}</b> at <a href="{{{ config('app.url') }}}">{{{ config('app.url') }}}</a> using this email <a href="mailto:{{{ $user->email }}}">{{{ $user->email }}}.
                </p>
                <p>If this was you, please finish the registration process by confirming your email below.</p>
                <p><a class="btn btn-success btn-lg" href="{{{ URL::to('/account/confirm/' . $confirmation->code) }}}"><i class="fa fa-check"></i> CONFIRM E-MAIL</button></p>
                
                <p>If the fancy button does not work, you can copy and paste this link into your browser: <a href="{{{ URL::to('/account/confirm/' . $confirmation->code) }}}">{{{ URL::to('/account/confirm/' . $confirmation->code) }}}</a></p>
                <p>If you did not register for our website, please ignore this email.</p>
            </div>
        </div>
    </body>
</html>

<?php
    $to = 'absinco.gmail.com';  // Please change to your email address
    $headers = 'From: Contact Form';   // Please change to your contact form name

    $name = $_POST['name'];
    $email = $_POST['email'];
    $subject = $_POST['subject'];
    $comment = $_POST['comment'];
    $body = " Name: $name\n E-Mail: $email\n Subject: $subject\n Comment: $comment\n";

    $answer = $_POST['answer'];
    if ($_POST['submit'] && $answer == '7') {          // Please change to your answer
        if (mail ($to, $subject, $body, $headers)) {
        echo '<p class="contact-success">Thank you for contacting us.</p>';
    } else {
        echo '<p class="contact-error">Something went wrong. Please try again.</p>';
    }
    } else if ($_POST['submit'] && $answer != '7') {   // Please change to your answer
    echo '<p class="contact-error">Please enter the Correct verification number.</p>';
    }

// Spam Prevention http://tangledindesign.com/how-to-create-a-contact-form-using-html5-css3-and-php/
?>

</body>
</html>