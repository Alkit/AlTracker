

var url = $('#url')
var tree = $('#tree')
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
                        if(!sel.length) { return false; }
                        sel = sel[0];
                        sel = ref.create_node("p1", {"type":"file"});
                        if(sel) {
                            ref.edit(sel);
                        }
                    }
                },
                "Delete": {
                    "label": "Delete",
                    "action": function (data) {
                        var ref = $.jstree.reference(data.reference),
                            sel = ref.get_selected();
                        if(!sel.length) { return false; }
                        ref.delete_node(sel);

                    }
                }
            };
        }
    }
}).bind({
    "rename_node.jstree": function (e, data) {
        $.ajax({
                type: 'post',
                async: true,
                dataType: 'json',
                data: data.node.text,
                url: '../../sprint/add',
            }
        )
    },
    "delete_node.jstree": function (e, obj) {
        console.log(obj.node.text)
        var deletedNode = obj.node.text
        $.ajax({
            method: 'DELETE',
            async: true,
            data: deletedNode,
            url: '../../sprint/delete',
        })
        console.log("ajax sended")
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
