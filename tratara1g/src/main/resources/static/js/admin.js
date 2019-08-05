$( document ).ready(function () {
    btn=document.getElementById('progress-btn');
    prog=document.getElementById('progress-box');
    text=document.getElementById('progress-text');
    
    btn.onclick=function() {
        prog.classList.add('anima');
        prog.addEventListener("transitionend", function(event) {
          prog.classList.remove('anima');
        }, false);
    }
    
    var baseURL = window.location.protocol + "//" + window.location.hostname;
    
    // {"pcName":null,"ipAddress":null,"endTime":null,"startTime":null,"active":false},
    $('#PCStatusTable').dataTable({
        ajax: {
            url: baseURL + "/admin/pcstatus",
            type: "GET",
            dataSrc: ""
          },
        columns: [
           { data : "pcName" },
           { data : "active"},
           { data : "ipAddress" },
           { data : "startTime"},
           { data : "endTime" },
           { data : "endTime" }
        ]
    });
});