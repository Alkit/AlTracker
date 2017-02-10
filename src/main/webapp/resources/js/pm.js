

var url = $('#url');
var tree = $('#tree');
tree.jstree({
    'core': {
        "check_callback": true,
        'data': {
            'url': url.val(),
            'data': function (node) {
                return {'id': node.id};
            }
        }
    },
    plugins: ['themes', 'json_data', 'ui','contextmenu'],
    "contextmenu":{
        "items": function () {
            return {
                "Create": {

                    "label": "Create sprint",
                    "action": function (data) {
                        var ref = $.jstree.reference(data.reference);
                        sel = ref.get_selected();
                        if(!sel.length ) { return false; }

                        sel = sel[0];
                        sel = ref.create_node("p1", {"type":"file"});
                        if(sel) {
                            ref.edit(sel);
                        }
                    }
                },
                "Delete": {
                    "label": "Delete sprint",
                    "action": function (data) {
                        var ref = $.jstree.reference(data.reference),
                            sel = ref.get_selected();
                        if(!sel.length) { return false; }
                        ref.delete_node(sel);
                        var deletedNode = sel[0]
                        $.ajax({
                            method: 'DELETE',
                            async: true,
                            data: deletedNode,
                            url: '../../sprint/delete'
                        })
                    }
                }
            };
        }
    }
}).bind({
    "rename_node.jstree": function (e, data) {
        if(tree.jstree().get_node(data.node)){
            alert("already exists");
            tree.jstree().delete_node(data.node);
            return;
        }
        $.ajax({
                type: 'post',
                async: true,
                dataType: 'json',
                data: data.node.text,
                url: '../../sprint/add'
            }
        )
    }
});
var pid = $('#projectInfo').attr('value')
$('#projectInfo').load('../project/'+pid)

$('#tree').on('select_node.jstree', function (e,selected) {
    var id = selected.selected[0].slice(1);
    var url = '../sprint/'.concat(id)
    if(selected.selected[0][0] == 's'){
        $('#sprint').load(url)
    }
    else if (selected.selected[0][0] == 't'){
        var urlv2 = '../task/'.concat(id)
        $('#taskInfo').load(urlv2)
    }
})

$('#addTask').on('click',function () {
    $('#taskForm').modal({
        closable: false
    }).modal('show')
})

$('#taskF').form({
    on: ['submit'],
    fields:{
        taskName:{
            identifier: 'taskName',
            rules: [{
                type: 'empty' ,
                prompt : 'Please enter task name'
            }]
        },
        estimate: {
            identifier: 'estimateHours',
            rules: [{
                type: 'integer',
                prompt: 'Please enter number of hours'
            }]
        },
        executor: {
            identifier: 'executor',
            rules: [{
                type: 'empty',
                prompt: 'Please choose executor'
            }]
        },
        qualification: {
            identifier: 'qualification',
            rules: [{
                type: 'empty',
                prompt: 'Please choose qualification'
            }]
        },
        taskToSprint: {
            identifier: 'sprint',
            rules: [{
                type: 'empty',
                prompt: 'Please choose sprint or subtask'
            }]
        },
        bDate: {
            identifier: 'beginDate',
            rules: [{
                type: 'empty',
                prompt: 'Please enter beginning date'
            }]
        },
        eDate: {
            identifier: 'endDate',
            rules: [{
                type: 'empty',
                prompt: 'Please enter ending date'
            }]
        }
    }
});

$('.etr').on('click',function () {
    var etrId = $(this).attr('value')
    console.log(etrId)

    $.ajax({
        type: 'post',
        async: true,
        url: 'acceptTr/'+etrId
    })
    //$(this).parent().parent().parent().remove()
})