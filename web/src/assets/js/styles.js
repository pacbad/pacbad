$(window).scroll(function() {
  if ($(document).scrollTop() > 100) {
    $('nav').addClass('shrink');
  } else {
    $('nav').removeClass('shrink');
  }
});