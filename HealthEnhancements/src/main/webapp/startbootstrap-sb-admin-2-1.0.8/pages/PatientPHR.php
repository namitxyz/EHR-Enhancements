<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width+device=width, inital-scale=1">

    <title>Team Zeus EMR</title>
    <link rel="icon" href="logo.ico">

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="../dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <!-- navigation -->
    <ul class="nav nav-tabs">
        <li role="presentation" ><a href="page1.html">Home</a></li>
        <li role="presentation" ><a href="page2.html">Page2</a></li>
        <li role="presentation" ><a href="page3.html">Page3</a></li>
        <li role="presentation" ><a href="page4.html">Page4</a></li>
        <li role="presentation" class="active"><a href="page5.html">Page5</a></li>
        <li role="presentation" ><a href="page6.html">Page6</a></li>
        <li role="presentation" ><a href="page7.html">Page7</a></li>
    </ul>
    <!-- end navigation -->

    <!-- main section -->
    <div class="container"><br><br>
        <div id="patientPHR">
            <h3>Patient Personal Health Record - Self Reporting</h3>
            <div id="gridSection"> <!-- 3 columns -->
                <div class="row">
                    <div class="col-md-4"> <!-- column 1 - left -->
                        <!-- Standard button -->
                        <h4>Measurements</h4>
                        <button type="button" class="btn btn-default" id="weight">Weight</button>
                        <button type="button" class="btn btn-default" id="bloodPressure">Blood Pressure</button>
                        <button type="button" class="btn btn-default">Blood Glucose</button><br/><br/>
                        <button type="button" class="btn btn-default">Body Composition</button>
                        <button type="button" class="btn btn-default">Food & Drink</button>
                        <button type="button" class="btn btn-default">Steps</button><br/><br/>
                        <button type="button" class="btn btn-default">Body Dimensions</button>
                        <button type="button" class="btn btn-default">Cholesterol</button>
                        <button type="button" class="btn btn-default">Exercise</button><br/><br/>
                    </div>
                    <div class="col-md-4"> <!-- column 2 - middle -->
                        <h4>Current Health</h4>
                        <button type="button" class="btn btn-default">Appointments</button>
                        <button type="button" class="btn btn-default">Allergies</button>
                        <button type="button" class="btn btn-default">Lab Results</button><br/><br/>
                        <button type="button" class="btn btn-default">Medications</button>
                        <button type="button" class="btn btn-default">Procedure</button>
                        <button type="button" class="btn btn-default">Conditions</button><br/><br/>
                        <button type="button" class="btn btn-default">Immunizations</button>
                        <button type="button" class="btn btn-default">Medical devices</button>

                    </div>
                    <div class="col-md-4"> <!-- column 3 - right -->
                        <div id="dataEntry">
                            <h4>Enter Your Health Information</h4>
                            <div id="dataEntryTemplate"></div>
                        </div>
                    </div>
                </div> <!-- end row -->
            </div> <!-- end columns -->
            <br/>
            <div id="message"></div>
        </div> <!-- end PHR -->
    </div> <!-- end container -->
    <!-- end main section -->
    <!-- scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript-->
    <script src="../bower_components/raphael/raphael-min.js"></script>
    <script src="../bower_components/morrisjs/morris.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>
    <!--<script>
      $(document).ready(function(){
        // $('#pcpComments').text('55');

      });

      var i =44;
    </script>  -->


    <script>
        $(document).ready(function(){

            // hide the data entry div when page loads
            $('#dataEntry').hide();

            // record weight
            $('#weight').click(function(){
                $('#message').text('');
                $('#dataEntry').show();
                var form = '<form class="form-horizontal"><div class="form-group">'
                        + '<input type="text" class="form-control" id="weightEntry" placeholder="Weight">'
                        + '<select class="form-control" id="weightUnits"><option>lbs</option><option>kg</option></select>'
                        + '</div><br/><div class="form-group">'
                        + '<input type="date" class="form-control" id="weightRecordedEntry" placeholder="Date">'
                        + '</div><br/><div class="form-group">'
                        + '<button type="text" class="btn-primary" id="weightSubmit">Record Weight</button></div></form>';
                $('#dataEntryTemplate').html(form);
                $('#weightEntry').focus();
                $('#weightSubmit').click(function(e){
                    e.preventDefault();
                    var weight = document.getElementById('weightEntry').value + ' ' + document.getElementById('weightUnits').value;
                    sessionStorage.setItem("weight", weight);
                    sessionStorage.setItem("weightDate", document.getElementById('weightRecordedEntry').value);
                    //console.log(sessionStorage.getItem("weight"));
                    //console.log(sessionStorage.getItem("weightDate"));
                    // hide the entry table and display a success message
                    $('#dataEntry').hide();
                    $('#message').text("Weight Recorded");
                })
            }); //end weight

            // record blood pressure
            $('#bloodPressure').click(function(){
                $('#message').text('');
                $('#dataEntry').show();
                var form = '<form class="form-horizontal"><div class="form-group">'
                        + '<input type="text" class="form-control" id="systolicEntry" placeholder="Systolic">mmHg'
                        + '</div><br/><div class="form-group">'
                        + '<input type="text" class="form-control" id="diastolicEntry" placeholder="Diastolic">mmHg'
                        + '</div><br/><div class="form-group">'
                        + '<input type="text" class="form-control" id="pulseEntry" placeholder="Pulse">beats/min'
                        + '</div><br/><div class="form-group">'
                        + '<select class="form-control" id="irregularBeat">'
                        + '<option>Unknown</option><option>Yes</option><option>No</option></select>'
                        + '</div><br/><div class="form-group">'
                        + '<input type="date" class="form-control" id="pressureRecordedEntry" placeholder="Date">'
                        + '</div><br/><div class="form-group">'
                        + '<button type="text" class="btn-primary" id="pressureSubmit">Record Pressure</button></div></form>';
                $('#dataEntryTemplate').html(form);
                $('#systolicEntry').focus();
                $('#pressureSubmit').click(function(e){
                    e.preventDefault();
                    sessionStorage.setItem("systolic", document.getElementById('systolicEntry').value);
                    sessionStorage.setItem("diastolic", document.getElementById('diastolicEntry').value);
                    sessionStorage.setItem("pulse", document.getElementById('pulseEntry').value);
                    sessionStorage.setItem("beat", document.getElementById('irregularBeat').value);
                    sessionStorage.setItem("pressureDate", document.getElementById('pressureRecordedEntry').value);
                    //console.log(sessionStorage.getItem("systolic"));
                    //console.log(sessionStorage.getItem("diastolic"));
                    // hide the enrty table and display a sucess message
                    $('#dataEntry').hide();
                    $('#message').text("Blood Pressure Recorded");
                })
            }); //end weight

            // record blood glucose


            // record body composition


            // record food & drink


            // record steps


            // record body dimensions


            // record cholesterol


            // record exercise


            // record appointments


            // record allergies


            // record lab results


            // record medications


            // record procedures


            // record conditions


            // record immunizations


            // record medical devices


        });  //end ready function

    </script>

    <!-- end scripts -->

</body>
</html>
