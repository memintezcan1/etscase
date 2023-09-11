import toastr from 'toastr'

toastr.options = {
  "closeButton": true,
  "debug": false,
  "progressBar": true,
  "preventDuplicates": true,
  "positionClass": "toast-top-right",
  "onclick": null,
  "showDuration": "5000",
  "hideDuration": "1000",
  "timeOut": "15000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut",
  "newestOnTop": false,
}

export default toastr
