function Main(){
    othis = this;
    console.log("main.js loaded!");

    $(".applicationContent").load("/NewIfcParser/jsp/login.html", function(){
        othis.current = new Login(othis);
    });
    
    this.apiCall = function(url, method, data, successCallback, errorCallback){
        console.log("main.js apiCall");
        $.ajax({
            url: url,
            method: method,
            data: data,
            error: errorCallback,
            success: successCallback
        });
    };
}

main = new Main();
