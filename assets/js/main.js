/// <reference path="../typings/tsd.d.ts" /
$(document).ready(function(){
    var apiResponse ="";
    var requestComplete= false;
    var templateList =[];


    function doApiRequest(type){       
        $.ajax({
            method: "GET",
           // url: "https://api.icndb.com/jokes/" + apiType + apiLimit + apiName,
           url: QueryBuilder(type),
           dataType: "json"
        }).done(function(response){
           console.log(response);
           console.log(templateList);
            getAllQuotes(response);
        }).fail(function(){
            console.log("Something went wrong")
        })
       /* var limit = $('input[name="limit"]:checked').val();
        var apiType="";
        var apiName ="";
        var apiLimit="";
        switch(type){
            case "random" : apiType = "random"; break;
            case "multiple": apiType = "";break;
            default : apiType="random";break;
        }
        if (type === "random"){
            if(limit !== null && limit!==""){
                switch(limit){
                    case "nerdy": apiLimit ="?limitTo=[nerdy]"; break;
                    case "explicit": apiLimit ="?limitTo=[explicit]"; break;
                    case "all": apiLimit =""; break;
                    default: apiLimit =""; break;
                }
            }
            else{
                apiLimit="";
            }
        }
        else{
            if (fname !== null && lname !== null){
                if(limit ==="all"){
                    apiName = "?firstName=" + fname + "&lastName=" + lname;    
                }
                else{
                    apiName = "firstName=" + fname + "&lastName=" + lname;   
                }
            }
            else{
                apiName="";
            }
        }*/
}


function getApiResponse(response){
return response;
}



function getAllQuotes(data){
    clearAllQuotes()
    for (i=0;i<data.value.length;i++){
    var template = $('#card-template').clone();
    template.attr('id', '');
    console.log(template.find('span').text(data.value[i].joke))
    var img =template.find('img');
    generateImage(img);
    $('#quote-container').append(template);
    template.show();
    templateList.push(template);
    }
}

function clearAllQuotes(){
 $("#quote-container").empty();

}
function getPersonalisedQuote(data){

}

function getRandomQuote(data){

}

function convertToList(){

}
function convertToCard(){

}
function generateImage(img){
    var random = getRandomInt(7);
    var imgPath = "assets/images/"+random.toString()+".jpg";
    img.attr("src",imgPath);
    img.show();

}
function getRandomInt(max) {
    return 1+Math.floor(Math.random() * Math.floor(max));
  }

$('input[name="limit"]').click(function(){
  doApiRequest("")
})

function QueryBuilder(type){
    var fname;
    var lname;
    var limit;
    var creds;
    var apiLimit;
    var apiType;

    if(type === "random"){
    apiType ="random";
    fname = $("#first-name").val();
    lname = $("#last-name").val();
    if(fname !=="" && lname !==""){
        creds = "firstName=" + fname + "&lastName=" + lname;
    }
    }
    else{
    apiType="";
    creds="";;
    }
   var limit = $('input[name="limit"]:checked').val();
    switch(limit){
        case "nerdy": apiLimit ="?limitTo=[nerdy]"; break;
        case "explicit": apiLimit ="?limitTo=[explicit]"; break;
        case "all": apiLimit ="";
        if(fname !=="" && lname !==""&& type==="random"){
            creds = "?firstName=" + fname + "&lastName=" + lname;
        }; break;
        default: apiLimit =""; break;
    }

    var query = "https://api.icndb.com/jokes/"+apiType+apiLimit+creds;
    debugger;
    return query;




}
})