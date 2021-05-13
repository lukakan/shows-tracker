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