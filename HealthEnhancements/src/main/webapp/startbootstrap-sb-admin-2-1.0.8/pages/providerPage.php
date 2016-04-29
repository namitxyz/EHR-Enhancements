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
    <li role="presentation" ><a href="page5.html">Page5</a></li>
    <li role="presentation" class="active"><a href="page6.html">Page6</a></li>
    <li role="presentation" ><a href="page7.html">Page7</a></li>
  </ul>
  <!-- end navigation -->

  <!-- main section -->
  <div class="container"><br><br>
    <form class="form-inline">
      <div class="form-group">
        <input type="text" class="form-control" id="providerID" placeholder="Provider ID">
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="patientID" placeholder="Patient ID">
      </div>
      <button type="button" class="btn btn-danger" id="sub">Add Patient</button>
      <button type="button" class="btn btn-success" id="verify">Verify Patient</button>
      <button type="button" class="btn btn-primary" id="viewAll">View All Patients</button>
    </form>
    <div id="result"></div>
    <br>
    <!-- patient records table -->
    <div id="patientInfo">
      <h2>Patient Records</h2>
      <div class="container" id="medRecords">
        <table class="table table-striped" id="medRecordsTbl">
          <thead>
          <tr>
            <th>Condition</th>
            <th>Onset</th>
            <th>Severity</th>
            <th>Encounter</th>
            <th>Notes</th>
          </tr>
          </thead>
          <tbody> </tbody>
        </table>
        <br/>
        <table class="table table-striped" id="observationTbl">
          <thead>
          <tr>
            <th>Observation</th>
            <th>Value</th>
            <th>Unit</th>
            <th>Date</th>
            <th>Status</th>
          </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
    <!-- end patient records -->
    <!-- all patients table -->
    <div id="allPatients">
      <h2>Patients</h2>
      <div class="container" id="patients">
        <table class="table table-striped display" id="allPatientsTbl">
          <thread>
            <tr>
              <th>Patient ID</th>
              <th>Last Name</th>
              <th>First Name</th>
              <th>Middle Inital</th>
              <th>Gender</th>
              <th>Birth Date</th>
              <th>Address</th>
              <th>City</th>
              <th>State</th>
            </tr>
          </thread>
        </table>
      </div>
    </div>
    <!-- end all patients -->
  <!-- Charts! -->
  <!-- two columns -->
  <div class="row" id="patientCharts">
    <div class="col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">
          Patient Heart Data
        </div>
        <div class="panel-body">
          <div id="morris-area-chart" style="height: 250px;"></div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">
          Patient Body Data
        </div>
        <div class="panel-body">
          <div id="morris-area-chart2" style="height: 250px;"></div>
        </div>
      </div>
    </div>
  </div><br/><br/>
  </div>
  <!-- end two columns -->
  <!-- end Charts! -->
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

      // hide the tables and focus on the providerID textbox
      $('#providerID').focus();
      $('#patientInfo').hide();
      $('#allPatients').hide();
      $('#patientCharts').hide();


      // add patient to provider mapping
      $('#sub').click(function() {
        // hide the table if its showing
        $('#patientInfo').hide();
        $('#allPatients').hide();
        $('#patientCharts').hide();
        // get provider and patient IDs
        var provider = document.getElementById('providerID').value;
        var patient = document.getElementById('patientID').value;
        // make an ajax call to AddPatientToProviderMapping
        $.ajax({
          type: "POST",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/AddPatientToProviderMapping",
          headers: {
            PatientID: patient,
            ProviderID: provider
          },
          datatype: "json",
          success: function(data){
            // display the results of the mapping
            if (data['success']){
              $('#result').text(data['success']);
            } else {
              $('#result').text(data['error']);
            }
            // populate the table with the new patients data
            popRecordsTable(patient);
          },
          error: function(xhr){
            // output error text to console
            console.log(xhr.responseText);
          }
        });
        // clear the provider and patient ID fields and set focus on provider textbox
        document.getElementById('providerID').value = '';
        document.getElementById('patientID').value = '';
        $('#providerID').focus();
      });  //end add patient provider mapping


      // verify patient to provider mapping
      $('#verify').click(function() {
        // hide tables and chart
        $('#patientInfo').hide();
        $('#allPatients').hide();
        $('#patientCharts').hide();
        // get provider and patient IDs
        var provider = document.getElementById('providerID').value;
        var patient = document.getElementById('patientID').value;
        // make ajax call to IsValidPatientToProviderMapping
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/IsValidPatientToProviderMapping",
          headers: {
            PatientID: patient,
            ProviderID: provider
          },
          datatype: "json",
          success: function(data){
            // display the results of the validity check
            if (data['success']){
              $('#result').text(data['success']);
            } else {
              $('#result').text(data['error']);
            }
          },
          error: function(xhr){
            // output error text to console
            console.log(xhr.responseText);
          }
        });
        // clear the provider and patient ID fields and set focus on provider textbox
        document.getElementById('providerID').value = '';
        document.getElementById('patientID').value = '';
        $('#providerID').focus();
      }); //end verify patient


      // view all patients for provider
      $('#viewAll').click(function() {
        // hide  tables and chart
        $('#patientInfo').hide();
        $('#allPatients').hide();
        $('#patientCharts').hide();
        // get provider id entered
        var provider = document.getElementById('providerID').value;
        // make an ajax call to GetAllPatientsForProvider
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/GetAllPatientsForProvider",
          headers: {
            ProviderID: provider
          },
          datatype: "json",
          success: function(data){
            // populate a table with all the patients
            popPatientTbl(provider);
          },
          error: function(xhr){
            // output error text to console
            console.log(xhr.responseText);
          }
        });
        // clear the providerID textbox and set focus on it
        document.getElementById('providerID').value = '';
        $('#providerID').focus();
      }); //end view all patients


      // populate patient records table with data
      function popRecordsTable(patient) {
        // make an ajax call to GetPatientData
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/GetPatientData",
          headers: {
            PatientID: patient
          },
          datatype: 'json',
          success: function(data){
            // remove any exsisting rows in tables
            $('#medRecordsTbl TBODY tr').remove();
            $('#observationTbl TBODY tr').remove();
            // parse the time into a redable format
            condTime=[];
            for (var i = 0; i < data['conditons'].length; i++){
              var splitTime = data['conditons'][i].m_szOnsetTime.split("T");
              var condYear = splitTime[0].split("-");
              condTime[i] = condYear[1] + '/' + condYear[2] + '/' + condYear[0];
            }
            // populate the patient records table
            for (var i = 0; i < data['conditons'].length; i++){
              $('#medRecordsTbl TBODY').append('<tr><td>' + data.conditons[i].m_szConditionDisplay
                      + '</td><td>' + condTime[i]
                      + '</td><td>' + data.conditons[i].m_szSeverityDisplay
                      + '</td><td>' + data.conditons[i].m_szEncounterID
                      + '</td><td>' + data.conditons[i].m_szNotes + '</td></tr>');
            }
            // parse the time into a readable format
            obsTime=[];
            for (var i = 0; i < data['m_szObservations'].length; i++) {
              var splitTime2 = data['m_szObservations'][i].m_szObservationEffectiveTime.split("T");
              var obsYear = splitTime2[0].split("-");
              var obsHours = splitTime2[1].split(":");
              obsTime[i] = obsYear[1] + '/' + obsYear[2] + '/' + obsYear[0] + ' ' + obsHours[0] + ':' + obsHours[1];
            }
            // populate the observation table
            heartChart = [];
            for (var i = 0; i < data['m_szObservations'].length; i++) {
              $('#observationTbl TBODY').append('<tr><td>' + data.m_szObservations[i].m_szObservationDisplay
                      + '</td><td>' + data.m_szObservations[i].m_szObservationValue
                      + '</td><td>' + data.m_szObservations[i].m_szObservationUnit
                      + '</td><td>' + obsTime[i]
                      + '</td><td>' + data.m_szObservations[i].m_szObservationStatues + '</td></tr>');
              if (data.m_szObservations[i].m_szObservationDisplay == "Systolic blood pressure" ||
                      data.m_szObservations[i].m_szObservationDisplay == "Diastolic blood pressure"){

              }
              heartChart[i] = {period: obsTime[i] };
            }
            $('#patientCharts').show();
            // create a new chart
            new Morris.Area({
              element: 'morris-area-chart',
              data: [{
                period: '2005 Q1',
                Systolic: 142.0,
                Diastolic: 91.0,
                HeartRate: 92.0,
                RespiratoryRate: 15.0
              }, {
                period: '2005 Q2',
                Systolic: 147.0,
                Diastolic: 94.0,
                HeartRate: 93.0,
                RespiratoryRate: 14.0
              }, {
                period: '2005 Q3',
                Systolic: 145.0,
                Diastolic: 92.0,
                HeartRate: 92.0,
                RespiratoryRate: 16
              }],
              xkey: 'period',
              ykeys: ['Systolic', 'Diastolic', 'HeartRate', 'RespiratoryRate'],
              labels: ['Systolic', 'Diastolic', 'HeartRate', 'RespiratoryRate'],
              pointSize: 2,
              hideHover: 'auto',
              resize: true
            });

            // create a new chart
            new Morris.Area({
              element: 'morris-area-chart2',
              data: [{
                period: '2005 Q1',
                Height: 173.0,
                Weight: 93.2,
                BMI: 31.14,
                Temp: 37.1
              }, {
                period: '2005 Q2',
                Height: 173.0,
                Weight: 91.1,
                BMI: 30.439,
                Temp: 37.0
              }, {
                period: '2005 Q3',
                Height: 173.0,
                Weight: 92.2,
                BMI: 30.806,
                Temp: 37.1
              }],
              xkey: 'period',
              ykeys: ['Height', 'Weight', 'BMI', 'Temp'],
              labels: ['Height', 'Weight', 'BMI', 'Temp'],
              pointSize: 2,
              hideHover: 'auto',
              resize: true
            });


            // display the newly populated tables
            $('#patientInfo').show();

          }  //end success
        });  // end ajax
      }  //end popObserveTable


      // populate all patients table
      function popPatientTbl(provider) {
        // make ajax call to GetAllPatientsForProvider
        $.ajax({
          type: "GET",
          url: "http://ehr-namitgupta.rhcloud.com/webresources/GetAllPatientsForProvider",
          headers: {
            ProviderID: provider
          },
          datatype: "json",
          success: function (data) {
            // remove any row in the table
            $('#allPatientsTbl TBODY tr').remove();
            for (var i = 0; i < data.length; i++) {
              // make ajax call to GetPatientData
              $.ajax({
                type: "GET",
                url: "http://ehr-namitgupta.rhcloud.com/webresources/GetPatientData",
                headers: {
                  PatientID: data[i]
                },
                datatype: 'json',
                success: function (data) {
                  // populate the patient table with information
                  $('#allPatientsTbl TBODY').append('<tr><td>' + data['m_szPatientID']
                          + '</td><td>' + data['m_szLastName'] + '</td><td>' +  data['m_szFirstName']
                          + '</td><td>' +  data['m_szMiddleInitial'] + '</td><td>' +  data['m_szGender']
                          + '</td><td>' +  data['m_szBirthDate'] + '</td><td>' +  data['m_szAddress']
                          + '</td><td>' +  data['m_szCity'] + '</td><td>' + data['m_szState'] + '</td></tr>');
                },
                error: function (xhr) {
                  // output error text to console
                  console.log(xhr.responseText);
                }
              }); //end ajax
            }
          },
          error: function (xhr) {
            // output error text to console
            console.log(xhr.responseText);
          }
        }); //end ajax
        // display the table and charts (charts not working right now???)
        $('#allPatients').show();
      } //end popPatientTbl
    });  //end ready function

  </script>
  <!-- end scripts -->

</body>
</html>

