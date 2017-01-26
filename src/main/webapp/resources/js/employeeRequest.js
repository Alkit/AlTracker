$('.request').on('click',function () {
    var value = $(this).attr('value');
    $('#requestForm').modal('show');
    $('#taskIdForRequest').val(value);
})


$('#taskRequest').form({
    on: ['blur','submit'],
    fields: {
        time:{
            identifier: 'time',
            rules: [{
                type: 'integer',
                prompt : 'Please enter a value'
            }]
        }
    }
})