//= require jquery
//= require lodash
//= require js/uikit
//= require js/components/upload
//= require_self

var fileId = 'file_' + _.now() + '_' + _.random(10000)

jQuery(function($){
    var progressbar = $("#progressbar"),
        bar         = progressbar.find('.uk-progress-bar'),
        settings    = {

            action: '/upload',

            params: { 'fileId': fileId },

            loadstart: function() {
                bar.css("width", "0%").text("0%");
                progressbar.removeClass("uk-hidden");
            },

            progress: function(percent) {
                percent = Math.ceil(percent);
                bar.css("width", percent+"%").text(percent+"%");
            },

            allcomplete: function(response) {
                bar.css("width", "100%").text("100%");
                setTimeout(function(){
                    progressbar.addClass("uk-hidden");
                }, 250);

                alert("Upload completed.")
            }
        };

    $.UIkit.uploadSelect($("#upload-select"), settings);
    $.UIkit.uploadDrop($("#upload-drop"), settings);
});
