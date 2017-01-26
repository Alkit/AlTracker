

$('.acceptTask').on('click',function () {
    var taskId = $(this).attr('value')
    console.log(taskId)

    $.ajax({
        type: 'post',
        async: true,
        url: 'acceptTask/'+taskId,
    })
    $(this).parent().parent().parent().remove()
})