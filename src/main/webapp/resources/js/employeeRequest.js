$('.request').on('click',function () {
    var value = $(this).attr('value');
    $('#requestForm').modal('show');
    $('#taskIdForRequest').val(value);
})

$('.complete').on('click',function () {
    var value = $(this).attr('value');
    $('#completeForm').modal('show');
    $('#taskForComplete').val(value);
    console.log($('#taskForComplete').attr('value'))
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

$('#completeF').form({
    on: ['blur','submit'],
    fields: {
        time:{
            identifier: 'timeSpent',
            rules: [{
                type: 'integer',
                prompt : 'Please enter a value'
            }]
        }
    }
})