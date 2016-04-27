<html>
<head>
    <title> Team Zeus </title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.6-dist/css/login.css">
    <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</head>
<body>


<script>


   
</script>
<script>

$(document).ready(function(){
    $("#apiCall").click(function(e){
          e.preventDefault();
          var user = document.getElementById("userName").value;
          var pass = document.getElementById("password").value;
          var result = "";
          //alert (user + pass)
          
        $.when(
            $.ajax({
                type: "GET",
                headers: {"UserName": user, "Password": pass},
                url: "http://ehr-namitgupta.rhcloud.com/webresources/UserLogin",
                username: user,
                password: pass,
                success :(function (response){ 
                    console.log ("API successfully got the username and password");
                    //if responseSuccess:"User authenticated"
                    result = response.Success;
                    if (result === "User authenticated"){
                        window.location.href ="startbootstrap-sb-admin-2-1.0.8/pages/patiantFirstPage.php";
                    }else {
                         alert("Your username and password did not match our record. Please try again! ");
                    }
                }),
                error :(function(){ alert ("Something went wrong.Please try again!  line 43 index.php")})      
            }),

            $.ajax({
                type: "GET",
                headers: {"UserName": user, "Password": pass},
                url: "http://ehr-namitgupta.rhcloud.com/webresources/GetPatientProfile",
                username: user,
                password: pass,
                success :(function (response){ 
                    console.log ("API successfully got patiant's profile");
                    //if responseSuccess:"User authenticated"
                    result = response;
                    console.log (result)
                }),
                error :(function(){ alert ("Something went wrong.Please try again! line 58 index.php ")}) 
            })

        ).then( function(){
            console.log("All Api finished!!!")
        });
        //console.log(result);
    });
});
   
</script>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="pr-wrap">
                <div class="pass-reset">
                    <label>
                        Enter the email you signed up with</label>
                    <input type="email" placeholder="Email" />
                    <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                </div>
            </div>
            <div class="wrap">
                <p class="form-title">
                    Patiants' Sign In</p>
                <form class="login" action="">
                <input type="text" id="userName" placeholder="Username" />
                <input type="password" id="password" placeholder="Password" />
                <input type="submit" value="Sign In" class="btn btn-success btn-sm" id=apiCall " />
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" />
                                    Remember Me
                                </label>
                            </div>
                        </div>
                        <div class="col-md-6 forgot-pass-content">
                            <a href="javascription:void(0)" class="forgot-pass">Forgot Password</a></br></br>
                            <a href="startbootstrap-sb-admin-2-1.0.8/pages/providerLogin.php" >Are you a Provider? Click Here and log-in as provider.</a></br></br>
                            <a href="startbootstrap-sb-admin-2-1.0.8/pages/register.php" >Don't have a user-name and password yet? Register Now.</a>

                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
    <div class="posted-by">CREATED BY: TEAM ZEUS</div>
</div>
<body>