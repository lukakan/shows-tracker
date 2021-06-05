$(document).ready(function (){
    $('#posterImage').change(function (){
        showImageThumbnail(this)
    })
});

function showImageThumbnail(posterImage){
    file = posterImage.files[0];
    reader = new FileReader();
    reader.onload = function (e){
        $('#thumbnail').attr('src', e.target.result);
    };

    reader.readAsDataURL(file);
}

function checkPasswords(form){

    let pass = document.getElementById("password").value;
    let repeated = document.getElementById("repeated-password").value;
    let passwordInformation = document.getElementById("password-information");
    let repeatedPasswordInformation = document.getElementById("repeated-password-information");


    repeatedPasswordInformation.innerHTML = "";
    passwordInformation.innerHTML = "";

    if(pass == ''){
        passwordInformation.innerHTML = "hasło nie może być puste";
        return false;
    } else if(repeated == ''){
        repeatedPasswordInformation.innerHTML = "powtórzone hasło nie może byc puste";
        return false;

    } else if(pass != repeated){
        repeatedPasswordInformation.innerHTML = "podane hasła różnią się od siebie";
        return false;
    } else {
        repeatedPasswordInformation.innerHTML = "";
        passwordInformation.innerHTML = "";
    }

}