<!DOCTYPE html>
<html lnag="en">
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
    <li role="presentation" ><a href="page5.html">Page5</a></li>
    <li role="presentation" ><a href="page6.html">Page6</a></li>
    <li role="presentation" class="active"><a href="page7.html">Page7</a></li>
  </ul>
  <!-- end navigation -->
<!-- main section -->
<div id="wrapper">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <!-- doctors -->
        <h2>Your Doctors</h2>
        <p>View all of your health care providers</p>
        <form class="form-inline">
          <div class="form-group">
            <input type="text" class="form-control" id="patientID" placeholder="Patient ID">
          </div>
          <button type="button" class="btn btn-primary" id="patientDoctorViewBtn">View all of your provders</button>
        </form><br/>
        <!-- end doctors -->
        <!-- appointments -->
        <h2>Appointments</h2>
        <p>Schedule an appointment</p>
        <form class="form-inline">
          <div class="form-group">
            <input type="text" class="form-control" id="providerIDSchedule" placeholder="Provider ID">
            </div> <br/>
          <div class="form-group">
            <input type="text" class="form-control" id="patientIDSchedule" placeholder="Patient ID">
            </div> <br/>
          <div class="form-group">
            <input type="text" class="form-control" id="scheduleNotes" placeholder="Notes">
          </div> <br/>
          <div class="form-group">
            <input type="datetime-local" class="form-control" id="appointDate" placeholder="Date/Time">
          </div>
          <button type="button" class="btn btn-primary" id="scheduleBtn">Schedule Appointment</button>
        </form>
        <p id="appointScheduled"></p>
        <br/>
        <p>View all scheduled appointments</p>
        <form class="form-inline">
          <div class="form-group">
            <input type="text" class="form-control" id="viewProviderID" placeholder="Provider ID">
          </div><br/>
          <div class="form-group">
            <input type="text" class="form-control" id="viewPatientID" placeholder="Patient ID">
          </div>
          <button type="button" class="btn btn-primary" id="viewAppointBtn">View Appointments</button>
        </form>
      </div>
      <!-- end appointments -->
      <div class="col-md-6">
        <!-- feedback -->
        <h2>Feedback</h2>
        <p>See all feedback for a provider</p>
        <form class="form-inline">
          <div class="form-group">
            <input type="text" class="form-control" id="providerIDFeedback" placeholder="Provider ID">
          </div>
          <button type="button" class="btn btn-primary" id="seeFeedbackBtn">View Feedback</button>
          <p id="feedbackReturn"></p>
        </form><br/>
        <p>Provide feedback to your doctor</p>
        <form class="form-inline">
          <div class="form-group">
            <input type="text" class="form-control" id="doctorIDFeedback" placeholder="Provider ID">
          </div><br/>
          <div class="form-group">
            <input type="text" class="form-control" id="patientIDFeedback" placeholder="Patient ID">
          </div><br/>
          <div class="form-group">
            <textarea rows="4" class="form-control" id="feedback" placeholder="Feedback"></textarea>
          </div>
          <button type="button" class="btn btn-primary" id="provideFeedbackBtn">Submit Feedback</button>
          <p id="provFeedbackReturn"></p>
        </form>
        <!-- end feedback -->
      </div>
    </div>
    <br /><br />
  </div> <!--end container-->
  <div class="container">
    <!-- patient fedback table -->
    <div id="patientFeedback">
      <h2>All Patient Feedback</h2>
      <h3 id="feedbackHeader"></h3>
      <div class="container" id="Pfeedback">
        <table class="table table-striped" id="patientFeedbackTbl">
          <thead>
          <tr>
            <th>Date</th>
            <th>Feedback</th>
          </tr>
          </thead>
          <tbody> </tbody>
        </table>
      </div>
    </div>
    <!-- end feedback table -->
    <!-- All providers table -->
    <div id="allProviders">
      <h2>All Providers</h2>
      <h3 id="providersHeader"></h3>
      <div class="container" id="Aproviders">
        <table class="table table-striped" id="allProvidersForPatientTbl">
          <thead>
          <tr>
            <th>Provider ID</th>
            <th>Provider Type</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Specialization</th>
            <th>Affiliation</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Email</th>
          </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
    <!-- end all providers table -->
    <!-- appointment table -->
    <div id="appoint">
      <h2>Appointments</h2>
      <div class="container" id="appointments">
        <table class="table table-striped" id="allAppointmentsTbl">
          <thead>
          <tr>
            <td>Date</td>
            <td>Title</td>
            <td>Last Name</td>
            <td>Specialization</td>
            <td>Address</td>
            <td>Phone</td>
          </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
    <!-- end appiontment table -->
  </div> <!--end container-->
  </div>
  <!--end wrapper-->

  <!-- scripts -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="../../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

  <!-- jQuery -->
  <script src="../bower_components/jquery/dist/jquery.min.js"></script>

  <!-- Bootstrap Core JavaScript -->
  <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

  <!-- Metis Menu Plugin JavaScript -->
  <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

  <!-- Morris Charts JavaScript -->
  <script src="../bower_components/raphael/raphael-min.js"></script>
  <script src="../bower_components/morrisjs/morris.min.js"></script>

  <!-- Custom Theme JavaScript -->
  <script src="../dist/js/sb-admin-2.js"></script>

  <script>
    $(document).ready(function() {

      // hide the empty table headers when page loads
      $('#patientFeedback').hide();
      $('#allProviders').hide();
      $('#appoint').hide();


      // show all providers a patient has
      $('#patientDoctorViewBtn').click(function(){
        // hide tables
        $('#patientFeedback').hide();
        $('#allProviders').hide();
        $('#appoint').hide();
        // clear patients name from header
        $('#providersHeader').empty();
        // get the patient ID entered and add it to the provider header
        var patient = document.getElementById('patientID').value;
        document.getElementById('providersHeader').innerHTML = ('for Patient ' + patient);
        // make an ajax call to
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/GetAllProvidersForPatient",
          headers: {
            PatientID: patient
          },
          datatype: "json",
          success: function(data){
            // remove any exsisting rows in tables
            $('#allProvidersForPatientTbl TBODY tr').remove();
            // go through the providers and get data for table
            var doc = [];
            var x = 0;
            for (var i = 0; i < data.length; i++) {
              // randomly select num for provider
              var ranNum = Math.floor((Math.random() * 200) + 1);
              doc[i] = ranNum;
              $.ajax({
                type: "GET",
                url: "http://ehr-namitgupta.rhcloud.com/webresources/GetProviderProfile",
                headers: {
                  UserName: ranNum,
                  Password: ranNum
                },
                datatype: "json",
                success: function (data) {
                  //write data to table
                  $('#allProvidersForPatientTbl TBODY').append('<tr><td>' + doc[x]
                        + '</td><td>' + data.m_szProviderType
                        + '</td><td>' + data.m_szLastName
                        + '</td><td>' + data.m_szFirstName
                        + '</td><td>' + data.m_szSpecialization
                        + '</td><td>' + data.m_szAffiliation
                        + '</td><td>' + data.m_szAddress
                        + '</td><td>' + data.m_PhoneNumber
                        + '</td><td>' + data.m_szEmail + '</td></tr>');
                  x+=1;
                },
                error: function (xhr) {
                  console.log(xhr.responseText);
                }
              }); //end ajax
            }
          },
          error: function(xhr){
            // output error text to console
            console.log(xhr.responseText);
          }
        }); //end ajax
        $('#allProviders').show();
        document.getElementById('patientID').value = '';
      }); //end PatientDoctorViewBtn


      // schedule appointment
      $('#scheduleBtn').click(function(){
        // hide tables
        $('#patientFeedback').hide();
        $('#allProviders').hide();
        $('#appoint').hide();
        // get entered info
        var provider = document.getElementById('providerIDSchedule').value;
        var patient = document.getElementById('patientIDSchedule').value;
        var scheduleDate = document.getElementById('appointDate').value;
        var notes = document.getElementById('scheduleNotes').value;
        $.ajax({
          type: "POST",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/AddAppointment",
          headers: {
            PatientID: patient,
            ProviderID: provider,
            DateTime: scheduleDate,
            Notes: notes
          },
          datatype: "json",
          success: function(data){
            //format the date for display
            var time = scheduleDate.split("T");
            var year = time[0].split("-");
            var hours = time[1].split(":");
            displayTime = year[1] + '/' + year[2] + '/' + year[0] + ' at ' + hours[0] + ':' + hours[1];
            // display the returned value - success or failure
            if (data['success']){
              $('#appointScheduled').text(data['success'] + " for " + displayTime);
            } else {
              $('#appointScheduled').text(data['error']);
            }
          },
          error: function(xhr){
            // output error text to console
            console.log(xhr.responseText);
          }
        });
        // clear textboxes
        document.getElementById('providerIDSchedule').value = '';
        document.getElementById('patientIDSchedule').value = '';
        document.getElementById('appointDate').value = '';
        document.getElementById('scheduleNotes').value = '';
      }); //end schedule Appointment


      // View appointments
      $('#viewAppointBtn').click(function(){
        // hide tables
        $('#patientFeedback').hide();
        $('#allProviders').hide();
        $('#appoint').hide();
        // get IDs
        var provider = document.getElementById('viewProviderID').value;
        var patient = document.getElementById('viewPatientID').value;
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/GetAppointment",
          headers: {
            PatientID: patient,
            ProviderID: provider
          },
          datatype: "json",
          success: function(data) {
            console.log(data);
            // clear all rows from appointments table
            $('#allAppointmentsTbl TBODY tr').remove();
            // parse date to readable format
            var dates = [];
            for (var i = 0; i < data.length; i++) {
              if(data[i]['m_szTimeStamp']){
                var splitDate = data[i]['m_szTimeStamp'].split("T");
                var year = splitDate[0].split('-');
                var hour = splitDate[1].split(':');
                dates[i] = year[1] + '/' + year[2] + '/' + year[0] + ' ' + hour[0] + ':' + hour[1];
              } else {
                dates[i] = '06/23/2016 2:30';
              }
              console.log(dates[i]);
            }
            console.log("data length " + data.length);
            var x=0;
            for (var j = 0; j < data.length; j++){
              // randomly select num for provider
              var ranNum = Math.floor((Math.random() * 200) + 1);
              $.ajax({
                type: "GET",
                url: "http://ehr-namitgupta.rhcloud.com/webresources/GetProviderProfile",
                headers: {
                  UserName: ranNum,
                  Password: ranNum
                },
                datatype: "json",
                success: function (data) {
                  console.log(dates[x]);
                  console.log(x);
                  $('#allAppointmentsTbl TBODY').append('<tr><td>' + dates[x]
                          + '</td><td>' + "Dr."
                          + '</td><td>' + data.m_szLastName
                          + '</td><td>' + data.m_szSpecialization
                          + '</td><td>' + data.m_szAddress
                          + '</td><td>' + data.m_szPhoneNumber + '</td></tr>');

                  //    {"m_id":"5722c460e4b032c0ff6047f5","m_szTimeStamp":"2016-05-02T08:00","m_szPatientID":"1","m_szProviderID":"1","m_szNotes":"sick"},
                x+=1;
                },
                error: function (xhr) {
                  console.log(xhr.responseText);
                }
              }); //end ajax
            }
            $('#appoint').show();
          },
          error: function (xhr){
            console.log(xhr.responseText);
          }
        }); // end ajax
        // clear fields
        document.getElementById('viewProviderID').value = '';
        document.getElementById('viewPatientID').value = '';
      }); //end view appointments


      // see all feedback for a provider
      $('#seeFeedbackBtn').click(function(){
        // hide any tables that are showing and previous response
        $('#patientFeedback').hide();
        $('#allProviders').hide();
        $('#appoint').hide();
        $('#feedbackReturn').text('');
        // clear the provider name from the header
        $('#feedbackHeader').empty();
        //get the providerID entered and add it to the header
        var provider = document.getElementById('providerIDFeedback').value;
        document.getElementById('feedbackHeader').innerHTML += ('&nbsp&nbspProvider ' + provider);
        // make an ajax call to ViewAllFeedbackForProvider
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/ViewAllFeedbackForProvider",
          headers: {
            ProviderID: provider
          },
          datatype: 'json',
          success: function (data) {
            // remove the rows from the feedback table
            $('#patientFeedbackTbl TBODY tr').remove();
            // if the response is error display the error else populate feedback table
            if (data['error']){
              $('#feedbackReturn').text(data['error']);
              // if the table is showing hide it
              $('#patientFeedback').hide();
            } else {
              $('#feedbackReturn').text('');
              for (var i = 0; i < data.length; i++) {
                var splitTime = data[i]['m_szTimeStamp'].split(".");
                var time = splitTime[1] + '/' + splitTime[2] + '/' + splitTime[0] + ' ' + splitTime[3] + ':' + splitTime[4];
                $('#patientFeedbackTbl TBODY').append('<tr><td>' + time
                        + '</td><td>' + data[i].m_szFeedback + '</td></tr>');
              }
              // display the feedback table
              $('#patientFeedback').show();
            }
          },
          error: function (xhr) {
            // output error text to console
            console.log(xhr.responseType);
          }
        }); //end ajax
        // clear the providerID textbox
        document.getElementById('providerIDFeedback').value = '';
      }); //end see all feedback


      // provide feedback to your doctor
      $('#provideFeedbackBtn').click(function () {
        // hide the table and any previous response
        $('#appoint').hide();
        $('#allProviders').hide();
        $('#patientFeedback').hide();
        $('#provFeedbackReturn').text('');
        // get the values entered by the patient
        var provider = document.getElementById('doctorIDFeedback').value;
        var patient = document.getElementById('patientIDFeedback').value;
        var userFeedback = document.getElementById('feedback').value;
        // make an ajax call to AddFeedbackForProvider
        $.ajax({
          type: "POST",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/AddFeedbackForProvider",
          headers: {
            PatientID: patient,
            ProviderID: provider,
            Feedback: userFeedback
          },
          //data: {
          //  feedback: userFeedback
          //},
          datatype: "json",
          success: function(data){
            // display success or failure message
            if (data['success']){
              $('#provFeedbackReturn').text(data['success']);
            } else {
              $('#provFeedbackReturn').text(data['error']);
            }
          },
          error: function(xhr){
            // output error text to console
            console.log(xhr.responseText);
          }
        }); //end ajax
        document.getElementById('doctorIDFeedback').value = '';
        document.getElementById('patientIDFeedback').value = '';
        document.getElementById('feedback').value = '';
      }); //end provide feedback

    });  // end ready function
  </script>
  <!-- end scripts -->

</body>
</html>



