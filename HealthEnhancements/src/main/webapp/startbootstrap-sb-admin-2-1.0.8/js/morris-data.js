$(function() {

    Morris.Line({
        element: 'morris-line-chart',
        data: [{
            period: '2014 Q1',
            Condition: 1,
            Medication: null,
            Encounter: 2
        }, {
            period: '2014 Q2',
            Condition: 2,
            Medication: 2,
            Encounter: 2
        }, {
            period: '2014 Q3',
            Condition: 1,
            Medication: 6,
            Encounter: 4
        }, {
            period: '2014 Q4',
            Condition: 3,
            Medication: 4,
            Encounter: 5
        }, {
            period: '2015 Q1',
            Condition: 1,
            Medication: 7,
            Encounter: 3
        }, {
            period: '2015 Q2',
            Condition: 2,
            Medication: 3,
            Encounter: 1
        }, {
            period: '2015 Q3',
            Condition: 4,
            Medication: 4,
            Encounter: 4
        }, {
            period: '2015 Q4',
            Condition: 1,
            Medication: 6,
            Encounter: 5
        }, {
            period: '2016 Q1',
            Condition: 1,
            Medication: 4,
            Encounter: 3
        }, {
            period: '2016 Q2',
            Condition: 1,
            Medication: 2,
            Encounter: 1
        }],
        xkey: 'period',
        ykeys: ['Condition', 'Medication', 'Encounter'],
        labels: ['Condition', 'Medication', 'Encounter'],
        pointSize: 8,
        hideHover: 'auto',
        resize: true
    });
    
    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2014 Q1',
            Condition: 2666,
            Medication: null,
            Encounter: 2647
        }, {
            period: '2014 Q2',
            Condition: 2778,
            Medication: 2294,
            Encounter: 2441
        }, {
            period: '2014 Q3',
            Condition: 4912,
            Medication: 1969,
            Encounter: 2501
        }, {
            period: '2014 Q4',
            Condition: 3767,
            Medication: 3597,
            Encounter: 5689
        }, {
            period: '2015 Q1',
            Condition: 6810,
            Medication: 1914,
            Encounter: 2293
        }, {
            period: '2015 Q2',
            Condition: 5670,
            Medication: 4293,
            Encounter: 1881
        }, {
            period: '2015 Q3',
            Condition: 4820,
            Medication: 3795,
            Encounter: 1588
        }, {
            period: '2015 Q4',
            Condition: 15073,
            Medication: 5967,
            Encounter: 5175
        }, {
            period: '2016 Q1',
            Condition: 10687,
            Medication: 4460,
            Encounter: 2028
        }, {
            period: '2016 Q2',
            Condition: 8432,
            Medication: 5713,
            Encounter: 1791
        }],
        xkey: 'period',
        ykeys: ['Condition', 'Medication', 'Encounter'],
        labels: ['Condition', 'Medication', 'iPod Touch'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "Activ days Over 1 hour",
            value: 12
        }, {
            label: "Active days Below 1 Hour",
            value: 30
        }, {
            label: "Active days Over 2 hours",
            value: 20
        }],
        resize: true
    });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
            y: '2006',
            a: 100,
            b: 90
        }, {
            y: '2007',
            a: 75,
            b: 65
        }, {
            y: '2008',
            a: 50,
            b: 40
        }, {
            y: '2009',
            a: 75,
            b: 65
        }, {
            y: '2014',
            a: 50,
            b: 40
        }, {
            y: '2015',
            a: 75,
            b: 65
        }, {
            y: '2016',
            a: 100,
            b: 90
        }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        hideHover: 'auto',
        resize: true
    });

});
